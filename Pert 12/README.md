# Praktikum PBO Sesi 12 — Refactoring dengan SOLID dan Repository Pattern

Modul praktikum Pemrograman Berbasis Objek (PBO) Sesi 12 ini berfokus pada penerapan teknik **Refactoring** menggunakan prinsip desain perangkat lunak **SOLID Principles** serta **Repository Pattern** pada studi kasus Sistem Perpustakaan (tersedia dalam bahasa Java dan PHP).

## 🎯 Apa Saja yang Dipelajari?

1. **Penerapan SOLID Principles**
   * **Single Responsibility Principle (SRP):** Memisahkan tanggung jawab kelas/modul secara spesifik (misalnya memisahkan logika perhitungan denda, manajemen buku, dan aturan peminjaman ke dalam kelas masing-masing).
   * **Open/Closed Principle (OCP):** Membuat sistem terbuka untuk ekstensi tetapi tertutup untuk modifikasi (contohnya penerapan *Factory Pattern* dan *Strategy Pattern* pada aturan denda tanpa harus membongkar kode utama).
   * **Liskov Substitution Principle (LSP):** Memastikan kelas turunan/sub-kelas dapat menggantikan kelas induknya (seperti kelas turunan denda anggota) tanpa merusak fungsionalitas program.
   * **Interface Segregation Principle (ISP):** Memecah interface yang besar menjadi interface yang lebih spesifik dan fungsional.
   * **Dependency Inversion Principle (DIP):** Menggantungkan ketergantungan pada abstraksi (interface/contract) bukan pada implementasi konkret.

2. **Penerapan Repository Pattern**
   * Memisahkan logika akses data (persistence/database/in-memory storage) dari *Business Logic* di *Service Layer*.
   * Menggunakan *Interface* `BookRepository` sebagai kontrak utama sehingga implementasi penyimpanan data (seperti `InMemoryBookRepository`) dapat diubah dengan mudah tanpa mengubah kode layanan di dalamnya.

3. **Refactoring Kode Kotor (*Code Smells Cleanup*)**
   * Membandingkan struktur kode lama yang menumpuk dan kotor (`LibraryServiceKotor.java`) dengan hasil *refactoring* kode bersih (`LibraryService.java` / `PerpustakaanService.java`).

4. **Unit Testing & Test-Driven Verification**
   * Menulis dan menjalankan pengujian otomatis menggunakan **JUnit 5** (di Java) dan **PHPUnit 11** (di PHP) untuk memastikan setiap hasil refactoring tetap berjalan sesuai ekspektasi logika bisnis perpustakaan.