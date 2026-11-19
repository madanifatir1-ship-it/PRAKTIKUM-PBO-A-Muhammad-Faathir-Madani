<?php

declare(strict_types=1);

namespace Perpustakaan\Exception;

final class AnggotaTidakDitemukanException extends PerpustakaanException
{
    public function __construct(string $idAnggota)
    {
        parent::__construct("Anggota dengan ID '{$idAnggota}' tidak terdaftar dalam sistem.");
    }
}
