<?php
declare(strict_types=1);

namespace Perpustakaan\Exception;

final class BukuSedangDipinjamException extends PerpustakaanException
{
    public function __construct(private string $isbn, private string $judul)
    {
        parent::__construct("Buku dengan ISBN [$isbn] berjudul \"$judul\" tidak tersedia");
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
