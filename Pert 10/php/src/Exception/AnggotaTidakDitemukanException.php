<?php

declare(strict_types=1);

namespace Id\Ac\Univpancasila\Perpustakaan\Exception;

final class AnggotaTidakDitemukanException extends PerpustakaanException
{
    public readonly string $idAnggota;

    public function __construct(string $idAnggota)
    {
        $this->idAnggota = $idAnggota;
        parent::__construct("Anggota dengan ID [$idAnggota] tidak terdaftar");
    }

    public function getIdAnggota(): string
    {
        return $this->idAnggota;
    }
}
