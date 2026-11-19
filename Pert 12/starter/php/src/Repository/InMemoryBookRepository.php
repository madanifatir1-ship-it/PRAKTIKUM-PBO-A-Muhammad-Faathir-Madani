<?php

declare(strict_types=1);

namespace Perpustakaan\Repository;

use Perpustakaan\Model\Buku;

final class InMemoryBookRepository implements BookRepository
{
    private KoleksiBuku $koleksi;

    public function __construct(?KoleksiBuku $koleksi = null)
    {
        $this->koleksi = $koleksi ?? new KoleksiBuku();
    }

    public function simpan(Buku $buku): void
    {
        $this->koleksi->tambah($buku);
    }

    public function cariByIsbn(string $isbn): ?Buku
    {
        return $this->koleksi->cariByIsbn($isbn);
    }

    /** @return Buku[] */
    public function cariSemua(): array
    {
        return $this->koleksi->semua();
    }

    public function kurangiStok(Buku $buku): void
    {
        $buku->kurangiStok();
    }
}
