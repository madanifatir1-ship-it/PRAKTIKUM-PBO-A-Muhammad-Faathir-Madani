# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
| :--- | :--- |
| **Nama** | Muhammad Faathir Madani |
| **NPM** | 4525210100 |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | Pertemuan 4 - Pewarisan (Inheritance & Polymorphism) |
| **Tanggal** | 24 September 2026 |

---

## 1. Implementasi Java

### 1.1. File: `Pegawai.java`
**Penjelasan Kode:**
> Mengimplementasikan *abstract class* `Pegawai` sebagai kelas induk yang menyimpan atribut dasar (`nip`, `nama`, `gajiPokok`)[cite: 14]. Kelas ini tidak dapat diinstansiasi secara langsung dan mendefinisikan metode dasar yang wajib di-*override* oleh *subclass*[cite: 14].

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:
![SS Before Pegawai.java](<before pegawa java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After Pegawai.java](<after pegawai java .png>)

### 1.2. File: `PegawaiTetap.java`
**Penjelasan Kode:**
> Merupakan *subclass* turunan dari `Pegawai` yang menginisialisasi atribut induk lewat `super(nip, nama, gajiPokok)`[cite: 13, 14]. Meng-override `hitungGaji()` untuk menambahkan tunjangan masa kerja sesuai persentase tahunan[cite: 13, 14].

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:
![SS Before PegawaiTetap.java](<before pegawai tetap java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After PegawaiTetap.java](<after pegawai tetap .png>)

### 1.3. File: `PegawaiKontrak.java`
**Penjelasan Kode:**
> Merupakan *subclass* turunan dari `Pegawai` yang memperhitungkan gaji berdasarkan jam kerja dan tarif per jam[cite: 14]. Menggunakan `super()` untuk memanggil *constructor* induk dan melakukan *method overriding* pada `hitungGaji()`[cite: 13, 14].

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:
![SS Before PegawaiKontrak.java](<before pegawai kontrak java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After PegawaiKontrak.java](<after pegawai kontrak java .png>)

### 1.4. File: `Dosen.java`
**Penjelasan Kode:**
> Mengimplementasikan kelas `Dosen` sebagai turunan dari `PegawaiTetap`[cite: 14]. Menambahkan kalkulasi tunjangan fungsional/SKS di atas gaji dasar pegawai tetap dengan tetap memanfaatkan *reusability* logika kelas induknya[cite: 14].

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:
![SS Before Dosen.java](<before dosen java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After Dosen.java](<after dosen java .png>)

### 1.5. File: `PegawaiHarian.java`
**Penjelasan Kode:**
> Mengimplementasikan *subclass* `PegawaiHarian` yang mewarisi kelas `Pegawai`[cite: 14]. Menghitung total gaji berdasarkan jumlah hari masuk kerja dikalikan dengan tarif upah harian[cite: 14].

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:
![SS Before PegawaiHarian.java](<before pegawai harian java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After PegawaiHarian.java](<after pegawai harian .png>)

### 1.6. File: `PegawaiParuhWaktu.java`
**Penjelasan Kode:**
> Mengimplementasikan *subclass* `PegawaiParuhWaktu` dengan spesifikasi jam kerja paruh waktu[cite: 14]. Menggunakan pemanggilan `super()` untuk identitas pegawai serta meng-override fungsi `hitungGaji()` secara spesifik[cite: 13, 14].

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:
![SS Before PegawaiParuhWaktu.java](<before pegawai paruh waktu .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After PegawaiParuhWaktu.java](<after pegawai paruh waktu .png>)

### 1.7. File: `KomposisiVsWarisan.java`
**Penjelasan Kode:**
> Berfungsi sebagai kelas eksperimen untuk membuktikan perbedaan konsep Pewarisan (*is-a*) dan Komposisi (*has-a*)[cite: 14]. Menunjukkan bahwa komposisi memberikan fleksibilitas untuk mengganti komponen perilaku objek secara dinamis saat program berjalan[cite: 14].

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:
![SS Before KomposisiVsWarisan.java](<before komposisi vs warisan .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After KomposisiVsWarisan.java]![alt text](<after komposisi warisan 1 .png>)

### 1.8. File: `Main.java`
**Penjelasan Kode:**
> Berfungsi sebagai kelas penguji utama yang menerapkan *polymorphism*[cite: 12, 14]. Menampung seluruh objek variasi pegawai dalam array bertipe induk `Pegawai[]` dan mengeksekusi metode `hitungGaji()` secara dinamis[cite: 12, 14].

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:
![SS Before Main.java](<before main java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After Main.java](<after main java .png>)

### Output
**Output Program:**
![Output Java](<run java .png>)

---

## 2. Implementasi PHP

### 2.1. File: `Pegawai.php`
**Penjelasan Kode:**
> Mengimplementasikan struktur *abstract class* `Pegawai` beserta seluruh *subclass* turunannya (`PegawaiTetap`, `PegawaiKontrak`, `Dosen`, `PegawaiHarian`, dll.) dalam PHP 8[cite: 14]. Menggunakan `parent::__construct()` pada *constructor* kelas turunan[cite: 13, 14] dan memanfaatkan `declare(strict_types=1)` untuk memastikan tipe data ketat serta perilaku pewarisan yang identik dengan versi Java[cite: 14].

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Galat logika)*:
![SS Before Pegawai.php](<before pegawai php 1 .png>)
![alt text](<before pegawai php 2 .png>)


* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After Pegawai.php](<after pegawai php 1 .png>)
![alt text](<after pegawai php 2 .png>)
![alt text](<after pegawai php 3 .png>)

### 2.2. File: `komposisiVsWarisan.php`
**Penjelasan Kode:**
> Mengimplementasikan eksperimen perbandingan Komposisi (*has-a*) versus Pewarisan (*is-a*) versi PHP[cite: 14]. Menggunakan *type-hinting* dan *dependency injection* untuk menunjukkan bagaimana komponen perilaku objek dapat diganti secara dinamis saat *runtime*[cite: 14].

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Galat logika)*:
![SS Before komposisiVsWarisan.php](<before komposisi warisan php .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After komposisiVsWarisan.php](<after komposisi vs warisan php -1.png>)

### 2.3. File: `main.php`
**Penjelasan Kode:**
> Skrip penguji PHP yang menguji pemanggilan *polymorphic* pada koleksi array `$daftar` berisi berbagai jenis pegawai[cite: 14]. Memverifikasi bahwa setiap *subclass* mengeksekusi perhitungan gaji secara spesifik dan menghasilkan total rekap gaji yang persis sama dengan keluaran program Java[cite: 14].

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Galat logika)*:
![SS Before main.php](<before main php .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After main.php](<after main php .png>)

### Output
**Output Program:**
![Output PHP](<run php .png>)

---

## 3. Kesimpulan
> Pewarisan (*Inheritance*) memungkinkan *subclass* mewarisi atribut dan metode dari kelas induk (*superclass*) serta menggunakan `super()` / `parent::__construct()` untuk menghindari duplikasi kode[cite: 13, 14]. Penerapan *abstract class* mencegah instansiasi langsung kelas yang belum spesifik[cite: 14], sementara *Polymorphism* memungkinkan variabel bertipe kelas induk untuk menangani berbagai bentuk objek *subclass* dan mengeksekusi metode yang di-*override* secara dinamis saat program berjalan[cite: 14]. Selain itu, percobaan Komposisi membuktikan bahwa hubungan *has-a* memberikan fleksibilitas ekstra untuk perubahan perilaku objek secara kontekstual dibanding hirarki pewarisan kaku[cite: 14].