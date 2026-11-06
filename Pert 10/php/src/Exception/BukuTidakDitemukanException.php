<?php

declare(strict_types=1);

namespace Id\Ac\Univpancasila\Perpustakaan\Exception;

final class BukuTidakDitemukanException extends PerpustakaanException
{
    public readonly string $isbn;

    public function __construct(string $isbn)
    {
        $this->isbn = $isbn;
        parent::__construct("Buku dengan ISBN [$isbn] tidak ditemukan");
    }

    public function getIsbn(): string
    {
        return $this->isbn;
    }
}
