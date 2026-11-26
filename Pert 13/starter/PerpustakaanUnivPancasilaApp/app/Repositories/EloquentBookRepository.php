<?php

namespace App\Repositories;

use App\Models\Buku;
use Illuminate\Support\Collection;

class EloquentBookRepository implements BookRepository
{
    public function all(?string $bahasa = null): Collection
    {
        $query = Buku::latest();

        if (!empty($bahasa)) {
            $query->berbahasa($bahasa);
        }

        return $query->get();
    }

    public function findOrFail(string $id): Buku
    {
        return Buku::findOrFail($id);
    }

    public function create(array $data): Buku
    {
        return Buku::create($data);
    }

    public function update(string $id, array $data): Buku
    {
        $buku = Buku::findOrFail($id);
        $buku->update($data);

        return $buku;
    }

    public function delete(string $id): void
    {
        $buku = Buku::findOrFail($id);
        $buku->delete();
    }
}
