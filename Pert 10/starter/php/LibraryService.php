<?php
declare(strict_types=1);

require_once __DIR__ . '/exceptions.php';

final class LibraryService
{
    public const BATAS_PINJAM = 3;

    /** @var array<string,int> */
    private array $stok = [];
    /** @var array<string,string> */
    private array $judul = [];
    /** @var array<string,int> */
    private array $pinjaman = [];

    public function tambahBuku(string $isbn, string $judulBuku, int $eksemplar): void
    {
        $isbn = trim($isbn);
        $this->validasiTeks($isbn, 'ISBN');
        $judulBuku = trim($judulBuku);
        $this->validasiTeks($judulBuku, 'Judul buku');
        if ($eksemplar < 0) {
            throw new InvalidArgumentException('Jumlah eksemplar tidak boleh negatif');
        }
        $this->stok[$isbn] = ($this->stok[$isbn] ?? 0) + $eksemplar;
        $this->judul[$isbn] = $judulBuku;
    }

    public function daftarkanAnggota(string $idAnggota): void
    {
        $idAnggota = trim($idAnggota);
        $this->validasiTeks($idAnggota, 'ID anggota');
        $this->pinjaman[$idAnggota] ??= 0;
    }

    public function pinjam(string $idAnggota, string $isbn): bool
    {
        $idAnggota = trim($idAnggota);
        $this->validasiTeks($idAnggota, 'ID anggota');
        $isbn = trim($isbn);
        $this->validasiTeks($isbn, 'ISBN');
        if (!array_key_exists($idAnggota, $this->pinjaman)) {
            throw new AnggotaTidakDitemukanException($idAnggota);
        }

        if (!array_key_exists($isbn, $this->stok)) {
            throw new BukuTidakDitemukanException($isbn);
        }

        if ($this->pinjaman[$idAnggota] >= self::BATAS_PINJAM) {
            throw new BatasPinjamTerlampauiException($idAnggota, self::BATAS_PINJAM);
        }

        if ($this->stok[$isbn] <= 0) {
            throw new BukuSedangDipinjamException($isbn, $this->judul[$isbn]);
        }

        $this->stok[$isbn]--;
        $this->pinjaman[$idAnggota]++;
        return true;
    }

    public function sisaEksemplar(string $isbn): int
    {
        return $this->stok[trim($isbn)] ?? 0;
    }

    public function eksporDaftarBuku(string $tujuan): void
    {
        $tujuan = trim($tujuan);
        $this->validasiTeks($tujuan, 'Lokasi file ekspor');
        $handle = fopen($tujuan, 'w');
        if ($handle === false) {
            throw new RuntimeException("Tidak dapat membuka berkas: $tujuan");
        }

        try {
            if (fwrite($handle, "ISBN\tJudul\tSisa Eksemplar\n") === false) {
                throw new RuntimeException('Gagal menulis header daftar buku');
            }
            foreach ($this->stok as $isbn => $jumlah) {
                $baris = $isbn . "\t" . ($this->judul[$isbn] ?? '') . "\t"
                    . $jumlah . "\n";
                if (fwrite($handle, $baris) === false) {
                    throw new RuntimeException("Gagal menulis data buku dengan ISBN [$isbn]");
                }
            }
        } finally {
            fclose($handle);
        }
    }

    private function validasiTeks(string $nilai, string $namaField): void
    {
        if ($nilai === '') {
            throw new InvalidArgumentException("$namaField tidak boleh kosong");
        }
    }
}
