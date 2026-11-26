<?php

namespace App\Repositories;

use App\Models\Buku;
use Illuminate\Support\Collection;

interface BookRepository
{
    public function all(?string $bahasa = null): Collection;

    public function findOrFail(string $id): Buku;

    public function create(array $data): Buku;

    public function update(string $id, array $data): Buku;

    public function delete(string $id): void;
}
