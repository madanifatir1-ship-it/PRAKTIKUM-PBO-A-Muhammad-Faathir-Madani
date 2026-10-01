1. Percobaan Langkah 1: saat memanggil `new Pegawai(...)` sebagai kelas abstract, Java menolak karena `Pegawai` tidak dapat diinstansiasi.
2. Pada `PegawaiTetap`, super() harus dipanggil sebelum access field lain karena Java mewajibkan constructor induk dijalankan terlebih dahulu.
3. `PegawaiKontrak` tidak perlu override `hitungGaji()` karena ia tidak mendapat tunjangan masa kerja, dan perilaku dasar dari `Pegawai` sudah sesuai: gaji pokok apa adanya.
4. Untuk `PegawaiTetap`, tunjangan masa kerja dihitung dari persentase gaji pokok per tahun dengan batas maksimum 40%.
