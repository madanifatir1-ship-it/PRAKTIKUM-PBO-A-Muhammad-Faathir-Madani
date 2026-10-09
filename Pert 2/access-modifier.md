# Alasan Access Modifier

## Mahasiswa

- `nim`: `private final` di Java dan `private readonly` di PHP; identitas tidak boleh dibaca atau diganti langsung dari luar objek, dan tidak disediakan setter.
- `nama`: privat agar akses atribut tetap dikendalikan objek; hanya getter yang disediakan.
- `nilaiTugas`, `nilaiUts`, `nilaiUas`: privat agar perubahan selalu melewati setter yang memvalidasi rentang 0 sampai 100.
- Konstanta bobot: `public static final`/konstanta kelas karena nilainya tetap dan dapat dipakai pemanggil sebagai informasi aturan; batas nilai tetap privat karena hanya dipakai untuk validasi internal.

## Buku

- `isbn`: privat dan final/readonly karena menjadi identitas buku yang tidak berubah setelah registrasi.
- `judul` dan `penulis`: privat agar data hanya dapat dibaca lewat getter dan tidak dapat diubah sembarangan.
- `jumlahEksemplar`: privat dan tetap, sebagai batas awal jumlah salinan.
- `jumlahTersedia`: privat agar perubahan stok hanya terjadi melalui `pinjam()` dan `kembalikan()` yang menjaga invariant.

## Jawaban Tugas Rumah (150 Kata)

Method pinjam() lebih tepat daripada setJumlahEksemplar() karena peminjaman adalah peristiwa bisnis, bukan perintah untuk mengganti angka internal. Dengan pinjam(), objek Buku memeriksa apakah salinan tersedia, menolak peminjaman ketika stok habis, lalu mengurangi jumlah tersedia secara terkendali. Setter publik memungkinkan pemanggil mengubah jumlah menjadi nilai negatif, mengembalikan stok secara tidak sah, atau menghapus jejak bahwa buku sedang dipinjam. Hal itu merusak invariant dan membuat setiap pemanggil harus mengulang validasi yang sama. Pasangan kembalikan() juga menyatakan peristiwa yang jelas; objek dapat menolak pengembalian jika tidak ada peminjaman aktif, sehingga stok tidak melampaui jumlah awal. Antarmuka ini membuat niat pemanggil mudah dipahami dan menjaga tanggung jawab aturan tetap di dalam Buku. Jika kelak ada pencatatan tanggal, batas durasi, atau identitas peminjam, logika dapat ditambahkan pada pinjam() tanpa mengubah cara kode klien meminta transaksi. Jadi API berbasis perilaku lebih aman, mudah diuji, serta lebih tahan terhadap perubahan dibanding setter yang mengekspos keadaan secara langsung.
