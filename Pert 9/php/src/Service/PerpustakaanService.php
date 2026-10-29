<?php

declare(strict_types=1);

namespace Perpustakaan\Service;

use Carbon\CarbonImmutable;
use InvalidArgumentException;
use OutOfBoundsException;
use Perpustakaan\Model\Anggota;
use Perpustakaan\Model\Buku;
use Perpustakaan\Model\Peminjaman;

final class PerpustakaanService
{
    /** @var array<string, Buku> */
    private array $daftarBuku = [];

    /** @var array<string, Anggota> */
    private array $daftarAnggota = [];

    /** @var list<Peminjaman> */
    private array $riwayatPeminjaman = [];

    public function tambahBuku(Buku $buku): void
    {
        if (isset($this->daftarBuku[$buku->getKode()])) {
            throw new InvalidArgumentException('Kode buku sudah terdaftar.');
        }
        $this->daftarBuku[$buku->getKode()] = $buku;
    }

    public function daftarkanAnggota(Anggota $anggota): void
    {
        if (isset($this->daftarAnggota[$anggota->getNomorAnggota()])) {
            throw new InvalidArgumentException('Nomor anggota sudah terdaftar.');
        }
        $this->daftarAnggota[$anggota->getNomorAnggota()] = $anggota;
    }

    public function cariBuku(string $kode): Buku
    {
        if (!isset($this->daftarBuku[$kode])) {
            throw new OutOfBoundsException('Buku tidak ditemukan: ' . $kode);
        }
        return $this->daftarBuku[$kode];
    }

    public function pinjam(
        string $nomorAnggota,
        string $kodeBuku,
        ?CarbonImmutable $tanggal = null,
    ): Peminjaman {
        if (!isset($this->daftarAnggota[$nomorAnggota])) {
            throw new OutOfBoundsException('Anggota tidak ditemukan: ' . $nomorAnggota);
        }

        $anggota = $this->daftarAnggota[$nomorAnggota];
        $buku = $this->cariBuku($kodeBuku);
        if ($buku->getStok() === 0) {
            throw new \LogicException('Stok buku tidak tersedia.');
        }

        $tanggalPinjam = $tanggal ?? CarbonImmutable::now();
        $tanggalJatuhTempo = $tanggalPinjam->addDays(14);
        $buku->kurangiStok();

        $peminjaman = new Peminjaman(
            $anggota,
            $buku,
            $tanggalPinjam,
            $tanggalJatuhTempo,
        );
        $this->riwayatPeminjaman[] = $peminjaman;
        return $peminjaman;
    }

    /** @return list<Buku> */
    public function getDaftarBuku(): array
    {
        return array_values($this->daftarBuku);
    }

    /** @return list<Peminjaman> */
    public function getRiwayatPeminjaman(): array
    {
        return $this->riwayatPeminjaman;
    }
}
