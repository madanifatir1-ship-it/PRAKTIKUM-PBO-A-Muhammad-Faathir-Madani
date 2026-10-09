# Bukti Enkapsulasi

## 1. Mengakses atribut privat dari luar kelas

Percobaan pada Java `Main`: membaca `probe.nilaiTugas`.

Hasil: gagal saat kompilasi.

```text
java\Main.java:8: error: nilaiTugas has private access in Mahasiswa
        System.out.println(probe.nilaiTugas);
                                ^
```

## 2. Membuat objek dengan nilai 150

Percobaan: `new Mahasiswa("2024004", "Salah Nilai", 150, 80, 80)`.

Hasil: objek menolak data saat program berjalan melalui exception. Pesan yang ditampilkan:

```text
Ditolak: nilai tugas harus berada di antara 0 dan 100.
```

Setter komponen nilai memakai validasi yang sama dan juga menolak nilai 150.

## 3. Mengubah NIM setelah objek dibuat

Percobaan pada Java `Main`: menjalankan `probe.nim = "2024999"`.

Hasil: gagal saat kompilasi karena atribut privat tidak dapat diakses dari luar kelas.

```text
java\Main.java:9: error: nim has private access in Mahasiswa
        probe.nim = "2024999";
             ^
```

Tidak ada `setNim()`; objek tidak menyediakan operasi untuk mengganti identitasnya.
