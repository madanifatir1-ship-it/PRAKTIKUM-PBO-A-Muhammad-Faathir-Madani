<?php

declare(strict_types=1);

namespace Perpustakaan\Denda;

final class DendaAlumni implements AturanDenda
{
    private const TARIF_PER_HARI = 750.0;

    public function hitung(int $hariTerlambat): float
    {
        return max(0, $hariTerlambat) * self::TARIF_PER_HARI;
    }

    public function nama(): string
    {
        return 'Alumni (Rp750/hari)';
    }
}
