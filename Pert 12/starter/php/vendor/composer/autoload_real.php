<?php

require_once __DIR__ . '/ClassLoader.php';

class ComposerAutoloaderInitPerpustakaan
{
    private static ?\Composer\Autoload\ClassLoader $loader = null;

    public static function getLoader(): \Composer\Autoload\ClassLoader
    {
        if (self::$loader !== null) {
            return self::$loader;
        }

        require __DIR__ . '/platform_check.php';

        self::$loader = $loader = new \Composer\Autoload\ClassLoader();

        $map = require __DIR__ . '/autoload_psr4.php';
        foreach ($map as $namespace => $path) {
            $loader->addPsr4($namespace, $path);
        }

        $loader->register(true);

        return $loader;
    }
}
