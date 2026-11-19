<?php

declare(strict_types=1);

namespace Perpustakaan\Exception;

final class BukuSedangDipinjamException extends PerpustakaanException
{
    public function __construct(string $isbn, string $judul = '')
    {
        $label = $judul !== '' ? "'{$judul}' (ISBN: {$isbn})" : "dengan ISBN '{$isbn}'";
        parent::__construct("Buku {$label} sedang dipinjam semua / stok habis.");
    }
}
