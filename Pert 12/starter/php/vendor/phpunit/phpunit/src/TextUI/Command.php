<?php

declare(strict_types=1);

namespace PHPUnit\TextUI;

use PHPUnit\Framework\Assert;
use PHPUnit\Framework\AssertionFailedError;
use PHPUnit\Framework\TestCase;
use RecursiveDirectoryIterator;
use RecursiveIteratorIterator;
use ReflectionClass;
use ReflectionMethod;
use Throwable;

final class Command
{
    /**
     * @param array<int, string> $argv
     */
    public static function main(array $argv = []): int
    {
        $target = getcwd() . DIRECTORY_SEPARATOR . 'tests';
        $filter = null;

        for ($i = 1, $count = count($argv); $i < $count; $i++) {
            $arg = $argv[$i];
            if ($arg === '--filter' && isset($argv[$i + 1])) {
                $filter = $argv[++$i];
            } elseif (str_starts_with($arg, '--filter=')) {
                $filter = substr($arg, 9);
            } elseif (!str_starts_with($arg, '-')) {
                $target = $arg;
            }
        }

        if (is_file($target)) {
            require_once $target;
        } elseif (is_dir($target)) {
            $iterator = new RecursiveIteratorIterator(new RecursiveDirectoryIterator($target));
            foreach ($iterator as $file) {
                if ($file->isFile() && str_ends_with($file->getFilename(), 'Test.php')) {
                    require_once $file->getPathname();
                }
            }
        } else {
            fwrite(STDERR, "Target test tidak ditemukan: {$target}" . PHP_EOL);
            return 1;
        }

        echo "PHPUnit 11.4.0 by Sebastian Bergmann and contributors." . PHP_EOL . PHP_EOL;

        $testClasses = [];
        foreach (get_declared_classes() as $className) {
            $ref = new ReflectionClass($className);
            if ($ref->isSubclassOf(TestCase::class) && !$ref->isAbstract()) {
                $testClasses[] = $ref;
            }
        }

        $totalTests = 0;
        $failures = [];

        foreach ($testClasses as $refClass) {
            foreach ($refClass->getMethods(ReflectionMethod::IS_PUBLIC) as $method) {
                if (!str_starts_with($method->getName(), 'test')) {
                    continue;
                }
                if ($filter !== null && !str_contains($method->getName(), $filter) && !str_contains($refClass->getName(), $filter)) {
                    continue;
                }

                $totalTests++;
                /** @var TestCase $instance */
                $instance = $refClass->newInstance();
                $instance->resetExpectations();

                $setUp = new ReflectionMethod($instance, 'setUp');
                $setUp->setAccessible(true);
                $tearDown = new ReflectionMethod($instance, 'tearDown');
                $tearDown->setAccessible(true);

                try {
                    $setUp->invoke($instance);
                    $method->invoke($instance);
                    $tearDown->invoke($instance);

                    $expectedEx = $instance->getExpectedException();
                    if ($expectedEx !== null) {
                        throw new AssertionFailedError("Failed asserting that exception of type \"{$expectedEx}\" is thrown.");
                    }
                    echo '.';
                } catch (Throwable $e) {
                    $expectedEx = $instance->getExpectedException();
                    if ($expectedEx !== null && $e instanceof $expectedEx) {
                        Assert::$assertionCount++;
                        $expectedMsg = $instance->getExpectedExceptionMessage();
                        if ($expectedMsg !== null && !str_contains($e->getMessage(), $expectedMsg)) {
                            echo 'F';
                            $failures[] = [
                                'test' => $refClass->getName() . '::' . $method->getName(),
                                'message' => "Failed asserting that exception message '{$e->getMessage()}' contains '{$expectedMsg}'.",
                            ];
                        } else {
                            echo '.';
                        }
                    } else {
                        echo 'F';
                        $failures[] = [
                            'test' => $refClass->getName() . '::' . $method->getName(),
                            'message' => $e::class . ': ' . $e->getMessage(),
                        ];
                    }
                }
            }
        }

        echo PHP_EOL . PHP_EOL;

        if ($failures !== []) {
            echo "There were " . count($failures) . " failure(s):" . PHP_EOL . PHP_EOL;
            foreach ($failures as $i => $f) {
                echo ($i + 1) . ") {$f['test']}" . PHP_EOL;
                echo "   {$f['message']}" . PHP_EOL . PHP_EOL;
            }
            echo "FAILURES!" . PHP_EOL;
            echo "Tests: {$totalTests}, Assertions: " . Assert::$assertionCount . ", Failures: " . count($failures) . "." . PHP_EOL;
            return 1;
        }

        echo "OK ({$totalTests} tests, " . Assert::$assertionCount . " assertions)" . PHP_EOL;
        return 0;
    }
}
