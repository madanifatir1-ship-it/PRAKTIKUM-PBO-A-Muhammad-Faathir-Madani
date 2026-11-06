# Skenario Kegagalan — Sistem Perpustakaan

Lengkapi tabel di bawah dengan **minimal 10 skenario**. Setiap skenario harus
benar-benar Anda coba jalankan, bukan sekadar dibayangkan.

Daftar ini menjadi bahan langsung untuk unit test pada sesi 11. Susun dengan
serius — Anda sendiri yang akan memakainya pekan depan.

| No | Masukan / tindakan | Kegagalan yang diharapkan | Jenis exception | Pesan yang ditampilkan |
|----|--------------------|---------------------------|-----------------|------------------------|
| 1 | Pinjam ISBN yang tidak terdaftar | Buku tidak ditemukan | BukuTidakDitemukanException | Buku dengan ISBN [BKU-X] tidak ditemukan |
| 2 | Pinjam dengan ID anggota belum terdaftar | Anggota tidak ditemukan | AnggotaTidakDitemukanException | Anggota dengan ID [ANG-X] tidak terdaftar |
| 3 | Pinjam buku yang stoknya nol | Buku tidak tersedia | BukuSedangDipinjamException | Buku dengan ISBN [BKU-001] berjudul "Pemrograman Berorientasi Objek" sedang habis/stok tidak tersedia |
| 4 | Anggota melakukan peminjaman keempat | Batas pinjam terlampaui | BatasPinjamTerlampauiException | Anggota dengan ID [ANG-001] telah mencapai batas pinjam [3] |
| 5 | Ketik huruf saat memilih menu angka | Pilihan menu tidak valid | InputMismatchException | Pilihan menu harus berupa angka |
| 6 | Masukkan pilihan menu negatif, misalnya -1 | Pilihan di luar menu | IllegalArgumentException | Pilihan menu tidak tersedia |
| 7 | Biarkan ID anggota atau ISBN kosong | Masukan kosong ditolak | IllegalArgumentException | ID anggota dan ISBN tidak boleh kosong |
| 8 | Ketik spasi pada ISBN | Masukan kosong setelah trimming | IllegalArgumentException | ISBN tidak boleh kosong |
| 9 | Tambahkan buku dengan eksemplar -2 | Jumlah eksemplar negatif ditolak | IllegalArgumentException | Jumlah eksemplar tidak boleh negatif |
| 10 | Ekspor ke direktori yang tidak dapat ditulis | Berkas tidak dapat dibuka/ditulis | IOException / RuntimeException | Tidak dapat membuka berkas atau menulis data |
| 11 | Pinjam ISBN dan ID anggota valid di bawah batas | Peminjaman berhasil | Tidak ada exception | Peminjaman berhasil |
| 12 | Pinjam buku dua kali yang stok awalnya satu | Kegagalan pada peminjaman kedua | BukuSedangDipinjamException | Buku dengan ISBN ... sedang habis/stok tidak tersedia |

## Pertanyaan yang harus dijawab di bawah tabel

1. Mana di antara skenario Anda yang menandakan **kesalahan pengguna**, dan
   mana yang menandakan **kesalahan pemrogram**? Bagaimana perbedaan itu
   memengaruhi pilihan jenis exception-nya?

Kesalahan pengguna meliputi data tidak ditemukan, stok habis, batas pinjam,
masukan kosong, pilihan salah, dan angka negatif. Kesalahan pemrogram mencakup
gagal membuka atau menulis berkas. Kegagalan pengguna ditangani sebagai
unchecked exception dan kegagalan sumber daya sebagai `IOException`.

2. Anda memilih PerpustakaanException diturunkan dari RuntimeException atau
   dari Exception? Tuliskan alasannya.

`PerpustakaanException` diturunkan dari `RuntimeException` (unchecked) karena
empat turunan mewakili permintaan pengguna yang tidak sah dan dapat diperbaiki
tanpa deklarasi `throws` pada setiap lapisan kode.

3. (Setelah Latihan) Ceritakan apa yang terjadi ketika Anda membuat satu blok
   catch kosong dengan sengaja, lalu menjalankan skenario yang gagal.

Jika catch sengaja dikosongkan, kegagalan tidak terlihat oleh pengguna maupun
dicatat. Sistem dapat melanjutkan dengan keadaan yang tampak normal tetapi
sebenarnya gagal, sehingga sulit didiagnosis dan berisiko menghasilkan data
tidak konsisten.
