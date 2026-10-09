<?php
declare(strict_types=1);

/**
 * Sesi 3 — PHP tidak punya constructor overloading.
 * Padanannya: default parameter + named constructor (static factory).
 */
class RekeningBank
{
    // TODO 1: Ganti angka ajaib menjadi konstanta bernama.
    private const BUNGA_TAHUNAN = 0.025;
    private const BIAYA_ADMIN = 5000.0;
    private const BATAS_PENARIKAN = 5000000.0;

    // TODO 2: Deklarasikan properti statis penghitung jumlah rekening.
    private static int $jumlahRekening = 0;

    private float $saldo;
    private readonly string $nomor;
    private string $pemilik;

    /**
     * Default parameter menggantikan constructor overloading.
     */
    public function __construct(
        string $nomor,
        string $pemilik,
        float $saldoAwal = 0,
    ) {
        // TODO 3: Tolak nomor/pemilik kosong dan saldo awal negatif atau non-finite.
        if (trim($nomor) === '' || trim($pemilik) === '') {
            throw new InvalidArgumentException('Nomor rekening dan nama pemilik wajib diisi.');
        }
        if (!is_finite($saldoAwal) || $saldoAwal < 0) {
            throw new InvalidArgumentException('Saldo awal harus finite dan tidak boleh negatif.');
        }

        $this->nomor = trim($nomor);
        $this->pemilik = trim($pemilik);
        $this->saldo = $saldoAwal;

        // TODO 4: Naikkan penghitung hanya setelah rekening berhasil divalidasi.
        self::$jumlahRekening++;
    }

    // TODO 5: Buat rekening pelajar bersaldo nol dengan new static() untuk late static binding.
    public static function rekeningPelajar(string $nomor, string $pemilik): static
    {
        return new static($nomor, $pemilik, 0);
    }

    public function setor(float $jumlah): void
    {
        // TODO 6: Validasi nominal setor dan pastikan hasil penjumlahan tetap finite.
        self::validasiJumlahPositif($jumlah);
        $saldoBaru = $this->saldo + $jumlah;
        if (!is_finite($saldoBaru)) {
            throw new InvalidArgumentException('Saldo setelah setor harus finite.');
        }

        $this->saldo = $saldoBaru;
    }

    public function tarik(float $jumlah): void
    {
        // TODO 7: Tolak nominal tidak valid, melebihi saldo, atau melebihi batas sekali tarik.
        self::validasiJumlahPositif($jumlah);
        if ($jumlah > self::BATAS_PENARIKAN) {
            throw new InvalidArgumentException('Penarikan melebihi batas sekali tarik.');
        }
        if ($jumlah > $this->saldo) {
            throw new LogicException('Saldo tidak mencukupi.');
        }

        $this->saldo -= $jumlah;
    }

    public function potongBiayaAdmin(): void
    {
        // TODO 8: Potong biaya admin jika saldo mencukupi; jika tidak, jangan lakukan apa pun.
        if ($this->saldo >= self::BIAYA_ADMIN) {
            $this->saldo -= self::BIAYA_ADMIN;
        }
    }

    public static function getJumlahRekening(): int
    {
        // TODO 9: Kembalikan jumlah rekening yang berhasil dibuat.
        return self::$jumlahRekening;
    }

    public static function bungaSetahun(float $pokok): float
    {
        // TODO 10: Validasi pokok dan hasil perhitungan bunga tahunan.
        if (!is_finite($pokok) || $pokok < 0) {
            throw new InvalidArgumentException('Pokok harus finite dan tidak boleh negatif.');
        }

        $bunga = $pokok * self::BUNGA_TAHUNAN;
        if (!is_finite($bunga)) {
            throw new InvalidArgumentException('Hasil perhitungan bunga harus finite.');
        }

        return $bunga;
    }

    private static function validasiJumlahPositif(float $jumlah): void
    {
        if (!is_finite($jumlah) || $jumlah <= 0) {
            throw new InvalidArgumentException('Jumlah harus finite dan lebih besar dari nol.');
        }
    }

    public function getSaldo(): float
    {
        return $this->saldo;
    }

    public function getNomor(): string
    {
        return $this->nomor;
    }

    public function getPemilik(): string
    {
        return $this->pemilik;
    }

    public function setPemilik(string $pemilik): void
    {
        if (trim($pemilik) === '') {
            throw new InvalidArgumentException('Nama pemilik tidak boleh kosong.');
        }

        $this->pemilik = trim($pemilik);
    }

    public function __toString(): string
    {
        return sprintf('Rekening[%s] %-14s Rp%s',
            $this->nomor, $this->pemilik, number_format($this->saldo, 2, ',', '.'));
    }
}
