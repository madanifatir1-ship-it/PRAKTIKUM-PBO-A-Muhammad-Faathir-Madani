# Penelusuran Polimorfisme

## 1. `toString()` memanggil `luas()` milik turunan

Variabel `b` di perulangan `Main` bertipe `BangunDatar`, tetapi objek yang ditunjuknya bisa berupa `Lingkaran`, `Persegi`, `Segitiga`, atau `Trapesium`. Saat `toString()` milik `BangunDatar` memanggil `luas()`, Java memilih implementasi berdasarkan tipe objek yang sedang ditunjuk, bukan tipe variabelnya. Proses ini disebut *late binding* atau *dynamic dispatch*. Jadi, untuk objek `Segitiga`, yang berjalan adalah `Segitiga.luas()`; untuk `Lingkaran`, yang berjalan adalah `Lingkaran.luas()`. Pemanggilan `keliling()` di `toString()` bekerja dengan cara yang sama.

## 2. Menambah bangun datar: anti-pattern vs polimorfik

Di `AntiPattern`, tipe baru perlu ditambahkan sebagai data, cabang baru di `hitungLuas(Object)`, dan satu objek di `daftar`. Untuk contoh sederhana ini berarti sekitar lima baris kode di sedikitnya dua bagian yang sudah ada (satu deklarasi tipe, tiga baris cabang, dan satu baris daftar). Kalau cabangnya kelupaan, tipe itu ditolak sebagai bangun yang tidak dikenal.

Di versi polimorfik, implementasi bentuk baru ditaruh di kelas turunannya sendiri. Kode perhitungan dan perulangan yang sudah ada tidak perlu disentuh; `Main` hanya perlu satu baris tambahan untuk memasukkan objeknya ke `daftar`. Jadi, perubahan di kode pemakai cukup satu baris dan tidak perlu menambah cabang pemilihan tipe.

## 3. Mengapa perhitungan ada di kelas bentuknya

Setiap bentuk punya rumus dan data yang berbeda. Kelas masing-masing sudah memegang data yang diperlukan, sehingga kelas itulah yang paling paham cara menghitung luasnya. Dengan meletakkan logika tersebut di dalam kelas terkait, pemanggil cukup memanggil `luas()` tanpa mengenali jenis objek atau menyimpan rantai kondisi. Kalau bentuk baru ditambahkan, rumusnya ikut berada di kelas baru itu dan tidak perlu mengubah logika untuk bentuk-bentuk lain.
