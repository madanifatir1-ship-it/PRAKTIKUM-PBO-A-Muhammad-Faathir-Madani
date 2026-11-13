<?php
declare(strict_types=1);

namespace Perpustakaan\Tests\Repository;

use PHPUnit\Framework\TestCase;
use Perpustakaan\Exception\AnggotaTidakDitemukanException;
use Perpustakaan\Exception\BatasPinjamTerlampauiException;
use Perpustakaan\Exception\BukuSedangDipinjamException;
use Perpustakaan\Exception\BukuTidakDitemukanException;
use Perpustakaan\Model\Buku;
use Perpustakaan\Repository\KoleksiBuku;
use Perpustakaan\Service\LibraryService;

final class KoleksiBukuTest extends TestCase
{
    private KoleksiBuku $koleksi;

    protected function setUp(): void
    {
        $this->koleksi = new KoleksiBuku();
    }

    public function testItemYangDisimpanDapatDitemukanKembali(): void
    {
        $buku = new Buku('978-1', 'PBO', 'Penulis A', 2);
        $this->koleksi->simpan($buku);
        $this->assertNotNull($this->koleksi->cariByIsbn('978-1'));
        $this->assertSame('PBO', $this->koleksi->cariByIsbn('978-1')?->getJudul());
    }

    public function testMencariIsbnYangTidakAdaMengembalikanNull(): void
    {
        $this->assertNull($this->koleksi->cariByIsbn('tidak-ada'));
    }

    public function testKoleksiBaruBerisiNolItem(): void
    {
        $this->assertCount(0, $this->koleksi);
    }

    public function testFilterHanyaMengembalikanYangLolosPredikat(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'Ada Stok', 'Penulis A', 2));
        $this->koleksi->simpan(new Buku('978-2', 'Habis', 'Penulis B', 0));
        $tersedia = $this->koleksi->filter(
            static fn (Buku $buku): bool => $buku->tersedia(),
        );
        $this->assertCount(1, $tersedia);
        $this->assertSame('978-1', $tersedia->semua()[0]->getNomorInduk());
    }

    public function testJumlahkanMenghitungTotalDenganBenar(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'PBO', 'Penulis A', 2));
        $this->koleksi->simpan(new Buku('978-2', 'Java', 'Penulis B', 3));
        $this->assertSame(5.0, $this->koleksi->jumlahkan(
            static fn (Buku $buku): int => $buku->getJumlahEksampelar(),
        ));
    }

    public function testSimpanDenganNomorIndukSamaMenimpaBukuLama(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'Judul Lama', 'Penulis A', 1));
        $this->koleksi->simpan(new Buku('978-1', 'Judul Baru', 'Penulis B', 2));
        $this->assertCount(1, $this->koleksi);
        $this->assertSame('Judul Baru', $this->koleksi->cariByIsbn('978-1')?->getJudul());
    }

    public function testMappingMenghasilkanBentukDataYangDiminta(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'PBO', 'Penulis A', 1));
        $this->koleksi->simpan(new Buku('978-2', 'Java', 'Penulis B', 2));
        $hasil = $this->koleksi->map(
            static fn (Buku $buku): array => [
                'isbn' => $buku->getNomorInduk(),
                'judul' => $buku->getJudul(),
            ],
        );
        $this->assertSame([
            ['isbn' => '978-1', 'judul' => 'PBO'],
            ['isbn' => '978-2', 'judul' => 'Java'],
        ], $hasil);
    }

    public function testMengelompokkanBukuBerdasarkanPenulis(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'PBO', 'Penulis A', 1));
        $this->koleksi->simpan(new Buku('978-2', 'Java', 'Penulis A', 2));
        $this->koleksi->simpan(new Buku('978-3', 'PHP', 'Penulis B', 3));
        $kelompok = $this->koleksi->kelompokkan(
            static fn (Buku $buku): string => $buku->getPenulis(),
        );
        $this->assertCount(2, $kelompok);
        $this->assertCount(2, $kelompok['Penulis A']);
        $this->assertCount(1, $kelompok['Penulis B']);
    }

    public function testPinjamIsbnTidakTerdaftarMelemparException(): void
    {
        $service = new LibraryService($this->koleksi);
        $service->daftarkanAnggota('A001');
        $this->expectException(BukuTidakDitemukanException::class);
        $service->pinjam('A001', 'ISBN-X');
    }

    public function testPinjamOlehAnggotaTidakTerdaftarMelemparException(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'PBO', 'Penulis A', 1));
        $service = new LibraryService($this->koleksi);
        $this->expectException(AnggotaTidakDitemukanException::class);
        $service->pinjam('A-X', '978-1');
    }

    public function testPinjamBukuStokHabisMelemparException(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'PBO', 'Penulis A', 0));
        $service = new LibraryService($this->koleksi);
        $service->daftarkanAnggota('A001');
        $this->expectException(BukuSedangDipinjamException::class);
        $service->pinjam('A001', '978-1');
    }

    public function testPinjamMelebihiBatasAnggotaMelemparException(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'PBO', 'Penulis A', 4));
        $service = new LibraryService($this->koleksi);
        $service->daftarkanAnggota('A001');
        $service->pinjam('A001', '978-1');
        $service->pinjam('A001', '978-1');
        $service->pinjam('A001', '978-1');
        $this->expectException(BatasPinjamTerlampauiException::class);
        $service->pinjam('A001', '978-1');
    }
}
