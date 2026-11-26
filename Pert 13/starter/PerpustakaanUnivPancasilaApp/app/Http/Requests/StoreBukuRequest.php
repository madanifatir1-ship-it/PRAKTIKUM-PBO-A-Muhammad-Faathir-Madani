<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class StoreBukuRequest extends FormRequest
{
    public function authorize(): bool
    {
        return true;
    }

    public function rules(): array
    {
        return [
            'isbn' => 'required|string|max:30|unique:buku,isbn',
            'nama_penulis' => 'required|string|max:255',
            'title' => 'required|string|max:255',
            'jumlah_eksamplar' => 'required|integer|min:0',
            'bahasa' => 'required|string|in:id,en,ar',
        ];
    }

    public function messages(): array
    {
        return [
            'isbn.required' => 'ISBN wajib diisi.',
            'isbn.unique' => 'ISBN sudah terdaftar.',
            'nama_penulis.required' => 'Nama penulis wajib diisi.',
            'title.required' => 'Judul buku wajib diisi.',
            'jumlah_eksamplar.required' => 'Jumlah eksamplar wajib diisi.',
            'jumlah_eksamplar.integer' => 'Jumlah eksamplar harus berupa angka.',
            'jumlah_eksamplar.min' => 'Jumlah eksamplar tidak boleh negatif.',
            'bahasa.required' => 'Kolom bahasa wajib diisi.',
            'bahasa.string' => 'Kolom bahasa harus berupa teks.',
            'bahasa.in' => 'Pilihan bahasa hanya boleh id, en, atau ar.',
        ];
    }
}