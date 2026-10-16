# Analisis Relasi Objek

## Hasil analisis kebutuhan

Calon kelas domain adalah Koleksi, Buku, Majalah, Skripsi, Bab, Anggota, Peminjaman, Jurusan, Mahasiswa, PerpustakaanService, StatusKoleksi, dan JenisKeanggotaan. Nomor induk, judul, penulis, tahun, jumlah eksemplar, tanggal, dan denda merupakan atribut karena nilainya melekat pada objek domain lain.

Kata kerja yang menjadi operasi antara lain menambah koleksi, mendaftarkan anggota, mencari koleksi, meminjam, mengembalikan, mengurangi stok, menambah stok, serta mengelola bab dan mahasiswa. Invariant yang diterapkan adalah stok tidak negatif, peminjaman membutuhkan koleksi yang bisa dipinjam dan memiliki stok, skripsi hanya dibaca di tempat, anggota dibatasi tiga peminjaman aktif, nomor anggota tetap, dan satu peminjaman mencatat tepat satu anggota serta satu koleksi.

## Komposisi dan agregasi

Komposisi digunakan pada Buku dan Bab. Bab dibuat di dalam Buku melalui tambahBab; pemanggil tidak dapat memasukkan objek Bab dari luar. Constructor Bab di Java bersifat privat dan Bab dibuat oleh Buku. Getter mengembalikan List.copyOf sehingga koleksi internal tidak dapat dikosongkan atau diubah oleh pemanggil. Dalam PHP, Bab tidak dapat dijadikan nested class seperti Java. Bab dibuat hanya melalui tambahBab berdasarkan konvensi; propertinya readonly dan getBab mengembalikan array berdasarkan nilai.

Agregasi digunakan pada Jurusan dan Mahasiswa. Jurusan menerima objek Mahasiswa yang sudah dibuat dari luar dan hanya mengelola keanggotaannya. Mengeluarkan mahasiswa dari daftar tidak mematikan atau menghapus objek itu. PerpustakaanService juga mengagregasi Anggota dan Koleksi: kedua objek didaftarkan dari luar dan keberadaannya tidak bergantung pada umur layanan. Relasi Anggota dengan Peminjaman ditampilkan sebagai agregasi pada diagram, sementara Peminjaman menyimpan referensi tepat ke seorang anggota dan satu koleksi.

Asosiasi Peminjaman-Koleksi menjelaskan bahwa setiap peminjaman menunjuk satu koleksi, sedangkan sebuah koleksi dapat muncul pada banyak catatan peminjaman dari waktu ke waktu. Pewarisan dipakai karena Buku, Majalah, dan Skripsi merupakan jenis khusus Koleksi dengan masa pinjam berbeda.

## Semantik nilai dan referensi

Java selalu meneruskan argumen berdasarkan nilai. Untuk objek, nilai yang disalin adalah nilai referensi, sehingga method dapat mengakses objek yang sama, tetapi menetapkan ulang parameter tidak mengganti variabel milik pemanggil. List.copyOf pada Buku dan Jurusan memberikan daftar yang tidak dapat dimodifikasi; elemen di dalamnya tetap merujuk pada objek yang sama.

PHP juga meneruskan argumen secara default berdasarkan nilai. Untuk objek, yang disalin adalah handle objek sehingga pemanggil dan method merujuk objek yang sama; keyword reference (&) tidak dibutuhkan untuk memanggil method atau mengubah state objek. Array dikembalikan berdasarkan nilai dengan copy-on-write. Dengan demikian perubahan struktur array hasil getBab atau getMahasiswa tidak mengubah array internal, sementara objek di dalam array tetap berupa referensi objek. Properti readonly pada Bab melindungi state bagian komposisi.

## Aturan peminjaman dan denda

Buku dapat dipinjam 14 hari, majalah 3 hari, dan skripsi tidak dapat dipinjam. Peminjaman menghitung tanggal jatuh tempo dari tanggal pinjam ditambah durasi koleksi. Pengembalian sesudah jatuh tempo menghasilkan denda per hari Rp1.000 untuk mahasiswa, Rp500 untuk dosen, dan Rp0 untuk anggota kehormatan. Koleksi rusak atau hilang ditolak oleh aturan bolehDipinjam. Anggota yang memiliki tiga peminjaman aktif tidak dapat meminjam lagi sampai ada pengembalian.

## Menjalankan contoh

Dari folder Pert 7, compile seluruh sumber Java dengan JDK 16 atau lebih baru:

```text
javac -d out java\Main.java java\model\*.java java\service\*.java
java -cp out Main
```

Jalankan implementasi PHP 8.1 atau lebih baru dengan:

```text
php php\main.php
```

File UML dapat dirender dengan PlantUML:

```text
plantuml uml\perpustakaan-kerangka.puml
```
