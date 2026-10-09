# Analisis Sesi 2: Enkapsulasi

## Kelas Mahasiswa

### Atribut
- `nim`: identitas mahasiswa, tidak boleh berubah setelah objek dibuat.
- `nama`: nama mahasiswa.
- `nilaiTugas`, `nilaiUts`, `nilaiUas`: komponen nilai yang dapat diperbarui melalui setter tervalidasi.

### Perilaku
- Membuat mahasiswa dari NIM, nama, dan tiga nilai.
- Mengambil identitas dan setiap nilai melalui getter.
- Memperbarui komponen nilai melalui setter.
- Menghitung nilai akhir dengan bobot tugas 30%, UTS 30%, dan UAS 40%.
- Menentukan huruf mutu berdasarkan nilai akhir.

### Invariant
1. NIM tidak null dan tidak kosong setelah spasi di awal/akhir diabaikan.
2. NIM tidak dapat diubah setelah objek dibuat.
3. Nilai tugas, UTS, dan UAS masing-masing finite dan berada pada rentang 0 sampai 100, termasuk batasnya.
4. Nilai akhir selalu dihitung dari komponen nilai dengan bobot 30%, 30%, dan 40%.

## Kelas Buku

### Atribut
- `isbn`: identitas buku, tidak boleh berubah setelah objek dibuat.
- `judul` dan `penulis`: informasi buku.
- `jumlahEksemplar`: jumlah salinan yang tersedia untuk dipinjam.

### Perilaku
- Membuat buku dengan data awal yang tervalidasi.
- Mengambil data buku melalui getter.
- Meminjam satu eksemplar jika masih tersedia.
- Mengembalikan satu eksemplar yang sebelumnya dipinjam.

### Invariant
1. ISBN tidak null dan tidak kosong setelah spasi di awal/akhir diabaikan, serta tidak dapat diubah.
2. Judul dan penulis tidak null atau kosong setelah spasi di awal/akhir diabaikan.
3. Jumlah eksemplar tidak pernah negatif.
4. Peminjaman hanya berhasil jika jumlah eksemplar tersedia lebih dari nol.
5. Pengembalian hanya berhasil jika ada peminjaman yang belum dikembalikan; jumlah tersedia tidak boleh melampaui jumlah awal.
