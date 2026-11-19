<?php

declare(strict_types=1);

namespace PHPUnit\Framework;

use Countable;

abstract class Assert
{
    public static int $assertionCount = 0;

    public static function assertTrue(mixed $condition, string $message = ''): void
    {
        self::$assertionCount++;
        if ($condition !== true) {
            throw new AssertionFailedError($message !== '' ? $message : 'Failed asserting that false is true.');
        }
    }

    public static function assertFalse(mixed $condition, string $message = ''): void
    {
        self::$assertionCount++;
        if ($condition !== false) {
            throw new AssertionFailedError($message !== '' ? $message : 'Failed asserting that true is false.');
        }
    }

    public static function assertEquals(mixed $expected, mixed $actual, string $message = ''): void
    {
        self::$assertionCount++;
        if ($expected != $actual) {
            $expStr = var_export($expected, true);
            $actStr = var_export($actual, true);
            throw new AssertionFailedError($message !== '' ? $message : "Failed asserting that {$actStr} matches expected {$expStr}.");
        }
    }

    public static function assertNotEquals(mixed $expected, mixed $actual, string $message = ''): void
    {
        self::$assertionCount++;
        if ($expected == $actual) {
            throw new AssertionFailedError($message !== '' ? $message : 'Failed asserting that two values are not equal.');
        }
    }

    public static function assertSame(mixed $expected, mixed $actual, string $message = ''): void
    {
        self::$assertionCount++;
        if ($expected !== $actual) {
            throw new AssertionFailedError($message !== '' ? $message : 'Failed asserting that two variables reference the same value/object.');
        }
    }

    public static function assertNotSame(mixed $expected, mixed $actual, string $message = ''): void
    {
        self::$assertionCount++;
        if ($expected === $actual) {
            throw new AssertionFailedError($message !== '' ? $message : 'Failed asserting that two variables do not reference the same value/object.');
        }
    }

    public static function assertNull(mixed $actual, string $message = ''): void
    {
        self::$assertionCount++;
        if ($actual !== null) {
            throw new AssertionFailedError($message !== '' ? $message : 'Failed asserting that value is null.');
        }
    }

    public static function assertNotNull(mixed $actual, string $message = ''): void
    {
        self::$assertionCount++;
        if ($actual === null) {
            throw new AssertionFailedError($message !== '' ? $message : 'Failed asserting that value is not null.');
        }
    }

    public static function assertEmpty(mixed $actual, string $message = ''): void
    {
        self::$assertionCount++;
        if (!empty($actual)) {
            throw new AssertionFailedError($message !== '' ? $message : 'Failed asserting that value is empty.');
        }
    }

    public static function assertNotEmpty(mixed $actual, string $message = ''): void
    {
        self::$assertionCount++;
        if (empty($actual)) {
            throw new AssertionFailedError($message !== '' ? $message : 'Failed asserting that value is not empty.');
        }
    }

    /**
     * @param iterable<mixed>|Countable $haystack
     */
    public static function assertCount(int $expectedCount, iterable|Countable $haystack, string $message = ''): void
    {
        self::$assertionCount++;
        $actualCount = is_array($haystack) || $haystack instanceof Countable
            ? count($haystack)
            : iterator_count($haystack);
        if ($expectedCount !== $actualCount) {
            throw new AssertionFailedError($message !== '' ? $message : "Failed asserting that actual size {$actualCount} matches expected size {$expectedCount}.");
        }
    }

    /**
     * @param iterable<mixed> $haystack
     */
    public static function assertContains(mixed $needle, iterable $haystack, string $message = ''): void
    {
        self::$assertionCount++;
        foreach ($haystack as $item) {
            if ($item === $needle) {
                return;
            }
        }
        throw new AssertionFailedError($message !== '' ? $message : 'Failed asserting that haystack contains expected value.');
    }

    public static function assertInstanceOf(string $expected, mixed $actual, string $message = ''): void
    {
        self::$assertionCount++;
        if (!($actual instanceof $expected)) {
            $actualType = is_object($actual) ? $actual::class : gettype($actual);
            throw new AssertionFailedError($message !== '' ? $message : "Failed asserting that {$actualType} is an instance of class {$expected}.");
        }
    }

    public static function assertStringContainsString(string $needle, string $haystack, string $message = ''): void
    {
        self::$assertionCount++;
        if (!str_contains($haystack, $needle)) {
            throw new AssertionFailedError($message !== '' ? $message : "Failed asserting that '{$haystack}' contains '{$needle}'.");
        }
    }

    public static function fail(string $message = ''): never
    {
        throw new AssertionFailedError($message !== '' ? $message : 'Test failed.');
    }
}
