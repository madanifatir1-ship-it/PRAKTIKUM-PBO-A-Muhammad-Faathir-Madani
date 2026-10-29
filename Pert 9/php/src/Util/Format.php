<?php

declare(strict_types=1);

namespace Perpustakaan\Util;

final class Format
{
    public static function rupiah(int|float $nilai): string
    {
        return 'Rp ' . number_format((float) $nilai, 0, ',', '.');
    }

    public static function tanggal(\DateTimeInterface|string $tanggal): string
    {
        if (is_string($tanggal)) {
            $tanggal = new \DateTimeImmutable($tanggal);
        }
        return $tanggal->format('d-m-Y');
    }
}
