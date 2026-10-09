<?php
declare(strict_types=1);

/**
 * Langkah 6 — latihan mandiri.
 *
 * Buat hierarki Notifikasi dengan tiga turunan: Email, SMS, WhatsApp.
 * Lalu lengkapi kirimSemua() TANPA satu pun pemeriksaan tipe.
 */

abstract class Notifikasi
{
    public function __construct(private readonly string $tujuan) {}

    public function getTujuan(): string { return $this->tujuan; }

    abstract public function saluran(): string;
    abstract public function kirim(string $pesan): void;
}

class Email extends Notifikasi
{
    public function saluran(): string { return 'Email'; }

    public function kirim(string $pesan): void
    {
        echo sprintf("%s ke %s: %s%s", $this->saluran(), $this->getTujuan(), $pesan, PHP_EOL);
    }
}

class SMS extends Notifikasi
{
    public function saluran(): string { return 'SMS'; }

    public function kirim(string $pesan): void
    {
        echo sprintf("%s ke %s: %s%s", $this->saluran(), $this->getTujuan(), $pesan, PHP_EOL);
    }
}

class WhatsApp extends Notifikasi
{
    public function saluran(): string { return 'WhatsApp'; }

    public function kirim(string $pesan): void
    {
        echo sprintf("%s ke %s: %s%s", $this->saluran(), $this->getTujuan(), $pesan, PHP_EOL);
    }
}

/**
 * @param Notifikasi[] $daftar
 */
function kirimSemua(array $daftar, string $pesan): void
{
    foreach ($daftar as $notifikasi) {
        $notifikasi->kirim($pesan);
    }
}

// Contoh pemanggilan:
kirimSemua([
     new Email('mfaathir4525100@univpancasila.ac.id'),
     new SMS('081234567890'),
     new WhatsApp('089505649485'),
 ], 'Buku yang Anda pesan sudah tersedia.');
