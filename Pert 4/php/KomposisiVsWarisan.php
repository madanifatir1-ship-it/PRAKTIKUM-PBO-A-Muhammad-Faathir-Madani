<?php
declare(strict_types=1);

class Mesin
{
    public function nyalakan(): string
    {
        return 'Mesin menyala.';
    }
}

class SumberTenaga
{
    public function __construct(private string $nama)
    {
    }

    public function getNama(): string
    {
        return $this->nama;
    }

    public function setNama(string $nama): void
    {
        $this->nama = $nama;
    }
}

// TODO 1: Selesai — ubah pewarisan Mobil extends Mesin menjadi komposisi.
class Mobil
{
    public function __construct(
        private Mesin $mesin,
        private SumberTenaga $sumberTenaga,
    ) {
    }

    public function setSumberTenaga(SumberTenaga $sumberTenaga): void
    {
        $this->sumberTenaga = $sumberTenaga;
    }

    public function getSumberTenaga(): SumberTenaga
    {
        return $this->sumberTenaga;
    }

    public function berjalan(): string
    {
        return 'Mobil berjalan menggunakan ' . $this->sumberTenaga->getNama() . '. ' . $this->mesin->nyalakan();
    }
}

$mobil = new Mobil(new Mesin(), new SumberTenaga('bensin'));
echo $mobil->berjalan(), PHP_EOL;

// TODO 2: Selesai — buat Mobil memiliki SumberTenaga dan ganti sumber tenaga
//         saat program berjalan tanpa membuat objek Mobil baru.
$mobil->setSumberTenaga(new SumberTenaga('listrik'));
echo 'Sumber tenaga diganti menjadi ' . $mobil->getSumberTenaga()->getNama() . '.', PHP_EOL;
echo $mobil->berjalan(), PHP_EOL;
