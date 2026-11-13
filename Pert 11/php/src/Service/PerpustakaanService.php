<?php
declare(strict_types=1);

namespace Perpustakaan\Service;

use Perpustakaan\Model\Buku;
use RuntimeException;

final class PerpustakaanService
{
    public function __construct(private LibraryService $libraryService)
    {
    }

    public function tampilkanLaporan(): void
    {
        echo "Buku tersedia:", PHP_EOL;
        foreach ($this->libraryService->getBukuTersedia() as $buku) {
            echo $buku->getNomorInduk(), ' - ', $buku->getJudul(), PHP_EOL;
        }
        echo 'Semua judul: ', $this->libraryService->getSemuaJudulDigabung(), PHP_EOL;
        echo 'Jumlah judul per penulis:', PHP_EOL;
        foreach ($this->libraryService->getJumlahBukuPerPenulis() as $penulis => $jumlah) {
            echo $penulis, ': ', $jumlah, PHP_EOL;
        }
    }

    public function eksporLaporan(string $tujuan): void
    {
        $baris = ['LAPORAN PERPUSTAKAAN', 'Buku tersedia:'];
        foreach ($this->libraryService->getBukuTersedia() as $buku) {
            $baris[] = $buku->getNomorInduk() . ' - ' . $buku->getJudul()
                . ' (' . $buku->getPenulis() . ')';
        }
        $baris[] = 'Semua judul: ' . $this->libraryService->getSemuaJudulDigabung();
        $baris[] = 'Jumlah judul per penulis:';
        foreach ($this->libraryService->getJumlahBukuPerPenulis() as $penulis => $jumlah) {
            $baris[] = $penulis . ': ' . $jumlah;
        }
        $hasil = file_put_contents($tujuan, implode(PHP_EOL, $baris) . PHP_EOL);
        if ($hasil === false) {
            throw new RuntimeException("Gagal menulis laporan ke [$tujuan]");
        }
    }
}
