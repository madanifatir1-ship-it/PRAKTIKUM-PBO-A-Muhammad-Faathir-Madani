# Catatan Praktikum Sesi 12 — Refactoring dengan SOLID & Repository Pattern (PHP)

## Ringkasan Perubahan
1. **Single Responsibility Principle (SRP)**
   - Memisahkan penyimpanan data buku ke `BookRepository` (`InMemoryBookRepository` & `KoleksiBuku`).
   - Memisahkan perhitungan denda keterlambatan ke hierarki strategi `AturanDenda`.
   - Memisahkan ekspor laporan berkas ke `Perpustakaan\Util\LaporanExporter`.

2. **Open/Closed Principle (OCP) & Strategy Pattern**
   - `AturanDenda` menjadi kontrak polimorfik (`DendaMahasiswa`, `DendaDosen`, `DendaBebas`, `DendaAlumni`).
   - `AturanDendaFactory::untuk($jenisAnggota)` memilih strategi tanpa mengubah `LibraryService`.

3. **Dependency Inversion Principle (DIP) & Repository Pattern**
   - `LibraryService` bergantung pada interface `BookRepository` dan `AturanDenda` melalui Constructor Injection.

## Cara Menjalankan
```bash
php main.php
php vendor/bin/phpunit
```
