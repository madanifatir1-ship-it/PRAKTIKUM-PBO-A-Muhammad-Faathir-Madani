<?php
declare(strict_types=1);

/**
 * Sesi 4 — hierarki pegawai (PHP).
 * Seluruh hierarki ditaruh dalam satu berkas agar mudah dibaca berdampingan
 * dengan versi Java. Mulai sesi 9, satu kelas = satu berkas.
 */
abstract class Pegawai
{
    public function __construct(
        protected readonly string $nip,
        protected readonly string $nama,
        protected readonly float  $gajiPokok,
    ) {
        // TODO 1: Selesai — tolak gaji pokok negatif sebelum menyimpan data pegawai.
        if ($this->gajiPokok < 0) {
            throw new InvalidArgumentException('Gaji pokok tidak boleh negatif.');
        }
    }

    // TODO 2: Selesai — kembalikan gaji pokok sebagai perhitungan gaji dasar.
    public function hitungGaji(): float
    {
        return $this->gajiPokok;
    }

    abstract public function jenis(): string;

    public function getNama(): string { return $this->nama; }
    public function getNip(): string  { return $this->nip; }

    public function __toString(): string
    {
        return sprintf('%-14s %-9s %-20s Rp%s',
            $this->nip, $this->jenis(), $this->nama,
            number_format($this->hitungGaji(), 2, ',', '.'));
    }
}

class PegawaiTetap extends Pegawai
{
    protected const TUNJANGAN_PER_TAHUN = 0.02;
    protected const TUNJANGAN_MAKSIMUM  = 0.40;

    public function __construct(
        string $nip, string $nama, float $gajiPokok,
        protected readonly int $masaKerjaTahun,
    ) {
        // TODO 3: Selesai — panggil constructor induk sebelum inisialisasi properti turunan.
        parent::__construct($nip, $nama, $gajiPokok);
        if ($this->masaKerjaTahun < 0) {
            throw new InvalidArgumentException('Masa kerja tidak boleh negatif.');
        }
    }

    // TODO 4: Selesai — tambahkan tunjangan masa kerja ke gaji dasar dari kelas induk.
    public function hitungGaji(): float
    {
        $persentaseTunjangan = min(
            $this->masaKerjaTahun * self::TUNJANGAN_PER_TAHUN,
            self::TUNJANGAN_MAKSIMUM
        );
        return parent::hitungGaji() * (1 + $persentaseTunjangan);
    }

    public function jenis(): string { return 'TETAP'; }
}

class PegawaiKontrak extends Pegawai
{
    public function __construct(
        string $nip, string $nama, float $gajiPokok,
        private readonly int $bulanKontrak,
    ) {
        parent::__construct($nip, $nama, $gajiPokok);
        if ($this->bulanKontrak < 0) {
            throw new InvalidArgumentException('Bulan kontrak tidak boleh negatif.');
        }
    }

    public function jenis(): string { return 'KONTRAK'; }

    public function getBulanKontrak(): int { return $this->bulanKontrak; }
}

// TODO 5: Selesai — buat dosen sebagai pegawai tetap dengan jabatan dan tunjangan fungsional.
class Dosen extends PegawaiTetap
{
    public function __construct(
        string $nip,
        string $nama,
        float $gajiPokok,
        int $masaKerjaTahun,
        private readonly string $jabatanFungsional,
        private readonly float $tunjanganFungsional,
    ) {
        parent::__construct($nip, $nama, $gajiPokok, $masaKerjaTahun);
        if ($this->tunjanganFungsional < 0) {
            throw new InvalidArgumentException('Tunjangan fungsional tidak boleh negatif.');
        }
    }

    public function hitungGaji(): float
    {
        return parent::hitungGaji() + $this->tunjanganFungsional;
    }

    public function jenis(): string { return 'DOSEN'; }

    public function getJabatanFungsional(): string { return $this->jabatanFungsional; }
    public function getTunjanganFungsional(): float { return $this->tunjanganFungsional; }
}

// TODO 6: Selesai — hitung gaji pegawai harian berdasarkan tarif harian dan hari kerja.
class PegawaiHarian extends Pegawai
{
    public function __construct(
        string $nip,
        string $nama,
        private readonly float $gajiPerHari,
        private readonly int $hariKerja,
    ) {
        parent::__construct($nip, $nama, $gajiPerHari);
        if ($this->hariKerja < 0) {
            throw new InvalidArgumentException('Hari kerja tidak boleh negatif.');
        }
    }

    public function hitungGaji(): float
    {
        return $this->gajiPerHari * $this->hariKerja;
    }

    public function jenis(): string { return 'HARIAN'; }

    public function getGajiPerHari(): float { return $this->gajiPerHari; }
    public function getHariKerja(): int { return $this->hariKerja; }
}

// TODO 7: Selesai — tambahkan pegawai paruh waktu dengan tarif per jam.
class PegawaiParuhWaktu extends Pegawai
{
    public function __construct(
        string $nip,
        string $nama,
        private readonly float $gajiPerJam,
        private readonly int $jamKerja,
    ) {
        parent::__construct($nip, $nama, $gajiPerJam);
        if ($this->jamKerja < 0) {
            throw new InvalidArgumentException('Jam kerja tidak boleh negatif.');
        }
    }

    public function hitungGaji(): float
    {
        return $this->gajiPerJam * $this->jamKerja;
    }

    public function jenis(): string { return 'PARUH WAKTU'; }

    public function getGajiPerJam(): float { return $this->gajiPerJam; }
    public function getJamKerja(): int { return $this->jamKerja; }
}
