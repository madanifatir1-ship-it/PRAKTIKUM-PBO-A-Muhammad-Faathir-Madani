# Keputusan Langkah 4

Saat `isiPenuh($sepeda)` dijalankan, PHP menghasilkan:

```text
TypeError: isiPenuh(): Argument #1 ($kendaraan) must be of type Fuelable, Sepeda given
```

Fungsi `isiPenuh` mensyaratkan objek `Fuelable`, sementara `Sepeda` hanya mengimplementasikan `Movable`. PHP menolak argumen yang tidak sesuai sebelum isi fungsi dijalankan, sehingga operasi pengisian bahan bakar tidak dilakukan pada objek yang tidak mendukungnya. Pemanggilan uji tersebut dikomentari di `main.php` agar demo dapat menampilkan bagian enum dan trait sampai selesai.
