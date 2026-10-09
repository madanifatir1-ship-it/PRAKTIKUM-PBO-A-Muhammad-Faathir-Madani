# Keputusan Desain — Abstraksi, Interface, Enum, dan Trait

## 1. Kenapa Java membatasi pewarisan class, tetapi mengizinkan banyak interface?

Satu class induk memberi jalur pewarisan implementasi dan state yang jelas. Kalau sebuah class boleh mewarisi beberapa class, dua induk bisa punya method bernama sama dengan implementasi berbeda. Class anak jadi harus menebak implementasi mana yang dipakai. Ini salah satu bentuk *Diamond Problem*: class `D` mewarisi `B` dan `C`, sementara keduanya mewarisi `A` dan mungkin menimpa method yang sama dari `A`.

Karena itu Java membatasi class hanya mewarisi satu class, sehingga sumber implementasi dan state turunannya tidak ambigu. Sebaliknya, satu class boleh mengimplementasikan banyak interface karena interface utamanya mendefinisikan kontrak. Jika beberapa interface menyediakan `default method` dengan signature yang sama, Java tetap meminta class tersebut menyelesaikan konflik dengan override secara eksplisit (atau memilih implementasi melalui `InterfaceName.super`), jadi pemanggilannya tidak dibiarkan ambigu.

## 2. Kenapa Java menolak `isiPenuh(sepeda)` saat kompilasi, tetapi PHP saat runtime?

Java mengecek tipe argumen saat kompilasi. `isiPenuh` meminta `Fuelable`, sementara `Sepeda` hanya mengimplementasikan `Movable`; compiler sudah tahu bahwa `Sepeda` tidak bisa diberikan ke parameter tersebut dan menolak program sebelum dijalankan.

PHP memeriksa deklarasi tipe parameter ketika fungsi dipanggil. Karena itu berkas PHP bisa lolos pemeriksaan sintaks, tetapi pemanggilan `isiPenuh($sepeda)` melempar `TypeError` saat runtime. Analisis statis PHP tetap bisa menemukan masalah ini lebih awal, tetapi pemeriksaan bahasa pada pemanggilan terjadi ketika eksekusi mencapai fungsi.

Penolakan saat kompilasi menguntungkan karena kesalahan terdeteksi lebih awal, sebelum jalur kode yang bermasalah dijalankan. Hasilnya lebih aman dan lebih mudah dilacak, serta compiler dan IDE dapat memberi umpan balik langsung.

## 3. Pesan error dari percobaan

Keluaran relevan compiler Java setelah baris `isiPenuh(sepeda);` diaktifkan:

```text
Main.java:35: error: incompatible types: Sepeda cannot be converted to Fuelable
        isiPenuh(sepeda);
                 ^
1 error
```

PHP 8.2 menghasilkan `TypeError` berikut saat baris `isiPenuh($sepeda)` dijalankan:

```text
isiPenuh(): Argument #1 ($kendaraan) must be of type Fuelable, Sepeda given, called in C:\SEMESTER 3\PRAK PBO\PRAKTIKUM-PBO-A-Muhammad-Faathir-Madani\P6\php\main.php on line 30
```

Path tersebut adalah path absolut dari mesin saat percobaan dijalankan; lokasi dan nomor baris bisa berubah kalau folder atau isi berkas dipindahkan.

## 4. Kenapa Trait disebut penggunaan ulang horizontal?

Trait menyediakan kumpulan method yang bisa disisipkan ke beberapa class tanpa membuat class-class itu berada dalam hubungan induk-anak. `Mobil` dan `Pesanan`, misalnya, sama-sama bisa memakai `Loggable` meskipun `Pesanan` bukan turunan `Kendaraan`.

Disebut horizontal karena kemampuan tersebut dibagikan lintas class yang sejajar, bukan diwariskan secara vertikal dari satu superclass. Jadi trait membantu memakai ulang implementasi tanpa memaksakan hubungan pewarisan yang tidak sesuai.
