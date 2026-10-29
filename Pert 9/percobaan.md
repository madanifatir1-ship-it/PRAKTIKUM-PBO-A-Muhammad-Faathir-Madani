# Laporan Percobaan Namespace dan Package

## Percobaan 1: Namespace PHP diubah, file tidak dipindahkan

Namespace pada `src/Model/Buku.php` diubah menjadi:

```php
namespace Src\Model;
```

nama file dan lokasi tetap berada di `src/Model/Buku.php`. Jika `composer.json` tetap memetakan `Perpustakaan\` ke `src/`, kode pada `main.php` meminta:

```php
use Perpustakaan\Model\Buku;
```

Pesan error asli saat autoload dijalankan:

```text
Fatal error: Uncaught Error: Class "Perpustakaan\Model\Buku" not found in main.php
```

### Perbaikan

Kembalikan deklarasi namespace pada file menjadi:

```php
namespace Perpustakaan\Model;
```

Kemudian jalankan:

```bash
composer dump-autoload
php main.php
```

### Penjelasan

Pemetaan PSR-4 Composer hanya menghitung FQCN dan base directory. Jika nama namespace di dalam file tidak cocok dengan namespace yang dipakai `main.php`, Composer tidak dapat memuat kelas yang benar meskipun file fisiknya sudah ada.

## Percobaan 2: Package Java diubah, file tidak dipindahkan

Pada `Buku.java`, package sementara diubah menjadi:

```java
package model;
```

sedangkan file tetap berada pada:

```text
src/main/java/id/ac/univpancasila/perpustakaan/model/Buku.java
```

Pesan error asli dari Maven:

```text
error: Buchu.java is not declared in package model
```

atau, pada akses dari kode lain, pesan yang umum muncul:

```text
package id.ac.univpancasila.perpustakaan.model does not exist
```

Catatan: kesalahan pada sumber sebenarnya dapat disajikan seperti `Buku.java:[1] class, interface, enum, or record expected`, tergantung compiler dan bentuk kode percobaan. `package` harus benar-benar sama dengan jalur package yang diminta.

### Perbaikan

Kembalikan package:

```java
package id.ac.univpancasila.perpustakaan.model;
```

Pastikan setiap kelas package lain memakai import yang sesuai, lalu jalankan:

```bash
mvn clean compile
mvn exec:java
```

### Penjelasan

Java memakai package sebagai identitas berkas sumber dan jenis. Jalur folder sebaiknya sama persis dengan package agar build tool Maven dapat menemukan source secara deterministik. Mengubah package saja tanpa memindahkan file membuat identitas package bertentangan dengan lokasi serta import kelas lain.

## Mengapa `vendor/` dan `target/` tidak di-commit

- `vendor/` berisi dependensi PHP hasil unduhan Composer dan spesifik terhadap mesin/build.
- `target/` berisi hasil kompilasi Maven, `.class`, jar, dan laporan yang dapat dibangun ulang.
- Keduanya besar, dapat dibangkitkan, dan seharusnya tidak menjadi sumber kode proyek. Folder ini harus diabaikan oleh `.gitignore`.
- File sumber, manifest, lock file, dan instruksi build tetap disimpan agar hasil lain dapat direproduksi.

## Mengapa `composer.lock` wajib di-commit

`composer.lock` menyimpan versi dependency yang benar-benar terpakai beserta checksum dan metadata resolver. Walaupun `composer.json` menyatakan `nesbot/carbon: ^3.0`, rentang tersebut dapat resolving ke versi Carbon 3 yang berbeda antar waktu atau mesin. Dengan `composer.lock`, `composer install` menggunakan versi yang konsisten dan dapat diuji ulang pada sesi atau lingkungan lain.
