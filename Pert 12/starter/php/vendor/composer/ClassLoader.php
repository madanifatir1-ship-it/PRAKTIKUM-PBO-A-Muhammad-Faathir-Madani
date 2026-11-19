<?php

namespace Composer\Autoload;

class ClassLoader
{
    /** @var array<string, array<int, string>> */
    private array $prefixDirsPsr4 = [];

    /**
     * @param string $prefix
     * @param string|array<int, string> $paths
     */
    public function addPsr4(string $prefix, string|array $paths): void
    {
        $paths = (array) $paths;
        if (!isset($this->prefixDirsPsr4[$prefix])) {
            $this->prefixDirsPsr4[$prefix] = $paths;
        } else {
            $this->prefixDirsPsr4[$prefix] = array_merge($this->prefixDirsPsr4[$prefix], $paths);
        }
    }

    public function register(bool $prepend = false): void
    {
        spl_autoload_register([$this, 'loadClass'], true, $prepend);
    }

    public function loadClass(string $class): ?bool
    {
        $file = $this->findFile($class);
        if ($file !== null && is_file($file)) {
            require $file;
            return true;
        }
        return null;
    }

    public function findFile(string $class): ?string
    {
        $logicalPathPsr4 = strtr($class, '\\', DIRECTORY_SEPARATOR) . '.php';
        foreach ($this->prefixDirsPsr4 as $prefix => $dirs) {
            if (str_starts_with($class, $prefix)) {
                $subPath = substr($logicalPathPsr4, strlen($prefix));
                foreach ($dirs as $dir) {
                    $file = $dir . DIRECTORY_SEPARATOR . $subPath;
                    if (is_file($file)) {
                        return $file;
                    }
                }
            }
        }
        return null;
    }
}
