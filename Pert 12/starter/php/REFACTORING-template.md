# Template Laporan Refactoring SOLID & Repository Pattern

## 1. Masalah pada `LibraryServiceKotor`
- **SRP (Single Responsibility Principle):** Satu kelas mengelola array stok/judul, validasi peminjaman, sekaligus perhitungan tarif denda.
- **OCP (Open/Closed Principle):** Penambahan jenis anggota baru (misal `alumni`) memaksa modifikasi langsung pada blok `match`/`switch` di `hitungDenda()`.
- **DIP (Dependency Inversion Principle):** Bergantung langsung pada struktur array internal (`$this->stok`, `$this->judul`), sulit diganti ke database atau diuji dengan repository palsu.

## 2. Solusi Refactoring
- **Repository Pattern:** Abstraksi `Perpustakaan\Repository\BookRepository` dengan implementasi `InMemoryBookRepository` (didukung koleksi `KoleksiBuku`).
- **Strategy + Factory Pattern:** Interface `Perpustakaan\Denda\AturanDenda` beserta `DendaMahasiswa`, `DendaDosen`, `DendaBebas`, `DendaAlumni`, dan `AturanDendaFactory`.
- **Guard Clauses & Custom Exceptions:** Validasi awal pada `LibraryService::pinjam()` dengan exception domain yang spesifik.

## 3. Verifikasi Pengujian
- `tests/Repository/KoleksiBukuTest.php`
- `tests/Service/LibraryServiceTest.php`
