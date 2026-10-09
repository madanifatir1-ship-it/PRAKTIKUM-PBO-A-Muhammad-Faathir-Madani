<?php
declare(strict_types=1);

class Buku
{
    // TODO 1: Enkapsulasi data; identitas dan jumlah awal tidak berubah setelah objek dibuat.
    private readonly string $isbn;
    private readonly string $judul;
    private readonly string $penulis;
    private readonly int $jumlahEksemplar;
    private int $jumlahTersedia;

    // TODO 2: Tolak ISBN, judul, atau penulis kosong dan jumlah eksemplar negatif.
    public function __construct(
        ?string $isbn,
        ?string $judul,
        ?string $penulis,
        int $jumlahEksemplar,
    ) {
        if ($isbn === null || trim($isbn) === '') {
            throw new InvalidArgumentException('ISBN tidak boleh null atau kosong.');
        }
        if ($judul === null || trim($judul) === '') {
            throw new InvalidArgumentException('Judul tidak boleh null atau kosong.');
        }
        if ($penulis === null || trim($penulis) === '') {
            throw new InvalidArgumentException('Penulis tidak boleh null atau kosong.');
        }
        if ($jumlahEksemplar < 0) {
            throw new InvalidArgumentException('Jumlah eksemplar tidak boleh negatif.');
        }

        $this->isbn = $isbn;
        $this->judul = $judul;
        $this->penulis = $penulis;
        $this->jumlahEksemplar = $jumlahEksemplar;
        $this->jumlahTersedia = $this->jumlahEksemplar;
    }

    // TODO 3: Lengkapi getter untuk seluruh atribut.
    public function getIsbn(): string { return $this->isbn; }
    public function getJudul(): string { return $this->judul; }
    public function getPenulis(): string { return $this->penulis; }
    public function getJumlahEksemplar(): int { return $this->jumlahEksemplar; }
    public function getJumlahTersedia(): int { return $this->jumlahTersedia; }

    // TODO 4: Tolak peminjaman saat stok habis dan kurangi jumlah tersedia.
    public function pinjam(): void
    {
        if ($this->jumlahTersedia === 0) {
            throw new LogicException('Tidak ada eksemplar buku yang tersedia untuk dipinjam.');
        }
        $this->jumlahTersedia--;
    }

    // TODO 5: Validasi pengembalian dan tambah jumlah tersedia.
    public function kembalikan(): void
    {
        if ($this->jumlahTersedia === $this->jumlahEksemplar) {
            throw new LogicException('Tidak ada peminjaman yang perlu dikembalikan.');
        }
        $this->jumlahTersedia++;
    }
}