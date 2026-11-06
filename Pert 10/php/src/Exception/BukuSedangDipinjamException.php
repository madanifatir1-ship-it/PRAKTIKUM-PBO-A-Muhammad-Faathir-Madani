<?php

declare(strict_types=1);

namespace Id\Ac\Univpancasila\Perpustakaan\Exception;

final class BukuSedangDipinjamException extends PerpustakaanException
{
    public readonly string $isbn;
    public readonly string $judul;

    public function __construct(string $isbn, string $judul)
    {
        $this->isbn = $isbn;
        $this->judul = $judul;
        parent::__construct("Buku dengan ISBN [$isbn] berjudul \"$judul\" sedang habis/stok tidak tersedia");
    }

    public function getIsbn(): string
    {
        return $this->isbn;
    }

    public function getJudul(): string
    {
        return $this->judul;
    }
}
