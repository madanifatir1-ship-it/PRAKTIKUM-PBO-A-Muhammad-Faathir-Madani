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
        if ($this->jariJari <= 0) {
            throw new InvalidArgumentException('Jari-jari Lingkaran harus > 0');
        }
    }

    public function luas(): float
    {
        return M_PI * $this->jariJari * $this->jariJari;
    }

    public function keliling(): float
    {
        return 2 * M_PI * $this->jariJari;
    }

    public function getJariJari(): float { return $this->jariJari; }
}

class Persegi extends BangunDatar
{
    public function __construct(private readonly float $sisi)
    {
        parent::__construct('Persegi');
        if ($this->sisi <= 0) {
            throw new InvalidArgumentException('Sisi Persegi harus > 0');
        }
    }

    public function luas(): float
    {
        return $this->sisi * $this->sisi;
    }

    public function keliling(): float
    {
        return 4 * $this->sisi;
    }
}

class Segitiga extends BangunDatar
{
    public function __construct(
        private readonly float $sisi1,
        private readonly float $sisi2,
        private readonly float $sisi3
    ) {
        parent::__construct('Segitiga');

        if ($this->sisi1 <= 0 || $this->sisi2 <= 0 || $this->sisi3 <= 0) {
            throw new InvalidArgumentException('Semua sisi Segitiga harus > 0');
        }

        $a = $this->sisi1; $b = $this->sisi2; $c = $this->sisi3;
        if ($a + $b <= $c || $a + $c <= $b || $b + $c <= $a) {
            throw new InvalidArgumentException('Ketiga sisi tidak membentuk segitiga');
        }
    }

    public function luas(): float
    {
        $a = $this->sisi1; $b = $this->sisi2; $c = $this->sisi3;
        $s = ($a + $b + $c) / 2.0;
        return sqrt($s * ($s - $a) * ($s - $b) * ($s - $c));
    }

    public function keliling(): float
    {
        return $this->sisi1 + $this->sisi2 + $this->sisi3;
    }
}

class Trapesium extends BangunDatar
{
    public function __construct(
        private readonly float $sisiAtas,
        private readonly float $sisiBawah,
        private readonly float $tinggi,
        private readonly float $sisiKiri,
        private readonly float $sisiKanan
    ) {
        parent::__construct('Trapesium');

        if ($this->sisiAtas <= 0 || $this->sisiBawah <= 0 || $this->tinggi <= 0 || $this->sisiKiri <= 0 || $this->sisiKanan <= 0) {
            throw new InvalidArgumentException('Semua sisi Trapesium harus > 0');
        }
    }

    public function luas(): float
    {
        return (($this->sisiAtas + $this->sisiBawah) / 2.0) * $this->tinggi;
    }

    public function keliling(): float
    {
        return $this->sisiAtas + $this->sisiBawah + $this->sisiKiri + $this->sisiKanan;
    }
}
