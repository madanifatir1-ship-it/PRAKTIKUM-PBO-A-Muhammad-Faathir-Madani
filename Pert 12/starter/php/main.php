<?php

require __DIR__ . '/vendor/autoload.php';

use Carbon\Carbon;
use Perpustakaan\Denda\AturanDendaFactory;
use Perpustakaan\Exception\PerpustakaanException;
use Perpustakaan\Repository\InMemoryBookRepository;
use Perpustakaan\Service\LibraryService;
use Perpustakaan\Service\PerpustakaanService;
use Perpustakaan\Util\LaporanExporter;

/*
 * DEMO: titik masuk program (manual Dependency Injection)
 */
$repo = new InMemoryBookRepository();
$aturanDenda = AturanDendaFactory::untuk('mahasiswa');
$s = new LibraryService($repo, $aturanDenda);

$s->tambahBuku('111', 'Laskar Pelangi', 'Andrea Hirarta', 1);
$s->tambahBuku('222', 'Bumi Manusia', 'Pramoedya', 5);

$s->daftarkanAnggota('A-001');
$s->daftarkanAnggota('A-002');

$percobaan = [
    ['A-001', '111'], // berhasil
    ['A-002', '111'], // buku habis
    ['A-002', '999'], // buku tidak ada
    ['X-999', '222'], // anggota tidak terdaftar
    ['A-001', '222'], // berhasil
    ['A-001', '222'], // berhasil
    ['A-001', '222'], // batas pinjam tercapai
];

foreach ($percobaan as [$id, $isbn]) {
    echo "Anggota $id meminjam ISBN '$isbn' -> ";
    try {
        $s->pinjam($id, $isbn);
        echo " [BERHASIL]\n";
    } catch (PerpustakaanException $e) {
        // unchecked
        echo " [GAGAL] " . $e->getMessage() . "\n";
    }
}

$perpusService = new PerpustakaanService();
$perpusService->tampilkanLaporanFungsional($s);

createReport($s);

function createReport(LibraryService $s): void
{
    LaporanExporter::eksporKeBerkas($s, __DIR__ . '/laporan_buku.txt');
}
