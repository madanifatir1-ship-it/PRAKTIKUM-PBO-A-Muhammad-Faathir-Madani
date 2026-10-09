<?php
declare(strict_types=1);

abstract class BangunDatar
{
    public function __construct(private readonly string $nama) {}

    abstract public function luas(): float;
    abstract public function keliling(): float;

    public function getNama(): string { return $this->nama; }

    public function __toString(): string
    {
        return sprintf('%-12s luas=%10.2f  keliling=%10.2f',
            $this->nama, $this->luas(), $this->keliling());
    }
}

class Lingkaran extends BangunDatar
{
    public function __construct(private readonly float $jariJari)
    {
        parent::__construct('Lingkaran');
        // TODO 1: Pastikan jari-jarinya positif biar lingkarannya valid.
        if ($this->jariJari <= 0) {
            throw new InvalidArgumentException('Jari-jari harus lebih dari 0.');
        }
    }

    // TODO 2: Hitung luas dan keliling pakai M_PI, jangan pakai angka 3.14.
    public function luas(): float     { return M_PI * $this->jariJari * $this->jariJari; }
    public function keliling(): float { return 2 * M_PI * $this->jariJari; }

    public function getJariJari(): float { return $this->jariJari; }
}

class Persegi extends BangunDatar
{
    public function __construct(private readonly float $sisi)
    {
        parent::__construct('Persegi');
        // TODO 3: Pastikan panjang sisinya positif sebelum dipakai.
        if ($this->sisi <= 0) {
            throw new InvalidArgumentException('Sisi harus lebih dari 0.');
        }
    }

    // TODO 4: Hitung luas dan keliling persegi dari panjang sisinya.
    public function luas(): float     { return $this->sisi * $this->sisi; }
    public function keliling(): float { return 4 * $this->sisi; }
}

// TODO 5: Segitiga pakai tiga sisi dan rumus Heron; sisi yang nggak valid langsung ditolak.
class Segitiga extends BangunDatar
{
    public function __construct(
        private readonly float $a,
        private readonly float $b,
        private readonly float $c
    ) {
        parent::__construct('Segitiga');
        if ($this->a <= 0 || $this->b <= 0 || $this->c <= 0) {
            throw new InvalidArgumentException('Semua sisi segitiga harus lebih dari 0.');
        }
        if ($this->a + $this->b <= $this->c
            || $this->a + $this->c <= $this->b
            || $this->b + $this->c <= $this->a) {
            throw new InvalidArgumentException('Panjang sisi tidak memenuhi ketaksamaan segitiga.');
        }
    }

    public function luas(): float
    {
        $setengahKeliling = $this->keliling() / 2;
        return sqrt(
            $setengahKeliling
            * ($setengahKeliling - $this->a)
            * ($setengahKeliling - $this->b)
            * ($setengahKeliling - $this->c)
        );
    }

    public function keliling(): float
    {
        return $this->a + $this->b + $this->c;
    }
}

// TODO 6: Trapesium menghitung luas dari dua alas dan tinggi, plus semua sisinya untuk keliling.
class Trapesium extends BangunDatar
{
    public function __construct(
        private readonly float $alasAtas,
        private readonly float $alasBawah,
        private readonly float $sisiMiring1,
        private readonly float $sisiMiring2,
        private readonly float $tinggi
    ) {
        parent::__construct('Trapesium');
        if ($this->alasAtas <= 0 || $this->alasBawah <= 0
            || $this->sisiMiring1 <= 0 || $this->sisiMiring2 <= 0
            || $this->tinggi <= 0) {
            throw new InvalidArgumentException('Semua ukuran trapesium harus lebih dari 0.');
        }
    }

    public function luas(): float
    {
        return ($this->alasAtas + $this->alasBawah) * $this->tinggi / 2;
    }

    public function keliling(): float
    {
        return $this->alasAtas + $this->alasBawah + $this->sisiMiring1 + $this->sisiMiring2;
    }
}
