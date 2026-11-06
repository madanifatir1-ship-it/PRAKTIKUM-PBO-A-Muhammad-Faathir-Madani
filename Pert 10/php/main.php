<?php

declare(strict_types=1);

require __DIR__ . '/vendor/autoload.php';

use Id\Ac\Univpancasila\Perpustakaan\Exception\PerpustakaanException;
use Id\Ac\Univpancasila\Perpustakaan\Service\LibraryService;

$logPath = __DIR__ . '/perpustakaan.log';

function catatLog(Throwable $exception, string $logPath): void
{
    $baris = date('c') . ' | ' . $exception::class . ' | ' . $exception->getMessage() . PHP_EOL;
    file_put_contents($logPath, $baris, FILE_APPEND | LOCK_EX);
}

$service = new LibraryService();
$service->tambahBuku('BKU-001', 'Pemrograman Berorientasi Objek', 3);
$service->tambahBuku('BKU-002', 'Struktur Data', 0);
$service->daftarkanAnggota('A001');
$service->daftarkanAnggota('A002');

echo "=== SISTEM PERPUSTAKAAN ===\n";
while (true) {
    echo "1. Pinjam buku\n2. Ekspor daftar buku\n0. Keluar\nPilihan: ";
    $input = trim((string) fgets(STDIN));

    try {
        if ($input === '') {
            throw new InvalidArgumentException('Pilihan menu tidak boleh kosong');
        }
        if (!ctype_digit($input) && !($input !== '' && $input[0] === '-' && ctype_digit(substr($input, 1)))) {
            throw new InvalidArgumentException('Pilihan menu harus berupa angka');
        }

        $pilihan = (int) $input;
        if ($pilihan === 0) {
            break;
        }
        if ($pilihan === 1) {
            echo 'ID anggota: ';
            $idAnggota = trim((string) fgets(STDIN));
            echo 'ISBN: ';
            $isbn = trim((string) fgets(STDIN));
            $service->pinjam($idAnggota, $isbn);
            echo "Peminjaman berhasil\n";
        } elseif ($pilihan === 2) {
            $tujuan = __DIR__ . '/daftar-buku.txt';
            $service->eksporDaftarBuku($tujuan);
            echo "Daftar buku diekspor ke $tujuan\n";
        } else {
            throw new InvalidArgumentException('Pilihan menu tidak tersedia');
        }
    } catch (PerpustakaanException $exception) {
        echo $exception->getMessage(), "\n";
        catatLog($exception, $logPath);
    } catch (Throwable $exception) {
        echo $exception->getMessage(), "\n";
        catatLog($exception, $logPath);
    }
}
