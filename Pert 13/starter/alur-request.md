# Alur Satu Permintaan HTTP — Laravel 13

Nama / NIM: Muhammad Faathir Madani / 4525210100
Endpoint yang ditelusuri: `GET /api/buku/get` (dan `GET /api/buku/get?bahasa=en`)

## Tahapan Perjalanan Request hingga Response JSON

1. **Entry Point (`public/index.php`)**
   Semua permintaan HTTP masuk pertama kali ke `public/index.php`. Berkas ini memuat Composer autoloader (`vendor/autoload.php`), menginisialisasi instance aplikasi Laravel dari `bootstrap/app.php`, lalu menangkap objek `Illuminate\Http\Request`.

2. **Bootstrap & Kernel (`bootstrap/app.php`)**
   Aplikasi mendaftarkan berkas routing (`routes/web.php` dan `routes/api.php`), middleware global, serta exception handler sebelum meneruskan request ke HTTP Kernel.

3. **Pencocokan Rute (`routes/api.php`)**
   Router Laravel mencocokkan HTTP method `GET` dan URI `/api/buku/get` dengan definisi route:
   `Route::get('/buku/get', [BukuController::class, 'index']);` (otomatis ber-prefix `/api`).

4. **Middleware & Dependency Injection (`app/Http/Controllers/BukuController.php`)**
   Request melewati pipeline middleware API. Sebelum memanggil method `index(Request $request)`, Laravel Service Container menyuntikkan objek `Illuminate\Http\Request` ke parameter method controller.

5. **Pemanggilan Model & Query Scope (`app/Models/Buku.php`)**
   Di dalam `BukuController::index()`, controller memanggil `Buku::latest()` dan jika parameter query `bahasa` dikirim, memanggil local query scope `->berbahasa($request->query('bahasa'))` yang menambahkan klausa `where('bahasa', $bahasa)` pada Eloquent Query Builder.

6. **Eksekusi Query ke Database (`database/database.sqlite` / MySQL)**
   Pemanggilan `$query->get()` menerjemahkan Query Builder menjadi perintah SQL, mengeksekusinya lewat koneksi PDO, lalu membungkus setiap baris hasil menjadi objek model `App\Models\Buku` di dalam `Illuminate\Support\Collection`.

7. **Serialisasi JSON Response (`app/Http/Controllers/BukuController.php`)**
   Controller memanggil `response()->json(['status' => true, 'data' => $buku])`. Eloquent otomatis menyembunyikan atribut `$hidden` (jika ada), mengubah `Collection` model menjadi array JSON, dan mengirim respons HTTP `200 OK` kembali ke client.

---

## Contoh SQL yang Dihasilkan Eloquent (`DB::enableQueryLog()`)

Saat menjalankan `DB::enableQueryLog()` dan memanggil `App\Models\Buku::berbahasa('en')->latest()->get()`:

```sql
select * from "buku" where "bahasa" = ? order by "created_at" desc
```

