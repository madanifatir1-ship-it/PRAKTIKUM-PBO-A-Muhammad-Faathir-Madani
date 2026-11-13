<?php
declare(strict_types=1);

namespace Perpustakaan\Model;

use InvalidArgumentException;

final class Buku
{
    public function __construct(
        private string $nomorInduk,
        private string $judul,
        private string $penulis,
        private int $jumlahEksemplar,
        private array $bab = [],
    ) {
        if ($nomorInduk === '' || $judul === '' || $penulis === '') {
            throw new InvalidArgumentException('Nomor induk, judul, dan penulis wajib diisi.');
        }
        if ($jumlahEksemplar < 0) {
            throw new InvalidArgumentException('Jumlah eksemplar tidak boleh negatif.');
        }
        foreach ($bab as $satuBab) {
            if (!$satuBab instanceof Bab) {
                throw new InvalidArgumentException('Daftar bab hanya boleh berisi objek Bab.');
            }
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

    public function getJumlahEksampelar(): int
    {
        return $this->jumlahEksemplar;
    }

    /** @return Bab[] */
    public function getBab(): array
    {
        return $this->bab;
    }

    public function tersedia(): bool
    {
        return $this->jumlahEksemplar > 0;
    }

    public function pinjam(): void
    {
        if (!$this->tersedia()) {
            throw new \LogicException('Stok buku tidak tersedia: ' . $this->nomorInduk);
        }
        --$this->jumlahEksemplar;
    }
}

final class Bab
{
    public function __construct(
        private string $judul,
        private int $halaman,
    ) {
        if ($judul === '' || $halaman < 1) {
            throw new InvalidArgumentException('Bab harus memiliki judul dan halaman positif.');
        }
    }

    public function getJudul(): string
    {
        return $this->judul;
    }

    public function getHalaman(): int
    {
        return $this->halaman;
    }
}
