<?php

declare(strict_types=1);

namespace Perpustakaan\Model;

use InvalidArgumentException;

final class Buku
{
    public function __construct(
        private string $kode,
        private string $judul,
        private string $penulis,
        private int $harga,
        private int $stok = 1,
    ) {
        if ($kode === '' || $judul === '' || $penulis === '') {
            throw new InvalidArgumentException('Kode, judul, dan penulis buku wajib diisi.');
        }
        if ($harga < 0) {
            throw new InvalidArgumentException('Harga buku tidak boleh negatif.');
        }
        if ($stok < 0) {
            throw new InvalidArgumentException('Stok buku tidak boleh negatif.');
        }
    }

    public function getKode(): string
    {
        return $this->kode;
    }

    public function getJudul(): string
    {
        return $this->judul;
    }

    public function getPenulis(): string
    {
        return $this->penulis;
    }

    public function getHarga(): int
    {
        return $this->harga;
    }

    public function getStok(): int
    {
        return $this->stok;
    }

    public function kurangiStok(): void
    {
        if ($this->stok === 0) {
            throw new \LogicException('Stok buku sudah habis.');
        }
        --$this->stok;
    }

    public function tambahStok(): void
    {
        ++$this->stok;
    }
}
