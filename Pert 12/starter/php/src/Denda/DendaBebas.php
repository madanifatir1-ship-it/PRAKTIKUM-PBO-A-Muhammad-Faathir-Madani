<?php

declare(strict_types=1);

namespace Perpustakaan\Denda;

final class DendaBebas implements AturanDenda
{
    public function hitung(int $hariTerlambat): float
    {
        return 0.0;
    }

    public function nama(): string
    {
        return 'Kehormatan (Bebas Denda)';
    }
}
