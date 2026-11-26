<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use App\Http\Requests\StoreBukuRequest;
use App\Models\Buku;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;

class BukuController extends Controller
{
    public function index(Request $request): JsonResponse
    {
        $query = Buku::latest();

        if ($request->filled('bahasa')) {
            $query->berbahasa($request->query('bahasa'));
        }

        $buku = $query->get();

        return response()->json([
            'status' => true,
            'data' => $buku,
        ]);
    }

    public function store(StoreBukuRequest $request): JsonResponse
    {
        $buku = Buku::create($request->validated());

        return response()->json([
            'status' => true,
            'message' => 'Buku berhasil ditambahkan',
            'data' => $buku,
        ], 201);
    }

    public function show(string $id): JsonResponse
    {
        $buku = Buku::findOrFail($id);

        return response()->json([
            'status' => true,
            'data' => $buku,
        ]);
    }

    public function update(Request $request, string $id): JsonResponse
    {
        $buku = Buku::findOrFail($id);

        $validated = $request->validate([
            'isbn' => 'required|string|max:20|unique:buku,isbn,' . $buku->id,
            'nama_penulis' => 'required|string|max:255',
            'title' => 'sometimes|required|string|max:255',
            'jumlah_eksamplar' => 'required|integer|min:0',
            'bahasa' => 'sometimes|required|string|in:id,en,ar',
        ]);

        $buku->update($validated);

        return response()->json([
            'status' => true,
            'message' => 'Data buku berhasil diperbarui',
            'data' => $buku,
        ]);
    }

    public function destroy(string $id): JsonResponse
    {
        $buku = Buku::findOrFail($id);
        $buku->delete();

        return response()->json([
            'status' => true,
            'message' => 'Buku berhasil dihapus',
        ]);
    }
}