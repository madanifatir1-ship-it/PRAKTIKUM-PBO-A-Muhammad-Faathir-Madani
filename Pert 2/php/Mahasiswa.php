<?php
declare(strict_types=1);

/**
 * Sesi 2 — enkapsulasi yang menjaga invariant (PHP).
 * Bandingkan baris demi baris dengan java/Mahasiswa.java.
 */
class Mahasiswa
{
    public const BOBOT_TUGAS = 0.30;
    public const BOBOT_UTS   = 0.30;
    public const BOBOT_UAS   = 0.40;

    private const NILAI_MIN = 0;
    private const NILAI_MAX = 100;

    // TODO 1: NIM readonly; komponen nilai dipromosikan dan hanya dapat diubah lewat setter tervalidasi.
    private readonly string $nim;

    public function __construct(
        ?string $nim,
        private readonly string $nama,
        private float $nilaiTugas,
        private float $nilaiUts,
        private float $nilaiUas,
    ) {
        // TODO 2: Tolak NIM null atau kosong dengan pesan yang menjelaskan kesalahannya.
        if ($nim === null || trim($nim) === '') {
            throw new InvalidArgumentException('NIM tidak boleh null atau kosong.');
        }

        // TODO 3: Validasi seluruh nilai sebelum menyimpan identitas.
        self::pastikanNilaiSah('nilai tugas', $nilaiTugas);
        self::pastikanNilaiSah('nilai UTS', $nilaiUts);
        self::pastikanNilaiSah('nilai UAS', $nilaiUas);
        $this->nim = $nim;
    }

    // TODO 4: Tolak nilai non-finite atau di luar rentang 0 sampai 100.
    private static function pastikanNilaiSah(string $namaKomponen, float $nilai): void
    {
        if (!is_finite($nilai) || $nilai < self::NILAI_MIN || $nilai > self::NILAI_MAX) {
            throw new InvalidArgumentException($namaKomponen . ' harus berada di antara 0 dan 100.');
        }
    }

    /** Hitung nilai akhir memakai konstanta bobot. */
    public function nilaiAkhir(): float
    {
        // TODO 5: Hitung nilai akhir berbobot.
        return $this->nilaiTugas * self::BOBOT_TUGAS
            + $this->nilaiUts * self::BOBOT_UTS
            + $this->nilaiUas * self::BOBOT_UAS;
    }

    // TODO 6: Kembalikan huruf mutu berdasarkan nilai akhir.
    public function hurufMutu(): string
    {
        return match (true) {
            $this->nilaiAkhir() >= 80 => 'A',
            $this->nilaiAkhir() >= 70 => 'B',
            $this->nilaiAkhir() >= 60 => 'C',
            $this->nilaiAkhir() >= 50 => 'D',
            default => 'E',
        };
    }

    // TODO 7: Sediakan getter identitas, setiap komponen nilai, dan nilai akhir; jangan sediakan setNim().
    public function getNim(): string  { return $this->nim; }
    public function getNama(): string { return $this->nama; }
    public function getNilaiTugas(): float { return $this->nilaiTugas; }
    public function getNilaiUts(): float { return $this->nilaiUts; }
    public function getNilaiUas(): float { return $this->nilaiUas; }
    public function getNilaiAkhir(): float { return $this->nilaiAkhir(); }

    public function setNilaiTugas(float $nilaiTugas): void
    {
        self::pastikanNilaiSah('nilai tugas', $nilaiTugas);
        $this->nilaiTugas = $nilaiTugas;
    }

    public function setNilaiUts(float $nilaiUts): void
    {
        self::pastikanNilaiSah('nilai UTS', $nilaiUts);
        $this->nilaiUts = $nilaiUts;
    }

    public function setNilaiUas(float $nilaiUas): void
    {
        self::pastikanNilaiSah('nilai UAS', $nilaiUas);
        $this->nilaiUas = $nilaiUas;
    }

    public function __toString(): string
    {
        return sprintf('%-10s %-18s akhir=%6.2f  mutu=%s',
            $this->nim, $this->nama, $this->nilaiAkhir(), $this->hurufMutu());
    }
}
