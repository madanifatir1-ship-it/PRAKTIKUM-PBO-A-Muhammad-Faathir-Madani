<?php

declare(strict_types=1);

namespace Perpustakaan\Denda;

final class DendaMahasiswa implements AturanDenda
{
    private const TARIF_PER_HARI = 1000.0;

    public function hitung(int $hariTerlambat): float
    {
        return max(0, $hariTerlambat) * self::TARIF_PER_HARI;
    }

    public function nama(): string
    {
        return 'Mahasiswa (Rp1.000/hari)';
    }
}
