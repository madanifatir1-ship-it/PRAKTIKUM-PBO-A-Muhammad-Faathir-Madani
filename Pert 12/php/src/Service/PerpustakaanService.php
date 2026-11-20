<?php
namespace Perpustakaan\Service;

use Perpustakaan\Model\Buku;

class PerpustakaanService
{
    /** @var Buku[] */
    private array $buku = [];

    public function tambahKoleksi(Buku $buku): void
    {
        $this->buku[] = $buku;
    }

    public function cariKoleksi(string $nomorInduk): ?Buku
    {
        foreach ($this->buku as $item) {
            if ($item->getNomorInduk() === $nomorInduk) {
                return $item;
            }
        }
        return null;
    }

    public function pinjam(string $nomorAnggota, string $nomorInduk): void
    {
        echo "Anggota {$nomorAnggota} meminjam buku {$nomorInduk}\n";
    }

    public function kembalikan(string $nomorAnggota, string $nomorInduk): void
    {
        echo "Anggota: {$nomorAnggota} mengembalikan buku {$nomorInduk}\n";
    }

    public function tampilkanLaporanFungsional(LibraryService $service): void
    {
        echo PHP_EOL . "--- LAPORAN FUNGSIONAL PERPUSTAKAAN ---" . PHP_EOL;
        $tersedia = $service->getBukuTersedia();
        echo "1. Jumlah Buku Tersedia: " . count($tersedia) . PHP_EOL;
        foreach ($tersedia as $buku) {
            echo "   - [{$buku->getNomorInduk()}] {$buku->getJudul()} - {$buku->getPenulis()} (Stok: {$buku->getJumlahEksemplar()})" . PHP_EOL;
        }

        $semuaJudul = $service->getSemuaJudulDigabung();
        echo "2. Semua Judul Digabung: " . ($semuaJudul !== '' ? $semuaJudul : '-') . PHP_EOL;

        echo "3. Jumlah Buku per Penulis:" . PHP_EOL;
        foreach ($service->getJumlahBukuPerPenulis() as $penulis => $jumlah) {
            echo "   - {$penulis}: {$jumlah}" . PHP_EOL;
        }

        $denda = number_format($service->hitungDenda(3), 0, ',', '.');
        echo "4. Simulasi Denda 3 Hari ({$service->getNamaAturanDenda()}): Rp{$denda}" . PHP_EOL;
    }
}
