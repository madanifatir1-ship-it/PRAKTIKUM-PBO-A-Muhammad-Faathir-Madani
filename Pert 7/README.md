# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
| :--- | :--- |
| **Nama** | Muhammad Faathir Madani |
| **NPM** | 4525210100 |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | 07 - Pemodelan Relasi Objek dan UML Class Diagram |
| **Tanggal** | 15 Oktober 2026 |

---

## 1. Implementasi Java

### 1.1. File: `Buku.java`
**Penjelasan Kode:**
> Mengimplementasikan relasi Komposisi antara Buku dan Bab secara ketat. Menggunakan guard clause untuk menolak jumlah eksemplar negatif, pembuatan objek Bab secara internal di method tambahBab, serta mengembalikan salinan tak-berubah di getBab via List.copyOf agar terhindar dari kebocoran koleksi internal.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:<br>
  ![SS Before Buku Part 1](<images/Before Buku Java 1 .png>)<br>
  ![SS Before Buku Part 2](<images/Before Buku Java 2 .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:<br>
  ![SS After Buku Part 1](<images/after buku java 1 .png>)<br>
  ![SS After Buku Part 2](<images/after buku java 2 .png>)

### 1.2. File: `Jurusan.java`
**Penjelasan Kode:**
> Mengimplementasikan relasi Agregasi antara Jurusan dan Mahasiswa. Objek Mahasiswa diterima dari luar lewat method daftarkan dengan pengecekan null dan duplikasi, serta method keluarkan yang melepas hubungan tanpa menghapus objek Mahasiswa dari memori.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:<br>
  ![SS Before Jurusan](<images/Before Jurusan Java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:<br>
  ![SS After Jurusan Part 1](<images/after jurusan java 1 .png>)<br>
  ![SS After Jurusan Part 2](<images/after jurusan java 2 .png>)

### 1.3. File: `Koleksi.java`
**Penjelasan Kode:**
> Abstract class yang menjadi induk bagi seluruh koleksi perpustakaan. Menyimpan atribut umum seperti nomorInduk, judul, penulis, tahun, jumlahEksemplar, dan statusKoleksi, serta menetapkan abstract method masaPinjamHari dan bolehDipinjam.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:<br>
  ![SS Before Koleksi](<images/before koleksi java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:<br>
  ![SS After Koleksi Part 1](<images/after koleksi java 1 .png>)<br>
  ![SS After Koleksi Part 2](<images/after koleksi java 2 .png>)

### 1.4. File: `Majalah.java`
**Penjelasan Kode:**
> Subclass turunan dari Koleksi yang mengimplementasikan aturan bisnis peminjaman khusus majalah dengan batas waktu peminjaman maksimal selama 3 hari.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:<br>
  ![SS Before Majalah](<images/before majalah java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:<br>
  ![SS After Majalah](<images/after majalah java .png>)

### 1.5. File: `Skripsi.java`
**Penjelasan Kode:**
> Subclass turunan dari Koleksi untuk dokumen skripsi. Mengatur aturan bisnis bahwa skripsi tidak dapat dipinjam keluar (masa pinjam 0 hari dan bolehDipinjam mengembalikan false).

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:<br>
  ![SS Before Skripsi](<images/before skripsi java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:<br>
  ![SS After Skripsi](<images/after skripsi java .png>)

### 1.6. File: `Anggota.java`
**Penjelasan Kode:**
> Entitas peminjam yang menyimpan data identitas anggota perpustakaan dan mengelola batasan invariant maksimal meminjam 3 koleksi dalam satu waktu.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:<br>
  ![SS Before Anggota](<images/before anggota java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:<br>
  ![SS After Anggota Part 1](<images/after anggota java 1 .png>)<br>
  ![SS After Anggota Part 2](<images/after anggota java 2 .png>)

### 1.7. File: `Peminjaman.java`
**Penjelasan Kode:**
> Kelas asosiasi yang mencatat transaksi antara Anggota dan Koleksi, menghitung tanggal jatuh tempo peminjaman, serta mengkalkulasi denda keterlambatan.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:<br>
  ![SS Before Peminjaman](<images/before peminjaman java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:<br>
  ![SS After Peminjaman Part 1](<images/after peminjaman java 1 .png>)<br>
  ![SS After Peminjaman Part 2](<images/after peminjaman java 2 .png>)

### 1.8. File: `StatusKoleksi.java`
**Penjelasan Kode:**
> Enum yang mendefinisikan konstanta status koleksi perpustakaan, yaitu TERSEDIA, DIPINJAM, RUSAK, dan HILANG.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:<br>
  ![SS Before StatusKoleksi](<images/before status koleksi java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:<br>
  ![SS After StatusKoleksi](<images/after status koleksi java .png>)

### 1.9. File: `JenisKeanggotaan.java`
**Penjelasan Kode:**
> Enum yang menyimpan jenis profil keanggotaan (MAHASISWA, DOSEN, ANGGOTA_KEHORMATAN) beserta besaran tarif denda keterlambatan per hari.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:<br>
  ![SS Before JenisKeanggotaan](<images/before jenis keanggotaan java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:<br>
  ![SS After JenisKeanggotaan](<images/after jenis keanggotaan java .png>)

### 1.10. File: `PerpustakaanService.java`
**Penjelasan Kode:**
> Kelas lapisan layanan yang menghubungkan entitas model lewat relasi Asosiasi dan Dependensi untuk menangani fungsionalitas tambah koleksi, pencarian, peminjaman, dan pengembalian.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:<br>
  ![SS Before PerpustakaanService](<images/before perpustakaan service java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:<br>
  ![SS After PerpustakaanService Part 1](<images/after perpustakaan service java 1 .png>)<br>
  ![SS After PerpustakaanService Part 2](<images/after perpustakaan service java 2 .png>)<br>
  ![SS After PerpustakaanService Part 3](<images/after perpustakaan service java 3 .png>)

### 1.11. File: `Main.java`
**Penjelasan Kode:**
> Berkas eksekusi utama Java yang membuktikan bahwa manipulasi koleksi Bab dari luar akan ditolak oleh UnsupportedOperationException serta membuktikan objek Mahasiswa tetap eksis setelah dilepas dari Jurusan.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:<br>
  ![SS Before Main](<images/before main JAVA -1.png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:<br>
  ![SS After Main Part 1](<images/after main java 1 .png>)<br>
  ![SS After Main Part 2](<images/after main java 2 .png>)

### Output
**Output Program:**<br>
![Output Java](<images/RUN JAVA .png>)

---

## 2. Implementasi PHP

### 2.1. File: `relasi.php`
**Penjelasan Kode:**
> Implementasi Komposisi dan Agregasi di PHP. Menggunakan constructor readonly pada kelas Bab, penolakan eksemplar negatif di Buku, serta pengelolaan larik Mahasiswa di Jurusan.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Galat logika)*:<br>
  ![SS Before relasi.php Part 1](<images/before relasi php 1 .png>)<br>
  ![SS Before relasi.php Part 2](<images/before relasi php 2 .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:<br>
  ![SS After relasi.php Part 1](<images/after relasi php 1 .png>)<br>
  ![SS After relasi.php Part 2](<images/after relasi php 2 .png>)<br>
  ![SS After relasi.php Part 3](<images/after relasi php 3 .png>)

### 2.2. File: `main.php`
**Penjelasan Kode:**
> Skrip eksekusi utama PHP untuk menjalankan pengujian Komposisi dan Agregasi serta menampilkan hasil ke terminal secara konsisten.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Galat logika)*:<br>
  ![SS Before main.php](<images/before main php .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:<br>
  ![SS After main.php](<images/after main php .png>)

### Output
**Output Program:**<br>
![Output PHP](<images/RUN PHP .png>)

---

## 3. Pemodelan UML Class Diagram

### 3.1. File: `perpustakaan-kerangka.puml`
**Penjelasan Kode:**
> Pemodelan PlantUML Class Diagram yang menggambarkan relasi Komposisi (*--), Agregasi (o--), Asosiasi (-->), Dependensi (..>), dan Inheritansi (<|--) beserta multiplicity pada setiap entitas.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kerangka awal)*:<br>
  ![SS Before perpustakaan-kerangka.puml](<images/before kerangka perpustakaan PUML .png>)

* **After** *(Kondisi akhir / Render Diagram UML)*:<br>
  ![SS After perpustakaan-kerangka.puml Part 1](<images/after perpustakaan puml 1 .png>)<br>
  ![SS After perpustakaan-kerangka.puml Part 2](<images/after perpustakaan puml 2 .png>)<br>
  ![SS After perpustakaan-kerangka.puml Part 3](<images/after perpustakaan puml 3 .png>)

### Output
**Output Diagram:**<br>
![Diagram Class UML](<images/run diagram puml .png>)

---

## 4. Kesimpulan
> Praktikum Pertemuan 07 membuktikan bahwa empat derajat keterikatan relasi objek yaitu Komposisi, Agregasi, Asosiasi, dan Dependensi adalah keputusan rancangan yang wajib tecermin secara konsisten di kode program dan pemodelan UML. Komposisi mengikat siklus hidup objek bagian agar mati-hidup bersama induknya serta dilindungi dari kebocoran koleksi melalui List.copyOf, sedangkan Agregasi mempertahankan independensi objek bagian di memori meski dilepas dari induknya. Penerapan PlantUML mempermudah visualisasi aturan bisnis dan multiplicity untuk menghasilkan arsitektur kode yang terstruktur baik di Java maupun PHP.