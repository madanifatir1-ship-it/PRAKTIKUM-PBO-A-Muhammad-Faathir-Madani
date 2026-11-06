<?php

declare(strict_types=1);

namespace Id\Ac\Univpancasila\Perpustakaan\Exception;

final class BatasPinjamTerlampauiException extends PerpustakaanException
{
    public readonly string $idAnggota;
    public readonly int $batas;

    public function __construct(string $idAnggota, int $batas)
    {
        $this->idAnggota = $idAnggota;
        $this->batas = $batas;
        parent::__construct("Anggota dengan ID [$idAnggota] telah mencapai batas pinjam [$batas]");
    }

    public function getIdAnggota(): string
    {
        return $this->idAnggota;
    }

    public function getBatas(): int
    {
        return $this->batas;
    }
}
