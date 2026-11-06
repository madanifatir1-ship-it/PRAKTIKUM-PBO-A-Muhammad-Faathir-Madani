<?php
declare(strict_types=1);

/**
 * Sesi 10 — hierarki exception domain (PHP).
 *
 * PHP tidak mengenal checked exception; seluruh exception setara dengan
 * unchecked di Java. Karena itu anotasi @throws pada PHPDoc menjadi jauh
 * lebih penting: pembaca kode tidak dibantu kompilator.
 */

abstract class PerpustakaanException extends RuntimeException
{
}

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

final class BukuSedangDipinjamException extends PerpustakaanException
{
    public readonly string $isbn;
    public readonly string $judul;

    public function __construct(string $isbn, string $judul)
    {
        $this->isbn = $isbn;
        $this->judul = $judul;
        parent::__construct("Buku dengan ISBN [$isbn] berjudul \"$judul\" sedang habis/stok tidak tersedia");
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
