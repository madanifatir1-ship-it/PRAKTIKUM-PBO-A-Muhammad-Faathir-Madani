# Lembar Penelusuran — Laravel 13

Nama / NIM: Muhammad Faathir Madani / 4525210100
Proyek yang ditelusuri: `starter/PerpustakaanUnivPancasilaApp/`

Isi kolom **Berkas** dan **Baris** dengan yang benar-benar Anda temukan di
proyek ini. Jangan menyalin dari modul — nomor barisnya akan diperiksa.

Kolom **Catatan Anda** diisi satu kalimat dengan bahasa Anda sendiri.

| Konsep OOP (pertemuan) | Wujudnya di Laravel | Berkas | Baris | Catatan Anda |
|---|---|---|---|---|
| Namespace & autoload (9) | PSR-4 `App\` → `app/` | `app/Models/Buku.php` | 3 | Namespace `App\Models` memetakan lokasi file ke folder `app/Models` sehingga Composer bisa memuat class otomatis tanpa `require`. |
| Pewarisan (4) | `class Buku extends Model` | `app/Models/Buku.php` | 10 | Class `Buku` mewarisi seluruh fitur query dan operasi database dari parent class `Model` milik Eloquent. |
| Enkapsulasi (2) | `$fillable` / `$hidden` | `app/Models/Buku.php` | 16 | Property `$fillable` membatasi atribut apa saja yang boleh diisi secara massal agar kolom sensitif tetap terlindungi. |
| Polimorfisme (5) | method yang Anda override, dipanggil framework | `app/Http/Requests/StoreBukuRequest.php` | 14, 25 | Method `rules()` dan `messages()` di-override dari `FormRequest` lalu dipanggil otomatis oleh framework saat validasi dijalankan. |
| Trait (6) | `use HasFactory, SoftDeletes` | `app/Models/Buku.php` | 12 | Trait `HasFactory` menyuntikkan kemampuan pembuatan data dummy/factory ke dalam model tanpa pewarisan ganda. |
| Enum (6) | `enum StatusBuku: string` | — (Tidak digunakan di proyek ini) | — | Proyek starter ini memakai tipe `string` biasa untuk kolom seperti `bahasa` tanpa file PHP Enum terpisah. |
| Relasi objek (7) | `hasMany()` / `belongsTo()` | `app/Models/Buku.php` & `app/Models/PeminjamanBuku.php` | 35 (`Buku.php`), 25 & 31 (`PeminjamanBuku.php`) | Relasi one-to-many antar model dihubungkan lewat method `hasMany()` pada `Buku` dan `belongsTo()` pada `PeminjamanBuku`. |
| Abstraksi & interface (6) | interface repository | — (Tidak digunakan di proyek ini) | — | Proyek ini belum memisahkan kontrak interface khusus karena controller langsung berinteraksi dengan Eloquent Model. |
| Exception (10) | custom exception + `render()` | — (Tidak digunakan di proyek ini) | — | Penanganan error masih mengandalkan exception bawaan Laravel seperti `ModelNotFoundException` dari `findOrFail()` dan `ValidationException`. |
| Collections (11) | `Illuminate\Support\Collection` | `app/Http/Controllers/BukuController.php` | 21 | Pemanggilan `$query->get()` mengembalikan objek `Collection` berisi kumpulan model `Buku` yang siap diserialisasi ke JSON. |
| Repository & SOLID (12) | Repository + Service | — (Tidak digunakan di proyek ini) | — | Logika bisnis dan akses data masih ditulis langsung di dalam controller tanpa layer Repository atau Service terpisah. |
| Dependency Injection (13) | constructor injection di Controller | `app/Http/Controllers/BukuController.php` | 14, 29 | Framework menyuntikkan objek `Request` dan `StoreBukuRequest` langsung ke parameter method controller saat route dipanggil. |
| Binding interface → implementasi (13) | Service Provider | `app/Providers/AppServiceProvider.php` | 12, 20 | `AppServiceProvider` menyediakan method `register()` dan `boot()` sebagai tempat mendaftarkan binding ke Service Container. |

---

## Pertanyaan lanjutan

**1. Apa yang diwarisi `Buku` dari `Model`?** Sebutkan minimal empat method
yang tersedia tanpa Anda tulis sendiri.

`Buku` mewarisi seluruh kemampuan ORM Eloquent dari `Illuminate\Database\Eloquent\Model` untuk berinteraksi dengan tabel `buku`. Empat contoh method bawaan yang langsung bisa dipakai tanpa ditulis sendiri adalah `all()`, `findOrFail()`, `create()`, `update()`, `delete()`, `where()`, dan `latest()`.

**2. Apa yang terjadi bila `$fillable` dikosongkan?** Coba, lalu tuliskan.

Jika `$fillable` dikosongkan (`[]`), Eloquent akan menolak penyimpanan data lewat `Buku::create(...)` dan melemparkan `Illuminate\Database\Eloquent\MassAssignmentException` sebagai perlindungan dari *mass assignment*. Sementara jika hanya salah satu kolom (misalnya `'bahasa'`) yang tidak dimasukkan ke `$fillable`, nilai kolom tersebut dari request akan diabaikan saat `create()` dan database memakai nilai default-nya (`'id'`).

**3. Di mana objek `PeminjamanService` dibuat?** Cari — mungkin tidak ada di
mana pun yang Anda tulis. Jelaskan siapa yang membuatnya.

Pada proyek starter ini file `PeminjamanService` memang tidak ada karena logika peminjaman ditulis langsung di `PeminjamanBukuController`, begitu pula saat sebuah class service atau `StoreBukuRequest` di-type-hint di controller tidak pernah ada sintaks `new ...` yang kita tulis manual. Objek tersebut dibuat secara otomatis oleh **Laravel Service Container (IoC Container)** menggunakan PHP *Reflection* untuk membaca tipe parameter pada constructor atau method controller lalu menyuntikkannya (*Dependency Injection*).

**4. Bandingkan dengan kode Anda sendiri di sesi 12.** Sebutkan satu hal yang
bentuknya SAMA PERSIS.

Salah satu bagian yang bentuknya sama persis dengan kode pada Sesi 12 adalah struktur penulisan validasi dan respons JSON pada Controller serta penggunaan Eloquent Model dengan properti `protected $table` dan `protected $fillable` untuk memetakan kolom tabel database.