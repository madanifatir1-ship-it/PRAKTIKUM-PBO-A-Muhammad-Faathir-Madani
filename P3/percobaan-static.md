# Laporan Percobaan Anggota Statis (Sesi 03)

## Hasil Percobaan Langkah 3
1. **Mencetak `jumlahRekening` lewat nama kelas:**
   Pemanggilan `RekeningBank.getJumlahRekening()` berhasil dipanggil langsung via nama kelas tanpa perlu inisiasi objek (`new`). Nilai yang keluar adalah total rekening yang sudah dibuat.

2. **Mengakses `this` di dalam method static:**
   Hasil kompilasi gagal atau error dengan pesan:
   `non-static variable this cannot be referenced from a static context`
   Penyebabnya karena keyword `this` merepresentasikan instans objek tertentu, sedangkan method `static` menempel di level kelas dan tidak terikat pada objek mana pun.

3. **Mengubah atribut `saldo` menjadi static:**
   Saat atribut `saldo` diubah menjadi `static`, semua objek rekening berbagi satu variabel saldo yang sama. Akun Ani dan Budi yang awalnya punya saldo berbeda akhirnya menampilkan saldo terakhir yang dimasukkan. Hal ini membuktikan nilai variabel static dipakai bersama oleh seluruh objek.

---

## Tugas Rumah 2: Analisis Pengujian pada Anggota Statis
Penggunaan anggota `static` (khususnya field data) membuat kode sulit diuji dalam pengujian otomatis (*unit testing*) karena menciptakan *global state*. Nilai pada variabel static terus tersimpan di memori sepanjang aplikasi berjalan, sehingga satu skenario uji bisa merusak hasil pengujian lainnya (*side effect*).

Sebagai contoh pada tugas ini, field `jumlahRekening` bertipe static. Jika modul pengujian pertama membuat 3 rekening, nilai `jumlahRekening` bernilai 3. Ketika pengujian kedua dijalankan untuk menguji pembuatan 1 rekening baru dengan ekspektasi hasil 1, pengujian tersebut otomatis gagal (*fail*) karena nilai counter sudah terakumulasi menjadi 4. Pengembang terpaksa membuat method tambahan hanya untuk me-reset nilai variabel static setiap kali sesi pengujian selesai.