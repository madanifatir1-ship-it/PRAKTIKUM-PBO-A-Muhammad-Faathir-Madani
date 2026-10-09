# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
| :--- | :--- |
| **Nama** | Muhammad Faathir Madani |
| **NPM** | 4525210100 |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | Pertemuan 3 - Constructor, Anggota Statis, dan Konstanta |
| **Tanggal** | 17 September 2026 |

---

## 1. Implementasi Java

### 1.1. File: `RekeningBank.java`
**Penjelasan Kode:**
> Mengimplementasikan kelas `RekeningBank` yang menerapkan *constructor overloading* dan *constructor delegation* (`this(...)`) untuk mencegah duplikasi logika validasi[cite: 12, 13]. Menggunakan variabel *static* `jumlahRekening` untuk menghitung total objek rekening yang dibuat[cite: 12, 13], serta mengganti angka ajaib (*magic numbers*) menggunakan konstanta `static final` (seperti `BIAYA_ADMIN`, `BATAS_PENARIKAN`, dan `BUNGA_TAHUNAN`)[cite: 12, 13].

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:
![SS Before RekeningBank.java](<before rekening java 1.png>)
![alt text](<before rekening java 2.png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After RekeningBank.java](<after rekening java 1 .png>)
![alt text](<after rekening java 2.png>)

### 1.2. File: `Main.java`
**Penjelasan Kode:**
> Berfungsi sebagai kelas penguji (*driver code*) untuk memverifikasi fungsionalitas `RekeningBank` di Java[cite: 12]. Program menguji *constructor delegation*, penghitung *static*, operasi mutasi saldo (setor dan tarik), pemotongan biaya admin yang aman agar saldo tidak negatif[cite: 12], serta pemanggilan metode *static* `bungaSetahun()`[cite: 12].

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Kesalahan kompilasi)*:
![SS Before Main.java](<before main java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After Main.java](<after main java .png>)

### Output
**Output Program:**
![Output Java](<Run java.png>)

---

## 2. Implementasi PHP

### 2.1. File: `RekeningBank.php`
**Penjelasan Kode:**
> Mengimplementasikan kelas `RekeningBank` pada PHP 8[cite: 12]. Karena PHP tidak mendukung *constructor overloading*[cite: 12, 13], kelas ini memanfaatkan *named constructor* (`rekeningPelajar()`) dengan instansiasi `new static()`[cite: 12, 13]. Atribut *static* `$jumlahRekening` dan konstanta kelas (`const`) digunakan untuk menyelaraskan logika bisnis dengan versi Java[cite: 12, 13].

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Galat logika)*:
![SS Before RekeningBank.php](<before rekening php 1 .png>)
![alt text](<before rekening php 2 .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After RekeningBank.php](<after rekening php 1.png>)
![alt text](<after rekening php 2 .png>)
![alt text](<after rekening php 3 .png>)

### 2.2. File: `main.php`
**Penjelasan Kode:**
> Merupakan skrip penguji utama versi PHP yang menjalankan skenario uji identik dengan versi Java[cite: 11, 12]. Menguji pembuatan objek melalui *constructor* standar maupun *named constructor*, memvalidasi batas penarikan, menjaga *invariant* saldo agar tidak negatif saat pemotongan biaya admin[cite: 12], dan mencetak kalkulasi bunga *static*[cite: 12].

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Galat logika)*:
![SS Before main.php](<before main php .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After main.php](<after main php.png>)

### Output
**Output Program:**
![Output PHP](<running php .png>)

---

## 3. Kesimpulan
> Penerapan *constructor delegation* (`this(...)`) pada Java dan *named constructor* pada PHP berhasil mengeliminasi duplikasi logika validasi saat pembuatan objek[cite: 12, 13]. Anggota *static* terbukti menjadi milik kelas yang dibagi ke seluruh instance (seperti penghitung objek `jumlahRekening`)[cite: 12, 13], sementara penggunaan konstanta menggantikan angka ajaib (*magic numbers*) sehingga kode lebih terstruktur, aman, dan mudah dipelihara[cite: 12, 13].