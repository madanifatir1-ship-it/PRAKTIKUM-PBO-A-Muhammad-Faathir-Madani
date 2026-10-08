# Keputusan Langkah 4

Pemanggilan `isiPenuh(sepeda)` menghasilkan kesalahan kompilasi berikut:

```text
java\Main.java:32: error: incompatible types: Sepeda cannot be converted to Fuelable
        isiPenuh(sepeda);
                 ^
1 error
```

`isiPenuh` menerima parameter bertipe `Fuelable`, sedangkan `Sepeda` hanya mengimplementasikan `Movable`. Penolakan saat kompilasi menguntungkan karena kesalahan penggunaan kontrak diketahui sebelum program berjalan; program tidak dapat mencoba mengisi bahan bakar pada objek yang memang tidak mendukungnya. Contoh pemanggilan tersebut tetap dikomentari di `Main.java` agar seluruh program dapat dikompilasi.

## Pewarisan

Java membatasi pewarisan class menjadi satu agar state dan implementasi yang diwarisi tidak menimbulkan konflik atau ambiguitas, misalnya saat dua superclass menyediakan implementasi berbeda untuk method yang sama. Sebuah class tetap dapat mengimplementasikan banyak interface karena interface menyatakan kontrak kemampuan, bukan mewariskan state class. Karena itu `Mobil` dapat mewarisi `Kendaraan` sekaligus memenuhi kontrak `Movable` dan `Fuelable`.
