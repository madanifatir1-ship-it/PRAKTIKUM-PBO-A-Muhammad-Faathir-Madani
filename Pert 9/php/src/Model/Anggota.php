<?php

declare(strict_types=1);

namespace Perpustakaan\Model;

use InvalidArgumentException;

final class Anggota
{
    public function __construct(
        private string $nomorAnggota,
        private string $nama,
    ) {
        if ($nomorAnggota === '' || $nama === '') {
            throw new InvalidArgumentException('Nomor anggota dan nama wajib diisi.');
        }
    }

    public function getNomorAnggota(): string
    {
        return $this->nomorAnggota;
    }

    public function getNama(): string
    {
        return $this->nama;
    }
}
