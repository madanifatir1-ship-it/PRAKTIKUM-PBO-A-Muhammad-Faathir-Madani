<?php
declare(strict_types=1);

require_once __DIR__ . '/Mahasiswa.php';

echo '=== Rekap Nilai ===', PHP_EOL;
$kelas = [
    new Mahasiswa('2024001', 'Ani Lestari',  85, 78, 90),
    new Mahasiswa('2024002', 'Budi Santoso', 60, 55, 62),
    new Mahasiswa('2024003', 'Citra Wijaya', 92, 88, 95),
];
foreach ($kelas as $m) {
    echo '  ', $m, PHP_EOL;
}

echo PHP_EOL, '=== Objek menolak data yang melanggar aturan ===', PHP_EOL;

try {
    new Mahasiswa('2024004', 'Salah Nilai', 150, 80, 80);
    echo '  MASALAH: nilai 150 seharusnya ditolak!', PHP_EOL;
} catch (InvalidArgumentException $e) {
    echo '  Ditolak: ', $e->getMessage(), PHP_EOL;
}

try {
    new Mahasiswa('', 'NIM Kosong', 80, 80, 80);
    echo '  MASALAH: NIM kosong seharusnya ditolak!', PHP_EOL;
} catch (InvalidArgumentException $e) {
    echo '  Ditolak: ', $e->getMessage(), PHP_EOL;
}

try {
    $kelas[0]->setNilaiTugas(150);
    echo '  MASALAH: setter seharusnya menolak nilai 150!', PHP_EOL;
} catch (InvalidArgumentException $e) {
    echo '  Ditolak setter: ', $e->getMessage(), PHP_EOL;
}

require_once __DIR__ . '/Buku.php';

echo PHP_EOL, '=== Uji peminjaman buku ===', PHP_EOL;
$buku = new Buku('978-602-000000-0', 'Pemrograman Berorientasi Objek', 'Tim Praktikum', 1);
$buku->pinjam();
echo '  Sisa setelah dipinjam: ', $buku->getJumlahTersedia(), PHP_EOL;
try {
    $buku->pinjam();
    echo '  MASALAH: buku habis seharusnya tidak dapat dipinjam!', PHP_EOL;
} catch (LogicException $e) {
    echo '  Ditolak: ', $e->getMessage(), PHP_EOL;
}
$buku->kembalikan();
echo '  Sisa setelah dikembalikan: ', $buku->getJumlahTersedia(), PHP_EOL;
