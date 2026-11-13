<?php
declare(strict_types=1);

namespace Perpustakaan\Service;

use Perpustakaan\Exception\AnggotaTidakDitemukanException;
use Perpustakaan\Exception\BatasPinjamTerlampauiException;
use Perpustakaan\Exception\BukuSedangDipinjamException;
use Perpustakaan\Exception\BukuTidakDitemukanException;
use Perpustakaan\Model\Buku;
use Perpustakaan\Repository\KoleksiBuku;

final class LibraryService
{
    public const BATAS_PINJAM = 3;

    private KoleksiBuku $koleksiBuku;
    /** @var array<string, int> */
    private array $pinjamanPerAnggota = [];

    public function __construct(?KoleksiBuku $koleksiBuku = null)
    {
        $this->koleksiBuku = $koleksiBuku ?? new KoleksiBuku();
    }

    public function tambahBuku(Buku $buku): void
    {
        $this->koleksiBuku->simpan($buku);
    }

    public function daftarkanAnggota(string $idAnggota): void
    {
        $idAnggota = trim($idAnggota);
        if ($idAnggota === '') {
            throw new \InvalidArgumentException('ID anggota tidak boleh kosong');
        }
        $this->pinjamanPerAnggota[$idAnggota] ??= 0;
    }

    public function pinjam(string $idAnggota, string $isbn): void
    {
        $idAnggota = trim($idAnggota);
        $isbn = trim($isbn);
        if ($idAnggota === '') {
            throw new \InvalidArgumentException('ID anggota tidak boleh kosong');
        }
        if ($isbn === '') {
            throw new \InvalidArgumentException('ISBN tidak boleh kosong');
        }
        if (!array_key_exists($idAnggota, $this->pinjamanPerAnggota)) {
            throw new AnggotaTidakDitemukanException($idAnggota);
        }
        $buku = $this->koleksiBuku->cariByIsbn($isbn);
        if ($buku === null) {
            throw new BukuTidakDitemukanException($isbn);
        }
        if ($this->pinjamanPerAnggota[$idAnggota] >= self::BATAS_PINJAM) {
            throw new BatasPinjamTerlampauiException($idAnggota, self::BATAS_PINJAM);
        }
        if (!$buku->tersedia()) {
            throw new BukuSedangDipinjamException($isbn, $buku->getJudul());
        }
        $buku->pinjam();
        ++$this->pinjamanPerAnggota[$idAnggota];
    }

    /** @return Buku[] */
    public function getBukuTersedia(): array
    {
        return $this->koleksiBuku->filter(
            static fn (Buku $buku): bool => $buku->tersedia(),
        )->semua();
    }

    public function getSemuaJudulDigabung(): string
    {
        return implode(', ', $this->koleksiBuku->map(
            static fn (Buku $buku): string => $buku->getJudul(),
        ));
    }

    /** @return array<string, int> */
    public function getJumlahBukuPerPenulis(): array
    {
        $kelompok = $this->koleksiBuku->kelompokkan(
            static fn (Buku $buku): string => $buku->getPenulis(),
        );
        return array_map('count', $kelompok);
    }

    /** @return Buku[] */
    public function getSemuaBuku(): array
    {
        return $this->koleksiBuku->semua();
    }
}
