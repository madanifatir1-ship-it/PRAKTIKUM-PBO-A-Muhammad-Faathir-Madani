/**
 * Sesi 3 — constructor berdelegasi, anggota statis, dan konstanta.
 *
 * Invariant:
 *   1. saldo tidak pernah negatif
 *   2. nomor rekening tidak berubah setelah objek dibuat
 *   3. setoran dan penarikan selalu bernilai positif
 */
public class RekeningBank {

    // TODO 1: Deklarasikan konstanta bunga, biaya admin, dan batas penarikan.
    public static final double BUNGA_TAHUNAN = 0.025;
    public static final double BIAYA_ADMIN = 5000.0;
    public static final double BATAS_PENARIKAN = 5000000.0;

    // TODO 2: Deklarasikan penghitung rekening statis yang privat.
    private static int jumlahRekening = 0;

    private final String nomor;
    private String pemilik;
    private double saldo;

    /**
     * Constructor ringkas.
     */
    public RekeningBank(String nomor, String pemilik) {
        // TODO 3: Delegasikan constructor ringkas ke constructor lengkap.
        this(nomor, pemilik, 0);
    }

    /** Constructor lengkap — SATU-SATUNYA tempat validasi berada. */
    public RekeningBank(String nomor, String pemilik, double saldoAwal) {
        // TODO 4: Validasi nomor, pemilik, dan saldo awal sebelum menyimpan atribut.
        if (nomor == null || nomor.trim().isEmpty()) {
            throw new IllegalArgumentException("Nomor rekening tidak boleh kosong.");
        }
        if (pemilik == null || pemilik.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama pemilik tidak boleh kosong.");
        }
        if (!Double.isFinite(saldoAwal) || saldoAwal < 0) {
            throw new IllegalArgumentException("Saldo awal harus finite dan tidak boleh negatif.");
        }

        this.nomor = nomor.trim();
        this.pemilik = pemilik.trim();
        this.saldo = saldoAwal;

        // TODO 5: Naikkan penghitung setelah validasi rekening selesai.
        jumlahRekening++;
    }

    public void setor(double jumlah) {
        // TODO 6: Validasi jumlah setor dan saldo hasil agar tetap finite.
        validasiJumlahPositif(jumlah);
        double saldoBaru = saldo + jumlah;
        if (!Double.isFinite(saldoBaru)) {
            throw new IllegalArgumentException("Saldo setelah setor harus finite.");
        }
        saldo = saldoBaru;
    }

    public void tarik(double jumlah) {
        // TODO 7: Tolak jumlah tidak valid, di atas batas, atau melebihi saldo.
        validasiJumlahPositif(jumlah);
        if (jumlah > BATAS_PENARIKAN) {
            throw new IllegalArgumentException("Penarikan melebihi batas sekali tarik.");
        }
        if (jumlah > saldo) {
            throw new IllegalStateException("Saldo tidak mencukupi.");
        }
        saldo -= jumlah;
    }

    public void potongBiayaAdmin() {
        // TODO 8: Kurangi biaya admin jika saldo cukup; jika tidak, jangan lakukan apa pun.
        if (saldo >= BIAYA_ADMIN) {
            saldo -= BIAYA_ADMIN;
        }
    }

    public static int getJumlahRekening() {
        // TODO 9: Kembalikan jumlah seluruh rekening.
        return jumlahRekening;
    }

    /**
     */
    public static double bungaSetahun(double pokok) {
        // TODO 10: Validasi pokok dan hasil perhitungan bunga tahunan.
        if (!Double.isFinite(pokok) || pokok < 0) {
            throw new IllegalArgumentException("Pokok harus finite dan tidak boleh negatif.");
        }
        double bunga = pokok * BUNGA_TAHUNAN;
        if (!Double.isFinite(bunga)) {
            throw new IllegalArgumentException("Hasil perhitungan bunga harus finite.");
        }
        return bunga;
    }

    private static void validasiJumlahPositif(double jumlah) {
        if (!Double.isFinite(jumlah) || jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah harus finite dan lebih besar dari nol.");
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNomor() {
        return nomor;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        if (pemilik == null || pemilik.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama pemilik tidak boleh kosong.");
        }
        this.pemilik = pemilik.trim();
    }

    @Override
    public String toString() {
        return String.format("Rekening[%s] %-14s Rp%,.2f", nomor, pemilik, saldo);
    }
}
