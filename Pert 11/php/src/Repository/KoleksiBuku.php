<?php
declare(strict_types=1);

namespace Perpustakaan\Repository;

use ArrayIterator;
use Countable;
use IteratorAggregate;
use Perpustakaan\Model\Buku;

final class KoleksiBuku implements IteratorAggregate, Countable
{
    /** @var array<string, Buku> */
    private array $items = [];

    /** @param Buku[] $buku */
    public function __construct(array $buku = [])
    {
        foreach ($buku as $item) {
            $this->simpan($item);
        }
    }

    public function simpan(Buku $b): void
    {
        $this->items[$b->getNomorInduk()] = $b;
    }

    public function cariByIsbn(string $isbn): ?Buku
    {
        return $this->items[$isbn] ?? null;
    }

    /** @return Buku[] */
    public function semua(): array
    {
        return array_values($this->items);
    }

    public function filter(callable $predikat): self
    {
        return new self(array_values(array_filter($this->items, $predikat)));
    }

    /** @return mixed[] */
    public function map(callable $f): array
    {
        return array_map($f, array_values($this->items));
    }

    public function jumlahkan(callable $f): float
    {
        return array_reduce(
            $this->items,
            function (float $carry, Buku $item) use ($f): float {
                return $carry + $f($item);
            },
            0.0,
        );
    }

    /** @return array<string, Buku[]> */
    public function kelompokkan(callable $kunci): array
    {
        $hasil = [];
        foreach ($this->items as $buku) {
            $hasil[$kunci($buku)][] = $buku;
        }
        return $hasil;
    }

    public function getIterator(): ArrayIterator
    {
        return new ArrayIterator(array_values($this->items));
    }

    public function count(): int
    {
        return count($this->items);
    }
}
