<?php
declare(strict_types=1);

require __DIR__ . '/vendor/autoload.php';

use Perpustakaan\Model\Buku;
use Perpustakaan\Repository\KoleksiBuku;
use Perpustakaan\Service\LibraryService;
use Perpustakaan\Service\PerpustakaanService;

$koleksi = new KoleksiBuku([
    new Buku('BKU-001', 'Pemrograman Berorientasi Objek', 'S. Suryana', 3),
    new Buku('BKU-002', 'Struktur Data', 'A. Nugroho', 0),
    new Buku('BKU-003', 'Pemrograman Java', 'S. Suryana', 2),
]);
$library = new LibraryService($koleksi);
$library->daftarkanAnggota('A001');
$library->daftarkanAnggota('A002');
$service = new PerpustakaanService($library);

try {
    $berjalan = true;
    while ($berjalan) {
        echo PHP_EOL, "=== SISTEM PERPUSTAKAAN ===", PHP_EOL;
        echo "1. Pinjam buku", PHP_EOL;
        echo "2. Lihat semua buku", PHP_EOL;
        echo "3. Lihat anggota demo", PHP_EOL;
        echo "4. Lihat laporan fungsional", PHP_EOL;
        echo "0. Keluar", PHP_EOL;
        echo 'Pilihan: ';
        $pilihan = trim((string) fgets(STDIN));
        switch ($pilihan) {
            case '1':
                echo 'ID anggota: ';
                $anggota = trim((string) fgets(STDIN));
                echo 'ISBN/nomor induk buku: ';
                $isbn = trim((string) fgets(STDIN));
                $library->pinjam($anggota, $isbn);
                echo "Peminjaman berhasil.", PHP_EOL;
                break;
            case '2':
                foreach ($library->getSemuaBuku() as $buku) {
                    echo $buku->getNomorInduk(), ' - ', $buku->getJudul(),
                        ' (', $buku->getPenulis(), '), tersedia: ',
                        $buku->getJumlahEksampelar(), PHP_EOL;
                }
                break;
            case '3':
                echo "Anggota demo: A001, A002", PHP_EOL;
                break;
            case '4':
                $service->tampilkanLaporan();
                break;
            case '0':
                $berjalan = false;
                break;
            default:
                echo "Pilihan menu tidak tersedia.", PHP_EOL;
        }
    }
} catch (Throwable $e) {
    fwrite(STDERR, $e->getMessage() . PHP_EOL);
} finally {
    $service->eksporLaporan(__DIR__ . '/laporan_buku.txt');
    echo "Laporan diekspor ke laporan_buku.txt", PHP_EOL;
}
