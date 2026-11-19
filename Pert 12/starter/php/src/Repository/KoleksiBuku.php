<?php

declare(strict_types=1);

namespace Perpustakaan\Repository;

use ArrayIterator;
use Countable;
use IteratorAggregate;
use Perpustakaan\Model\Buku;
use Traversable;

/**
 * @implements IteratorAggregate<string, Buku>
 */
final class KoleksiBuku implements Countable, IteratorAggregate
{
    /** @var array<string, Buku> */
    private array $items = [];

    public function tambah(Buku $buku): void
    {
        $this->items[$buku->getNomorInduk()] = $buku;
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

    /** @return Buku[] */
    public function tersedia(): array
    {
        return array_values(array_filter(
            $this->items,
            fn (Buku $buku): bool => $buku->tersedia()
        ));
    }

    public function count(): int
    {
        return count($this->items);
    }

    public function kosong(): bool
    {
        return $this->items === [];
    }

    /** @return Traversable<string, Buku> */
    public function getIterator(): Traversable
    {
        return new ArrayIterator($this->items);
    }
}
