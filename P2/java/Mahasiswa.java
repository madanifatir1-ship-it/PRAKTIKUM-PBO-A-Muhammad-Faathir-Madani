/**
 * Sesi 2 — enkapsulasi yang menjaga invariant.
 *
 * Deskripsi masalah:
 *   "Sistem akademik mencatat mahasiswa dengan NIM, nama, dan tiga komponen
 *    nilai: tugas, UTS, dan UAS. NIM tidak pernah berubah setelah mahasiswa
 *    terdaftar. Setiap komponen nilai berada dalam rentang 0 sampai 100.
 *    Nilai akhir dihitung 30% tugas, 30% UTS, 40% UAS."
 *
 * Tuliskan lebih dulu daftar invariant-nya di analisis.md,
 * baru lengkapi TODO di bawah ini.
 */
public class Mahasiswa {

    // Konstanta bobot — jangan menulis angka 0.30 dan 0.40 di dalam method.
    public static final double BOBOT_TUGAS = 0.30;
    public static final double BOBOT_UTS   = 0.30;
    public static final double BOBOT_UAS   = 0.40;

    private static final double NILAI_MIN = 0;
    private static final double NILAI_MAX = 100;

    // TODO 1: Identitas tidak berubah; komponen nilai hanya berubah melalui setter tervalidasi.
    private final String nim;
    private final String nama;
    private double nilaiTugas;
    private double nilaiUts;
    private double nilaiUas;

    public Mahasiswa(String nim, String nama, double nilaiTugas, double nilaiUts, double nilaiUas) {
        // TODO 2: Tolak NIM null atau kosong dengan pesan yang menjelaskan kesalahannya.
        if (nim == null || nim.trim().isEmpty()) {
            throw new IllegalArgumentException("NIM tidak boleh null atau kosong.");
        }

        // TODO 3: Validasi seluruh nilai sebelum menyimpan objek.
        pastikanNilaiSah("nilai tugas", nilaiTugas);
        pastikanNilaiSah("nilai UTS", nilaiUts);
        pastikanNilaiSah("nilai UAS", nilaiUas);

        this.nim = nim;
        this.nama = nama;
        this.nilaiTugas = nilaiTugas;
        this.nilaiUts = nilaiUts;
        this.nilaiUas = nilaiUas;
    }

    // TODO 4: Tolak nilai non-finite atau di luar rentang 0 sampai 100.
    private static void pastikanNilaiSah(String namaKomponen, double nilai) {
        if (!Double.isFinite(nilai) || nilai < NILAI_MIN || nilai > NILAI_MAX) {
            throw new IllegalArgumentException(
                    namaKomponen + " harus berada di antara 0 dan 100.");
        }
    }


    /**
     * Nilai akhir memakai konstanta bobot yang ditentukan kelas.
     */
    public double nilaiAkhir() {
        // TODO 5: Hitung nilai akhir berbobot.
        return nilaiTugas * BOBOT_TUGAS + nilaiUts * BOBOT_UTS + nilaiUas * BOBOT_UAS;
    }

    // TODO 6: Kembalikan huruf mutu berdasarkan nilai akhir: >=80 A, >=70 B, >=60 C, >=50 D, selain itu E.
    public String hurufMutu() {
        double akhir = nilaiAkhir();
        if (akhir >= 80) return "A";
        if (akhir >= 70) return "B";
        if (akhir >= 60) return "C";
        if (akhir >= 50) return "D";
        return "E";
    }

    // ── Getter ────────────────────────────────────────────────
    // TODO 7: Sediakan getter identitas, setiap komponen nilai, dan nilai akhir; jangan sediakan setNim().
    public String getNim()  { return nim; }
    public String getNama() { return nama; }
    public double getNilaiTugas() { return nilaiTugas; }
    public double getNilaiUts() { return nilaiUts; }
    public double getNilaiUas() { return nilaiUas; }
    public double getNilaiAkhir() { return nilaiAkhir(); }

    public void setNilaiTugas(double nilaiTugas) {
        pastikanNilaiSah("nilai tugas", nilaiTugas);
        this.nilaiTugas = nilaiTugas;
    }

    public void setNilaiUts(double nilaiUts) {
        pastikanNilaiSah("nilai UTS", nilaiUts);
        this.nilaiUts = nilaiUts;
    }

    public void setNilaiUas(double nilaiUas) {
        pastikanNilaiSah("nilai UAS", nilaiUas);
        this.nilaiUas = nilaiUas;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-18s akhir=%6.2f  mutu=%s",
                nim, nama, nilaiAkhir(), hurufMutu());
    }
}
