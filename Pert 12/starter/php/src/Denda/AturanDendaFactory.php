<?php

declare(strict_types=1);

namespace Perpustakaan\Denda;

use InvalidArgumentException;

final class AturanDendaFactory
{
    public static function untuk(string $jenisAnggota): AturanDenda
    {
        return match (strtolower(trim($jenisAnggota))) {
            'mahasiswa' => new DendaMahasiswa(),
            'dosen' => new DendaDosen(),
            'kehormatan', 'bebas' => new DendaBebas(),
            'alumni' => new DendaAlumni(),
            default => throw new InvalidArgumentException("Jenis anggota tidak dikenal: {$jenisAnggota}"),
        };
    }
}
