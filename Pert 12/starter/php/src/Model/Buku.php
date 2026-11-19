<?php

declare(strict_types=1);

namespace Perpustakaan\Model;

use InvalidArgumentException;
use RuntimeException;

final class Buku
{
    public function __construct(
        private readonly string $nomorInduk,
        private readonly string $judul,
        private readonly string $penulis,
        private int $jumlahEksemplar,
    ) {
        if (trim($nomorInduk) === '') {
            throw new InvalidArgumentException('ISBN / Nomor Induk buku tidak boleh kosong.');
        }
        if (trim($judul) === '') {
            throw new InvalidArgumentException('Judul buku tidak boleh kosong.');
        }
        if (trim($penulis) === '') {
            throw new InvalidArgumentException('Penulis buku tidak boleh kosong.');
        }
        if ($jumlahEksemplar < 0) {
            throw new InvalidArgumentException('Jumlah eksemplar tidak boleh negatif.');
        }
    }

    public function getNomorInduk(): string
    {
        return $this->nomorInduk;
    }

    public function getJudul(): string
    {
        return $this->judul;
    }

    public function getPenulis(): string
    {
        return $this->penulis;
    }

    public function getJumlahEksemplar(): int
    {
        return $this->jumlahEksemplar;
    }

    public function getJumlahEksampelar(): int
    {
        return $this->jumlahEksemplar;
    }

    public function tersedia(): bool
    {
        return $this->jumlahEksemplar > 0;
    }

    public function tambahStok(): void
    {
        $this->jumlahEksemplar++;
    }

    public function kurangiStok(): void
    {
        if ($this->jumlahEksemplar <= 0) {
            throw new RuntimeException("Stok buku '{$this->judul}' sudah habis.");
        }
        $this->jumlahEksemplar--;
    }
}
