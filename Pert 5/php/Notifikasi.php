<?php
declare(strict_types=1);

/**
 * Langkah 6 — latihan mandiri.
 *
 * Buat hierarki Notifikasi dengan tiga turunan: Email, SMS, WhatsApp.
 * Lalu lengkapi kirimSemua() TANPA satu pun pemeriksaan tipe.
 */

// TODO 1: Bikin kontrak notifikasi yang nyimpen tujuan dan ngasih tahu nama salurannya.
abstract class Notifikasi
{
    public function __construct(public readonly string $tujuan) {}

    abstract public function kirim(string $pesan): void;

    public function saluran(): string
    {
        return static::class;
    }
}

// TODO 2: Tiap saluran punya format kirim yang beda, tapi kontraknya tetap sama.
class Email extends Notifikasi
{
    public function kirim(string $pesan): void
    {
        printf("[Email ke %s] %s%s", $this->tujuan, $pesan, PHP_EOL);
    }
}

class SMS extends Notifikasi
{
    public function kirim(string $pesan): void
    {
        printf("[SMS ke %s] %s%s", $this->tujuan, $pesan, PHP_EOL);
    }
}

class WhatsApp extends Notifikasi
{
    public function kirim(string $pesan): void
    {
        printf("[WhatsApp ke %s] %s%s", $this->tujuan, $pesan, PHP_EOL);
    }
}

/**
 * TODO 3: Kirim pesan ke semua notifikasi; biar tiap objek yang menentukan caranya.
 *
 * ATURAN: tidak boleh ada instanceof, tidak boleh ada match/switch
 *         atas jenis notifikasi. Kalau Anda merasa membutuhkannya,
 *         berarti hierarki Anda belum benar.
 *
 * @param Notifikasi[] $daftar
 */
function kirimSemua(array $daftar, string $pesan): void
{
    foreach ($daftar as $notifikasi) {
        $notifikasi->kirim($pesan);
    }
}

// TODO 4: Coba kirim pesan yang sama ke tiga saluran berbeda.
kirimSemua([
    new Email('ani@univpancasila.ac.id'),
    new SMS('081234567890'),
    new WhatsApp('081234567890'),
], 'Buku yang Anda pesan sudah tersedia.');
