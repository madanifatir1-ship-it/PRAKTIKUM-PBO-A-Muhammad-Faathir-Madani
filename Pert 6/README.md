# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
| :--- | :--- |
| **Nama** | Muhammad Faathir Madani |
| **NPM** | 4525210100 |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | Pertemuan 6 - Interface, Abstraksi, Enum & Trait |
| **Tanggal** | 9 Oktober 2026 |

---

## 1. Implementasi Java

### 1.1. File: `Fuelable.java`
**Penjelasan Kode:**
Mendefinisikan interface Fuelable sebagai kontrak khusus untuk objek yang dapat diisi bahan bakar. Memuat metode isiBahanBakar(), kapasitasTangki(), dan tipeBahanBakar(). Interface ini dipisah dari interface Movable sesuai dengan prinsip Interface Segregation Principle agar kelas yang tidak butuh bahan bakar tidak dipaksa mengimplementasikannya.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Starter code)*:
![SS Before Fuelable.java](<before fuelable java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After Fuelable.java](<AFTER FUELABLE JAVA .png>)

### 1.2. File: `Kendaraan.java`
**Penjelasan Kode:**
Mengimplementasikan abstract class Kendaraan yang menyimpan atribut dasar merek dan tahun. Menyediakan implementasi metode umur() untuk menghitung usia kendaraan agar tidak bernilai negatif, serta mendefinisikan abstract method jumlahRoda() yang wajib diisi oleh kelas turunan.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Starter code)*:
![SS Before Kendaraan.java](<before kendaraan java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After Kendaraan.java](<AFTER KENDARAAN JAVA .png>)

### 1.3. File: `Main.java`
**Penjelasan Kode:**
Program penguji utama Java yang menguji objek Mobil dan Sepeda pada koleksi interface Movable dan Fuelable. Membuktikan bahwa pemanggilan fungsi isiPenuh() pada objek Sepeda ditolak saat proses kompilasi (compile-time error) karena Sepeda tidak mengimplementasikan interface Fuelable.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Starter code)*:
![SS Before Main.java](<before main java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After Main.java](<AFTER MAIN JAVA .png>)

### 1.4. File: `Mobil.java`
**Penjelasan Kode:**
Kelas konkret turunan Kendaraan yang mengimplementasikan dua interface sekaligus yaitu Movable dan Fuelable. Meng-override jumlahRoda(), bergerak(), kecepatanMaksimum(), serta menangani pengisian bahan bakar dengan validasi batas kapasitas tangki dan penolakan nilai non-positif.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Starter code)*:
![SS Before Mobil.java](<before mobil java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After Mobil.java](<after mobil Java .png>)

### 1.5. File: `Movable.java`
**Penjelasan Kode:**
Mendefinisikan interface Movable sebagai kontrak untuk objek yang dapat bergerak. Memuat metode bergerak(), kecepatanMaksimum(), dan default method ringkasanGerak() yang mengembalikan string informasi kecepatan maksimum.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Starter code)*:
![SS Before Movable.java](<before moveable java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After Movable.java](<AFTER MOVEABLE JAVA .png>)

### 1.6. File: `Sepeda.java`
**Penjelasan Kode:**
Kelas baru turunan Kendaraan yang hanya mengimplementasikan interface Movable tanpa mengimplementasikan Fuelable. Menentukan jumlah roda sebanyak 2, mendefinisikan kecepatan maksimum dan perilaku bergerak tanpa membutuhkan bahan bakar.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / File baru dibuat)*:
![SS Before Sepeda.java](<before sepeda java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After Sepeda.java](<after sepeda JAVA .png>)

### 1.7. File: `TipeBahanBakar.java`
**Penjelasan Kode:**
Mengimplementasikan enum TipeBahanBakar dengan konstanta BENSIN, SOLAR, dan LISTRIK. Enum ini menyimpan atribut label dan harga per satuan, serta memiliki metode biayaPengisian() dan ramahLingkungan() yang mengembalikan nilai true khusus untuk LISTRIK.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Starter code)*:
![SS Before TipeBahanBakar.java](<before tipe bahan bakar JAVA .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After TipeBahanBakar.java](<AFTER TIPEBAHANBAKAR JAVA .png>)

### Output Program (Java)
![Output Java](<RUN JAVA .png>)

---

## 2. Implementasi PHP

### 2.1. File: `abstraksi.php`
**Penjelasan Kode:**
Mengimplementasikan interface Movable dan Fuelable, backed enum TipeBahanBakar, trait Loggable, abstract class Kendaraan, serta kelas Mobil, Sepeda, dan Pesanan dalam skrip PHP 8 dengan declare(strict_types=1). Membuktikan penggunaan ulang kode secara horizontal menggunakan trait Loggable pada kelas Pesanan yang tidak memiliki hubungan hirarki dengan Kendaraan.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Starter code)*:
![SS Before abstraksi.php](<before abstraksi 1 PHP .png>)
![alt text](<before abstraksi 2 PHP .png>)
![alt text](<before abstraksi 3 PHP .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After abstraksi.php](<after abstraksi PHP 1 .png>)
![alt text](<after abstraksi PHP 2 .png>)
![alt text](<after abstraksi PHP 3.png>)

### 2.2. File: `main.php`
**Penjelasan Kode:**
Skrip penguji utama versi PHP yang mengeksekusi perulangan objek Movable, fungsi isiPenuh(), evaluasi metode enum, serta pemanggilan trait Loggable. Membuktikan bahwa pemaksaan objek Sepeda ke fungsi isiPenuh() menghasilkan TypeError saat runtime yang dapat ditangkap dengan blok try-catch agar program tetap berjalan.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Starter code)*:
![SS Before main.php](<BEFORE MAIN PHP .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After main.php](<after main php -1.png>)

### Output Program (PHP)
![Output PHP](<RUN PHP .png>)

---

## 3. Kesimpulan
Interface menetapkan kontrak perilaku tanpa menyimpan status, memungkinkan satu kelas mengimplementasikan banyak interface sekaligus. Abstract class digunakan untuk membagikan kode dan atribut yang identik di sepanjang hirarki pewarisan. Enum pada Java dan PHP mampu memiliki properti dan metode perilaku sendiri. Selain itu, Trait pada PHP menyediakan mekanisme penggunaan ulang kode secara horizontal antar kelas yang tidak berada dalam hirarki pewarisan yang sama.