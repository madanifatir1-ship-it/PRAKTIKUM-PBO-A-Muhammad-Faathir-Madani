<?php

declare(strict_types=1);

require __DIR__ . '/vendor/autoload.php';

use Carbon\CarbonImmutable;
use Perpustakaan\Model\Anggota;
use Perpustakaan\Model\Buku;
use Perpustakaan\Service\PerpustakaanService;
use Perpustakaan\Util\Format;

$service = new PerpustakaanService();

$buku = new Buku(
    'BKU-001',
    'Pemrograman Berorientasi Objek',
    'Muhammad Faathir Madani',
    125000,
    3,
);
$anggota = new Anggota('ANG-001', 'M. Faathir Madani');

$service->tambahBuku($buku);
$service->daftarkanAnggota($anggota);

$tanggalPinjam = CarbonImmutable::now();
$peminjaman = $service->pinjam('ANG-001', 'BKU-001', $tanggalPinjam);

echo "=== SISTEM PERPUSTAKAAN ===\n";
echo 'Anggota       : ', $anggota->getNama(), ' (', $anggota->getNomorAnggota(), ")\n";
echo 'Buku          : ', $buku->getJudul(), "\n";
echo 'Harga         : ', Format::rupiah($buku->getHarga()), "\n";
echo 'Tanggal Pinjam: ', Format::tanggal($peminjaman->getTanggalPinjam()), "\n";
echo 'Jatuh Tempo   : ', Format::tanggal($peminjaman->getTanggalJatuhTempo()), "\n";
echo 'Sisa Stok     : ', $buku->getStok(), "\n";
