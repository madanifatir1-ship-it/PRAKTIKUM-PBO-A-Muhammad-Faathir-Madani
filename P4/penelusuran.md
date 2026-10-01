# Penelusuran Polimorfisme

## 1) Konsep utama
Polimorfisme bekerja karena objek dari kelas turunan diperlakukan sebagai objek dari kelas induk. `BangunDatar` mendefinisikan kontrak `luas()` dan `keliling()`, lalu setiap turunan mengimplementasikan caranya sendiri.

## 2) Mengapa `BangunDatar.toString()` bisa memanggil `luas()`/`keliling()`?
Karena method `toString()` dipanggil pada objek konkret, misalnya `Lingkaran` atau `Persegi`. Saat runtime, Java menggunakan versi method yang sesuai dengan tipe objek sesungguhnya, bukan tipe referensi. Ini adalah inti dari dynamic dispatch atau late binding.

## 3) Perbedaan anti-pattern vs polimorfik
Pada anti-pattern, setiap kali bentuk baru ditambahkan, kita harus mengubah `hitungLuas(Object)` dengan `else if` baru. Dengan polimorfik, cukup tambahkan kelas baru yang mewarisi `BangunDatar` dan menempelkan objek baru ke array.

## 4) Hasil pengerjaan
- `Lingkaran`: validasi jari-jari > 0, luas = `Math.PI * r * r`, keliling = `2 * Math.PI * r`
- `Persegi`: validasi sisi > 0, luas = `sisi * sisi`, keliling = `4 * sisi`
- `Segitiga`: validasi semua nilai > 0, luas = `0.5 * alas * tinggi`, keliling = `alas + tinggi + sisiMiring`
- `Trapesium`: validasi semua sisi > 0, luas = `((a + b) / 2) * tinggi`, keliling = `a + b + c + d`
