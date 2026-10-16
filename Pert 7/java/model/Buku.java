package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Sesi 7 — KOMPOSISI.
 *
 * Tiga hal yang membuat sebuah relasi benar-benar komposisi:
 *   1. tidak ada constructor atau method yang MENERIMA objek bagian dari luar
 *   2. objek bagian dibuat DI DALAM induk
 *   3. getter mengembalikan salinan yang tidak bisa diubah
 *
 * Lengkapi TODO sehingga ketiganya terpenuhi.
 */
public class Buku extends Koleksi {
    private final List<Bab> bab = new ArrayList<>();

    public Buku(String nomorInduk, String judul, String penulis, int jumlahEksemplar) {
        // TODO 1: tolak jumlah eksemplar negatif.
        super(nomorInduk, judul, penulis, jumlahEksemplar);
    }

    public Buku(String nomorInduk, String judul, String penulis, int tahun, int jumlahEksemplar) {
        super(nomorInduk, judul, penulis, tahun, jumlahEksemplar);
    }

    /**
     * TODO 2: satu-satunya cara Bab lahir.
     *         Perhatikan: parameternya nomor dan judul, BUKAN objek Bab.
     *         Kalau Anda menerima objek Bab dari luar, ini menjadi agregasi.
     */
    public void tambahBab(int nomor, String judulBab) {
        // TODO 2: Bab dibuat di dalam Buku untuk mempertahankan komposisi.
        bab.add(new Bab(nomor, judulBab));
    }

    /**
     * TODO 3: kembalikan salinan yang tidak bisa diubah.
     *         Petunjuk: List.copyOf(...)
     *         Coba dulu mengembalikan `bab` apa adanya, jalankan bukti-relasi.md
     *         percobaan 1, lalu perbaiki. Rasakan bedanya.
     */
    public List<Bab> getBab() {
        return List.copyOf(bab);
    }

    @Override
    public int masaPinjamHari() { return 14; }

    @Override
    public boolean bolehDipinjam() {
        return getStatus() == StatusKoleksi.TERSEDIA;
    }

    /** TODO 4: kurangi stok; tolak bila sudah nol. */
    public void kurangiStok() {
        if (getJumlahEksemplar() == 0) {
            System.out.println("Stok eksemplar = 0");
            return;
        }
        super.kurangiStok();
    }

    @Override
    public String toString() {
        return String.format("%-32s %-16s [%d eks, %d bab]",
                getJudul(), getPenulis(), getJumlahEksemplar(), bab.size());
    }

    /**
     * Bab adalah BAGIAN dari Buku — kelasnya bersarang untuk menegaskan itu,
     * dan constructor-nya privat agar hanya Buku yang boleh membuatnya.
     */
    public static final class Bab {
        private final int nomor;
        private final String judul;

        private Bab(int nomor, String judul) {
            this.nomor = nomor;
            this.judul = judul;
        }

        public int getNomor()    { return nomor; }
        public String getJudul() { return judul; }

        @Override public String toString() { return "Bab " + nomor + ": " + judul; }
    }
}
