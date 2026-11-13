<?php
declare(strict_types=1);

namespace Perpustakaan\Exception;

final class BatasPinjamTerlampauiException extends PerpustakaanException
{
    public function __construct(private string $idAnggota, private int $batas)
    {
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
