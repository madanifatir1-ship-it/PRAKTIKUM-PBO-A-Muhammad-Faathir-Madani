<?php
declare(strict_types=1);

namespace Perpustakaan\Exception;

final class BukuTidakDitemukanException extends PerpustakaanException
{
    public function __construct(private string $isbn)
    {
        parent::__construct("Buku dengan ISBN [$isbn] tidak ditemukan");
    }

    public function getIsbn(): string
    {
        return $this->isbn;
    }
}
