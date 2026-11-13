<?php
declare(strict_types=1);

namespace Perpustakaan\Exception;

final class AnggotaTidakDitemukanException extends PerpustakaanException
{
    public function __construct(private string $idAnggota)
    {
        parent::__construct("Anggota dengan ID [$idAnggota] tidak terdaftar");
    }

    public function getIdAnggota(): string
    {
        return $this->idAnggota;
    }
}
