<?php

namespace App\Repositories;

use App\Models\Buku;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use Illuminate\Support\Collection;

class InMemoryBookRepository implements BookRepository
{
    /** @var array<int, Buku> */
    private array $items = [];

    public function __construct()
    {
        $b1 = new Buku([
            'isbn' => '978-602-0001-01-1',
            'nama_penulis' => 'Andrea Hirata',
            'title' => 'Laskar Pelangi (In-Memory)',
            'jumlah_eksamplar' => 4,
            'bahasa' => 'id',
            'status' => 'baik',
        ]);
        $b1->id = 1;

        $b2 = new Buku([
            'isbn' => '978-013-2350-88-4',
            'nama_penulis' => 'Robert C. Martin',
            'title' => 'Clean Code (In-Memory)',
            'jumlah_eksamplar' => 2,
            'bahasa' => 'en',
            'status' => 'baik',
        ]);
        $b2->id = 2;

        $this->items = [1 => $b1, 2 => $b2];
    }

    public function all(?string $bahasa = null): Collection
    {
        $collection = collect(array_values($this->items));

        if (!empty($bahasa)) {
            return $collection->where('bahasa', $bahasa)->values();
        }

        return $collection;
    }

    public function findOrFail(string $id): Buku
    {
        $key = (int) $id;
        if (!isset($this->items[$key])) {
            throw (new ModelNotFoundException)->setModel(Buku::class, [$id]);
        }

        return $this->items[$key];
    }

    public function create(array $data): Buku
    {
        $nextId = empty($this->items) ? 1 : max(array_keys($this->items)) + 1;
        $buku = new Buku(array_merge(['status' => 'baik'], $data));
        $buku->id = $nextId;
        $this->items[$nextId] = $buku;

        return $buku;
    }

    public function update(string $id, array $data): Buku
    {
        $buku = $this->findOrFail($id);
        $buku->fill($data);

        return $buku;
    }

    public function delete(string $id): void
    {
        $this->findOrFail($id);
        unset($this->items[(int) $id]);
    }
}
