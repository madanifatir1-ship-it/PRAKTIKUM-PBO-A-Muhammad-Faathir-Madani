/**
 * Enum: hanya nilai yang terdaftar di sini yang mungkin ada.
 * Bandingkan dengan `public static final int BENSIN = 1;`
 * yang membiarkan angka 99 lolos begitu saja.
 */
public enum TipeBahanBakar {

    // TODO 1: isi data tiap bahan bakar biar label dan tarifnya pas.
    //         BENSIN  -> "Bensin",  12000
    //         SOLAR   -> "Solar",   10500
    //         LISTRIK -> "Listrik",  2500   (per kWh)
    BENSIN("Bensin", 12000),
    SOLAR("Solar", 10500),
    // TODO 2 (Langkah 2): tambahkan listrik juga, tarifnya lebih hemat.
    LISTRIK("Listrik", 2500);

    private final String label;
    private final double hargaPerSatuan;

    TipeBahanBakar(String label, double hargaPerSatuan) {
        this.label = label;
        this.hargaPerSatuan = hargaPerSatuan;
    }

    public String getLabel() { return label; }

    /** TODO 3: hitung total isiannya, tinggal kalikan jumlah dan tarif. */
    public double biayaPengisian(double jumlah) {
        return jumlah * hargaPerSatuan;
    }

    /** TODO 4: tandai listrik saja sebagai pilihan yang ramah lingkungan. */
    public boolean ramahLingkungan() {
        return this == LISTRIK;
    }
}
