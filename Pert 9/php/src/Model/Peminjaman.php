<?php

declare(strict_types=1);

namespace Perpustakaan\Model;

use Carbon\CarbonImmutable;

final class Peminjaman
{
    public function __construct(
        private Anggota $anggota,
        private Buku $buku,
        private CarbonImmutable $tanggalPinjam,
        private CarbonImmutable $tanggalJatuhTempo,
    ) {
    }

    public function getAnggota(): Anggota
    {
        return $this->anggota;
    }

    public function getBuku(): Buku
    {
        return $this->buku;
    }

    public function getTanggalPinjam(): CarbonImmutable
    {
        return $this->tanggalPinjam;
    }

    public function getTanggalJatuhTempo(): CarbonImmutable
    {
        return $this->tanggalJatuhTempo;
    }
}
