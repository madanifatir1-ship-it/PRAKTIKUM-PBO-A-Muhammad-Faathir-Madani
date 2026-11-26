<?php

namespace App\Exceptions;

use Exception;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;

class BukuTidakBolehDipinjamException extends Exception
{
    public function __construct(
        public readonly string $isbn,
        public readonly string $alasan,
    ) {
        parent::__construct("Buku dengan ISBN {$isbn} tidak boleh dipinjam: {$alasan}");
    }

    public function render(Request $request): JsonResponse
    {
        return response()->json([
            'status' => false,
            'message' => 'Buku tidak boleh dipinjam.',
            'isbn' => $this->isbn,
            'alasan' => $this->alasan,
        ], 422);
    }
}