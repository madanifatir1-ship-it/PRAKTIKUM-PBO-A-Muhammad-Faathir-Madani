<?php
declare(strict_types=1);

require_once __DIR__ . '/exceptions.php';
require_once __DIR__ . '/LibraryService.php';

$service = new LibraryService();
$service->tambahBuku('BKU-001', 'Pemrograman Berorientasi Objek', 3);
$service->tambahBuku('BKU-002', 'Struktur Data', 0);
$service->daftarkanAnggota('A001');
$service->daftarkanAnggota('A002');
$logPath = __DIR__ . '/perpustakaan.log';

function catatLog(Throwable $e, string $logPath): void
{
    $baris = date('c') . ' | ' . get_class($e) . ' | ' . $e->getMessage() . PHP_EOL;
    file_put_contents($logPath, $baris, FILE_APPEND);
}

try {
    $service->pinjam('ANG-001', 'BKU-X');
} catch (PerpustakaanException $e) {
    echo $e->getMessage(), PHP_EOL;
    catatLog($e, $logPath);
} catch (Throwable $e) {
    echo 'Gagal: ', $e->getMessage(), PHP_EOL;
    catatLog($e, $logPath);
}

$ekspor = __DIR__ . '/daftar-buku.txt';
try {
    $service->eksporDaftarBuku($ekspor);
    echo "Daftar buku diekspor ke $ekspor", PHP_EOL;
} catch (Throwable $e) {
    echo 'Ekspor gagal: ', $e->getMessage(), PHP_EOL;
    catatLog($e, $logPath);
}
