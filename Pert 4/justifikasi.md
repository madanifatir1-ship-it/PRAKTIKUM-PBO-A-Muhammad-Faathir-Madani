# Justifikasi Pewarisan dan Komposisi

Pewarisan pada hierarki ini digunakan hanya ketika hubungan "adalah" benar
secara konsep. `PegawaiTetap` adalah `Pegawai`: ia mempunyai identitas dan
gaji dasar yang sama, sementara perhitungan gajinya menambahkan tunjangan
masa kerja. `PegawaiKontrak` juga adalah `Pegawai`; status kontrak dan lama
kontrak membedakannya, tetapi ia tetap dapat diperlakukan sebagai pegawai dan
menggunakan perhitungan gaji dasar. Karena itu, keduanya dapat dimasukkan ke
dalam satu kumpulan bertipe `Pegawai`.

`Dosen` adalah `PegawaiTetap`, bukan sekadar memiliki pegawai tetap. Dosen
mewarisi masa kerja dan aturan tunjangan yang melekat pada pegawai tetap,
kemudian menambahkan jabatan serta tunjangan fungsional. Method
`hitungGaji()` Dosen memperluas hasil induknya dengan memanggil
`super.hitungGaji()` atau `parent::hitungGaji()`. Dengan demikian, perubahan
perhitungan gaji tetap pada induk ikut digunakan oleh dosen.

`PegawaiHarian` dan `PegawaiParuhWaktu` masing-masing adalah `Pegawai`.
Keduanya membawa identitas pegawai dan dapat dicetak dalam rekap yang sama,
tetapi aturan upahnya berbeda: tarif per hari dikalikan hari kerja untuk
pegawai harian, sedangkan tarif per jam dikalikan jam kerja untuk pegawai
paruh waktu. Kedua kelas meng-override `hitungGaji()`, sehingga pemanggilan
method melalui referensi `Pegawai` tetap memilih perhitungan yang sesuai
dengan objek nyata. Inilah polimorfisme; kode rekap tidak perlu memeriksa tipe
setiap objek secara manual.

Relasi yang sengaja tidak dibuat dengan pewarisan adalah `Mobil` terhadap
sumber tenaga. Kalimat "mobil adalah mesin bensin" atau "mobil adalah motor
listrik" tidak benar; mobil memiliki sumber tenaga. Karena itu, solusi yang diminta adalah membuat `Mobil`
menyimpan objek yang memenuhi abstraksi `SumberTenaga` sebagai komponen dan
mendelegasikan operasi berjalan kepadanya. Komponen itu dapat diganti saat
program berjalan, misalnya dari `MesinBensin` ke `MotorListrik`, tanpa membuat
objek mobil baru. Kedua file `KomposisiVsWarisan` sengaja dibiarkan sebagai
latihan TODO untuk mengubah pewarisan yang keliru tersebut menjadi komposisi.
Jika sumber tenaga dijadikan superclass mobil, jenis tenaga akan terkunci
pada kelas mobil saat objek dibuat dan relasi is-a-nya keliru.

Dengan demikian, setiap `extends` pada hierarki pegawai menyatakan substitusi
yang masuk akal, sedangkan variasi yang dapat berubah selama hidup objek
ditangani dengan komposisi. Kelas induk membatasi akses data bersama dengan
`protected`, menyediakan constructor tervalidasi, dan menjadi tempat perilaku
dasar yang dipakai ulang. Kelas turunan menambahkan aturan khusus tanpa
menyalin implementasi induk. Struktur ini memudahkan penambahan tipe pegawai
baru: buat turunan, implementasikan `jenis()` dan `hitungGaji()` bila aturannya
berbeda, lalu tambahkan objeknya ke daftar polimorfik.
