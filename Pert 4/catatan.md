# Catatan Praktikum P4 — Pewarisan

## Percobaan membuat objek kelas abstrak

Java menolak `new Pegawai("X", "Y", 1000)` karena Pegawai adalah kelas
abstrak:

```text
error: Pegawai is abstract; cannot be instantiated
```

PHP juga melempar `Error` dengan pesan `Cannot instantiate abstract class
Pegawai` bila kelas abstrak dibuat langsung. Ini perilaku yang benar: objek
dibuat dari kelas konkret seperti PegawaiTetap atau PegawaiKontrak.

## Percobaan constructor tanpa `super(...)`

Constructor Pegawai tidak memiliki constructor tanpa argumen. Karena itu,
constructor kelas turunan yang tidak memanggil `super(nip, nama, gajiPokok)`
gagal dikompilasi. Pesan Java yang diamati:

```text
constructor Pegawai in class Pegawai cannot be applied to given types;
  required: String,String,double
  found:    no arguments
  reason: actual and formal argument lists differ in length
```

Pemanggilan `super(...)` harus menjadi pernyataan pertama constructor
PegawaiTetap, PegawaiKontrak, PegawaiHarian, dan PegawaiParuhWaktu. Dosen
meneruskannya melalui constructor PegawaiTetap.

## Memakai `super.hitungGaji()`

PegawaiTetap menghitung gaji dengan mengambil gaji dasar dari
`super.hitungGaji()` lalu menambahkan persentase masa kerja: 2% per tahun,
maksimum 40%. Untuk 15 tahun dan gaji dasar Rp6.000.000, hasilnya
Rp7.800.000. Jika rumus dasar kelas induk berubah, subclass tetap memakai
perhitungan terbaru tanpa menduplikasi rumus. Menyalin rumus induk ke setiap
subclass membuat perubahan kebijakan mudah tidak konsisten.

Eksperimen sementara mengubah gaji dasar induk dari `gajiPokok` menjadi
`gajiPokok + 1.000`, tanpa mengubah PegawaiTetap. Untuk pokok Rp6.000.000 dan
masa kerja 15 tahun, implementasi yang memanggil `super.hitungGaji()`
menghasilkan Rp7.801.300,00. Versi pembanding yang menyalin rumus lama
menghasilkan Rp7.800.000,00. Eksperimen membuktikan bahwa pemanggilan `super`
mewarisi perubahan perhitungan dasar, sementara rumus salinan menjadi usang.

## Alasan pewarisan dan komposisi

"Dosen adalah PegawaiTetap" sesuai karena dosen memiliki gaji dasar dan masa
kerja pegawai tetap, lalu menambahkan jabatan serta tunjangan fungsional.
PegawaiKontrak, PegawaiHarian, dan PegawaiParuhWaktu adalah Pegawai, tetapi
masing-masing menghitung pembayaran dengan aturan berbeda. Mobil bukan
SumberTenaga: mobil memiliki sumber tenaga. TODO pada kedua file
KomposisiVsWarisan meminta contoh komposisi yang memungkinkan objek Mobil
yang sama berganti dari MesinBensin menjadi MotorListrik saat program berjalan.
