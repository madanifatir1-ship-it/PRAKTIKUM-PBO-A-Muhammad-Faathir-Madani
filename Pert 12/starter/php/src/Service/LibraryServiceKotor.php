<?php

declare(strict_types=1);

namespace Perpustakaan\Service;

use InvalidArgumentException;

final class LibraryServiceKotor
{
    public const BATAS_PINJAM = 3;

    /** @var array<string, int> */
    private array $stok = [];

    /** @var array<string, string> */
    private array $judul = [];

    /** @var array<string, int> */
    private array $pinjaman = [];

    public function __construct(
        private readonly string $jenisAnggota,
    ) {}

    public function tambahBuku(string $isbn, string $judulBuku, int $eksemplar): void
    {
        $this->stok[$isbn] = ($this->stok[$isbn] ?? 0) + $eksemplar;
        $this->judul[$isbn] = $judulBuku;
    }

    public function daftarkanAnggota(string $idAnggota): void
    {
        $this->pinjaman[$idAnggota] ??= 0;
    }

    /** INILAH yang harus Anda refaktor. */
    public function pinjam(string $idAnggota, string $isbn): bool
    {
        if (array_key_exists($idAnggota, $this->pinjaman)) {
            if (array_key_exists($isbn, $this->stok)) {
                if ($this->stok[$isbn] > 0) {
                    if ($this->pinjaman[$idAnggota] < self::BATAS_PINJAM) {
                        $this->stok[$isbn]--;
                        $this->pinjaman[$idAnggota]++;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public function hitungDenda(int $hariTerlambat): float
    {
        if ($hariTerlambat <= 0) {
            return 0.0;
        }
        return match (strtolower(trim($this->jenisAnggota))) {
            'mahasiswa' => $hariTerlambat * 1000.0,
            'dosen' => $hariTerlambat * 500.0,
            'kehormatan' => 0.0,
            default => throw new InvalidArgumentException("Jenis anggota tidak dikenal: {$this->jenisAnggota}"),
        };
    }

    public function sisaEksemplar(string $isbn): int
    {
        return $this->stok[$isbn] ?? 0;
    }

    public function judulDari(string $isbn): ?string
    {
        return $this->judul[$isbn] ?? null;
    }
}
