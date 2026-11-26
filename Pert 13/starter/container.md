# Pembuktian Service Container — Laravel 13

Nama / NIM: Muhammad Faathir Madani / 4525210100

## 1. Mekanisme Binding & Dependency Injection
- Kontrak `App\Repositories\BookRepository` diikat ke implementasi `App\Repositories\EloquentBookRepository` di dalam `App\Providers\AppServiceProvider::register()` menggunakan `$this->app->bind(BookRepository::class, EloquentBookRepository::class)`.
- `BukuController` hanya bergantung pada interface `BookRepository` melalui Constructor Injection (`public function __construct(private readonly BookRepository $bookRepository)`), tanpa memanggil model `Buku::*` secara langsung.

## 2. Pengujian Pergantian ke `InMemoryBookRepository`
- Ketika baris binding di `AppServiceProvider` diubah menjadi `$this->app->bind(BookRepository::class, InMemoryBookRepository::class)`, Laravel Service Container otomatis menyuntikkan objek `InMemoryBookRepository` ke dalam `BukuController`.
- Endpoint `/api/buku/get` langsung mengembalikan data buku dari array memori (`Laskar Pelangi (In-Memory)` dan `Clean Code (In-Memory)`) dan filter `?bahasa=en` tetap berjalan normal.
- **Pengamatan utama:** Kode di dalam `BukuController` **tidak berubah satu baris pun** karena controller terlepas (decoupled) dari detail penyimpanan data (Dependency Inversion Principle).

## 3. Pengembalian ke `EloquentBookRepository`
- Setelah pengujian selesai, binding pada `AppServiceProvider` dikembalikan ke `EloquentBookRepository::class` agar aplikasi kembali menggunakan database Eloquent dan seluruh suite pengujian (`php artisan test`) berjalan hijau.
