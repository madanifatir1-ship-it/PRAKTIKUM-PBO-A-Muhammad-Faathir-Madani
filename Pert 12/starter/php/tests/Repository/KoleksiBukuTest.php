<?php

declare(strict_types=1);

namespace Perpustakaan\Tests\Repository;

use Perpustakaan\Denda\DendaMahasiswa;
use Perpustakaan\Exception\AnggotaTidakDitemukanException;
use Perpustakaan\Exception\BatasPinjamTerlampauiException;
use Perpustakaan\Exception\BukuSedangDipinjamException;
use Perpustakaan\Exception\BukuTidakDitemukanException;
use Perpustakaan\Model\Buku;
use Perpustakaan\Repository\InMemoryBookRepository;
use Perpustakaan\Repository\KoleksiBuku;
use Perpustakaan\Service\LibraryService;
use PHPUnit\Framework\TestCase;

final class KoleksiBukuTest extends TestCase
{
    public function testKoleksiBukuTambahCariDanFilterTersedia(): void
    {
        $koleksi = new KoleksiBuku();
        $this->assertTrue($koleksi->kosong());

        $b1 = new Buku('111', 'Laskar Pelangi', 'Andrea Hirata', 1);
        $b2 = new Buku('222', 'Bumi Manusia', 'Pramoedya', 0);
        $koleksi->tambah($b1);
        $koleksi->tambah($b2);

        $this->assertCount(2, $koleksi);
        $this->assertFalse($koleksi->kosong());
        $this->assertSame($b1, $koleksi->cariByIsbn('111'));
        $this->assertNull($koleksi->cariByIsbn('999'));
        $this->assertCount(1, $koleksi->tersedia());
    }

    public function testInMemoryBookRepositorySimpanCariDanKurangiStok(): void
    {
        $repo = new InMemoryBookRepository();
        $buku = new Buku('978-1', 'Clean Code', 'Robert C. Martin', 2);
        $repo->simpan($buku);

        $ditemukan = $repo->cariByIsbn('978-1');
        $this->assertNotNull($ditemukan);
        $this->assertEquals('Clean Code', $ditemukan->getJudul());
        $this->assertCount(1, $repo->cariSemua());

        $repo->kurangiStok($ditemukan);
        $this->assertEquals(1, $ditemukan->getJumlahEksemplar());
    }

    public function testExceptionPinjamAnggotaTidakAda(): void
    {
        $service = new LibraryService(new InMemoryBookRepository(), new DendaMahasiswa());
        $this->expectException(AnggotaTidakDitemukanException::class);
        $service->pinjam('ANG-NGASAL', '978-1');
    }

    public function testExceptionPinjamValidasiBisnis(): void
    {
        $service = new LibraryService(new InMemoryBookRepository(), new DendaMahasiswa());
        $service->daftarkanAnggota('A-001');
        $service->daftarkanAnggota('A-002');
        $service->tambahBuku('111', 'Laskar Pelangi', 'Andrea Hirata', 1);
        $service->tambahBuku('222', 'Bumi Manusia', 'Pramoedya', 5);

        try {
            $service->pinjam('A-001', '999');
            $this->fail('Seharusnya melempar BukuTidakDitemukanException');
        } catch (BukuTidakDitemukanException) {
            $this->assertTrue(true);
        }

        $service->pinjam('A-001', '111');
        try {
            $service->pinjam('A-002', '111');
            $this->fail('Seharusnya melempar BukuSedangDipinjamException');
        } catch (BukuSedangDipinjamException) {
            $this->assertTrue(true);
        }

        $service->pinjam('A-001', '222');
        $service->pinjam('A-001', '222');
        $this->expectException(BatasPinjamTerlampauiException::class);
        $service->pinjam('A-001', '222');
    }
}
