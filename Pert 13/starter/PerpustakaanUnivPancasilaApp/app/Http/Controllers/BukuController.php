<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use App\Http\Requests\StoreBukuRequest;
use App\Repositories\BookRepository;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;

class BukuController extends Controller
{
    public function __construct(
        private readonly BookRepository $bookRepository,
    ) {}

    public function index(Request $request): JsonResponse
    {
        $bahasa = $request->filled('bahasa') ? (string) $request->query('bahasa') : null;
        $buku = $this->bookRepository->all($bahasa);

        return response()->json([
            'status' => true,
            'data' => $buku,
        ]);
    }

    public function store(StoreBukuRequest $request): JsonResponse
    {
        $buku = $this->bookRepository->create($request->validated());

        return response()->json([
            'status' => true,
            'message' => 'Buku berhasil ditambahkan',
            'data' => $buku,
        ], 201);
    }

    public function show(string $id): JsonResponse
    {
        $buku = $this->bookRepository->findOrFail($id);

        return response()->json([
            'status' => true,
            'data' => $buku,
        ]);
    }

    public function update(Request $request, string $id): JsonResponse
    {
        $buku = $this->bookRepository->findOrFail($id);

        $validated = $request->validate([
            'isbn' => 'sometimes|required|string|max:20|unique:buku,isbn,' . $buku->id,
            'nama_penulis' => 'sometimes|required|string|max:255',
            'title' => 'sometimes|required|string|max:255',
            'jumlah_eksamplar' => 'sometimes|required|integer|min:0',
            'bahasa' => 'sometimes|required|string|in:id,en,ar',
        ]);

        $buku = $this->bookRepository->update($id, $validated);

        return response()->json([
            'status' => true,
            'message' => 'Data buku berhasil diperbarui',
            'data' => $buku,
        ]);
    }

    public function destroy(string $id): JsonResponse
    {
        $this->bookRepository->delete($id);

        return response()->json([
            'status' => true,
            'message' => 'Data buku berhasil dihapus',
        ]);
    }
}