<?php

declare(strict_types=1);

namespace Perpustakaan\Util;

use Exception;
use Perpustakaan\Service\LibraryService;

final class LaporanExporter
{
    public static function eksporKeBerkas(LibraryService $service, string $filePath): void
    {
        echo PHP_EOL . "--- Ekspor Laporan Buku ---" . PHP_EOL;
        $namaFile = basename($filePath);

        $fileHandle = fopen($filePath, 'w');
        if (!$fileHandle) {
            echo "[ERROR] Gagal membuka file untuk menulis.\n";
            return;
        }

        try {
            fwrite($fileHandle, "==============================================================================\n");
            fwrite($fileHandle, "                       LAPORAN DAFTAR BUKU PERPUSTAKAAN                       \n");
            fwrite($fileHandle, "==============================================================================\n");
            fwrite($fileHandle, sprintf("%-12s | %-35s | %-8s | %-15s\n", "ISBN", "JUDUL BUKU", "STOK", "STATUS"));
            fwrite($fileHandle, "------------------------------------------------------------------------------\n");

            $daftarJudul = $service->getSemuaJudul();
            foreach ($daftarJudul as $isbn => $judulBuku) {
                $stokSisa = $service->sisaEksemplar((string) $isbn);
                $status = ($stokSisa > 0) ? "Tersedia" : "Tidak Tersedia";
                fwrite($fileHandle, sprintf("%-12s | %-35s | %-8d | %-15s\n", $isbn, $judulBuku, $stokSisa, $status));
            }
            fwrite($fileHandle, "==============================================================================\n");
            echo ">> BERHASIL: Laporan buku beserta statusnya disimpan ke berkas '{$namaFile}'.\n";
        } catch (Exception $e) {
            echo "[ERROR SISTEM] Gagal menyelesaikan laporan: " . $e->getMessage() . "\n";
        } finally {
            if (is_resource($fileHandle)) {
                fclose($fileHandle);
                echo "[INFO] File '{$namaFile}' telah ditutup dengan aman melalui blok finally.\n";
            }
        }
    }
}
