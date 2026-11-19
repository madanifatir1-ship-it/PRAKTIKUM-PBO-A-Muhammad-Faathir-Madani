<?php

declare(strict_types=1);

namespace Perpustakaan\Denda;

interface AturanDenda
{
    public function hitung(int $hariTerlambat): float;

    public function nama(): string;
}
