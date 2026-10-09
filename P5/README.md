# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
| :--- | :--- |
| **Nama** | Muhammad Faathir Madani |
| **NPM** | 4525210100 |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | Pertemuan 5 - Polimorfisme |
| **Tanggal** | 9 Oktober 2026 |

---

## 1. Implementasi Java

### 1.1. File: `BangunDatar.java`
**Penjelasan Kode:**
Mengimplementasikan abstract class BangunDatar sebagai kontrak induk. Kelas ini menyimpan atribut nama, mendefinisikan abstract method luas() dan keliling(), serta meng-override toString() di kelas induk. Metode toString() memanfaatkan dynamic dispatch (late binding) untuk memanggil metode luas() dan keliling() yang diimplementasikan pada subclass.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Starter code)*:
![SS Before BangunDatar.java](<before bangun datar java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After BangunDatar.java](<after bangun datar java .png>)

### 1.2. File: `Lingkaran.java`
**Penjelasan Kode:**
Subclass turunan BangunDatar yang memvalidasi agar jariJari > 0 pada constructor. Meng-override metode luas() (Math.PI * r * r) dan keliling() (2 * Math.PI * r) menggunakan konstanta bawaan Math.PI.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Starter code)*:
![SS Before Lingkaran.java](<before lingkaran java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After Lingkaran.java](<after lingkaran java .png>)

### 1.3. File: `Persegi.java`
**Penjelasan Kode:**
Subclass turunan BangunDatar yang menolak nilai sisi <= 0 pada constructor. Meng-override metode luas() (sisi * sisi) dan keliling() (4 * sisi).

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Starter code)*:
![SS Before Persegi.java](<before persegi java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After Persegi.java](<after persegi java .png>)

### 1.4. File: `Segitiga.java`
**Penjelasan Kode:**
Subclass baru turunan BangunDatar dengan 3 panjang sisi (a, b, c). Menolak konstruksi jika ada sisi <= 0 atau jika ketiga sisi tidak memenuhi syarat ketaksamaan segitiga (a+b <= c, a+c <= b, b+c <= a). Menghitung keliling() (a+b+c) dan luas() menggunakan rumus Heron.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / File baru dibuat)*:
![SS Before Segitiga.java](<before segitiga java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After Segitiga.java](<after segitiga java .png>)

### 1.5. File: `Trapesium.java`
**Penjelasan Kode:**
Subclass baru turunan BangunDatar dengan atribut alas atas, alas bawah, dua sisi miring, dan tinggi. Memvalidasi bahwa seluruh nilai atribut harus > 0. Meng-override metode luas() (0.5 * (a + b) * t) dan keliling() (a + b + c + d).

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / File baru dibuat)*:
![SS Before Trapesium.java](<before trapesium java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After Trapesium.java](<after trapesium java .png>)

### 1.6. File: `AntiPattern.java`
**Penjelasan Kode:**
Memuat implementasi non-polimorfik yang mengandalkan pengecekan tipe instanceof serta percabangan if-else kaku pada kelas eksternal. Berkas ini berfungsi sebagai pembanding saat demo praktikum untuk menunjukkan kekurangan dari desain tanpa polimorfisme.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Starter code)*:
![SS Before AntiPattern.java](<before anti pattern java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After AntiPattern.java](<after antipattern java.png>)

### 1.7. File: `AntiPatternRefaktor.java`
**Penjelasan Kode:**
Merupakan file baru hasil refactoring polimorfik dari AntiPattern.java. Logika kalkulasi dipindahkan ke kelasnya masing-masing tanpa bergantung pada percabangan tipe instanceof, sehingga penambahan bentuk baru tidak perlu mengubah metode pengolahan utama.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / File baru dibuat)*:
![SS Before AntiPatternRefaktor.java](<before anti pattern refaktor java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After AntiPatternRefaktor.java](<after anti pattern refaktor java .png>)

### 1.8. File: `Main.java`
**Penjelasan Kode:**
Program penguji utama Java yang menerapkan upcasting. Menggunakan array bertipe induk BangunDatar[] untuk menampung objek Lingkaran, Persegi, Segitiga, dan Trapesium. Memproses pencetakan dan akumulasi luas secara polimorfik tanpa merubah struktur perulangan.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Starter code)*:
![SS Before Main.java](<before main java .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After Main.java](<after main java .png>)

### Output Program (Java)
![Output Java](<RUN JAVA .png>)

---

## 2. Implementasi PHP

### 2.1. File: `BangunDatar.php`
**Penjelasan Kode:**
Mengimplementasikan abstract class BangunDatar serta seluruh kelas turunannya (Lingkaran, Persegi, Segitiga, dan Trapesium) dalam skrip PHP 8 dengan declare(strict_types=1). Menggunakan magic method __toString() untuk pencetakan polimorfik dan M_PI untuk kalkulasi lingkaran.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Starter code)*:
![SS Before BangunDatar.php](<before bangundatar php .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After BangunDatar.php](<after bangun datar php 1 .png>)
![alt text](<after bangun datar php 2 .png>)
![alt text](<after bangun datar php 3.png>)

### 2.2. File: `Notifikasi.php`
**Penjelasan Kode:**
Implementasi Latihan Mandiri Langkah 6 berbasis polimorfisme. Mendefinisikan abstract class Notifikasi beserta tiga subclass (Email, SMS, WhatsApp). Fungsi kirimSemua() memproses eksekusi pengiriman pesan ke seluruh elemen array tanpa menggunakan pengecekan tipe instanceof, switch, atau match.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Starter code)*:
![SS Before Notifikasi.php](<before notifikasi php .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After Notifikasi.php](<after notifikasi php 1 .png>)
![alt text](<after notifikasi php 2 .png>)

### 2.3. File: `main.php`
**Penjelasan Kode:**
Skrip penguji utama versi PHP yang menguji pemanggilan polimorfik pada koleksi array \$daftar berisi objek bentuk bangun datar. Menghasilkan kalkulasi total luas dan format keluaran string yang identik dengan versi Java.

**Bukti Eksekusi (Screenshot):**
* **Before** *(Kondisi awal / Starter code)*:
![SS Before main.php](<before main php .png>)

* **After** *(Kondisi akhir / Eksekusi berhasil)*:
![SS After main.php](<after main php .png>)

### Output Program (PHP)
![Output PHP](<RUN PHP .png>)

---

## 3. Kesimpulan
Polimorfisme memungkinkan pemanggilan metode dengan nama yang sama pada variabel bertipe kelas induk, namun mengeksekusi perilaku yang sesuai dengan tipe asli objek subclass saat runtime. Penggunaan abstract class berfungsi menetapkan kontrak metode wajib bagi kelas-kelas turunannya. Pendekatan polimorfik terbukti jauh lebih fleksibel, bersih, dan mematuhi Open/Closed Principle jika dibandingkan dengan pola Anti-Pattern berbasis instanceof atau percabangan if-else, karena penambahan kelas baru tidak memerlukan perubahan pada kode pengolahan utama yang sudah ada.