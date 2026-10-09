public class PegawaiTetap extends Pegawai {

    /** Tunjangan masa kerja: 2% gaji pokok per tahun, maksimum 40%. */
    protected static final double TUNJANGAN_PER_TAHUN = 0.02;
    protected static final double TUNJANGAN_MAKSIMUM  = 0.40;

    private final int masaKerjaTahun;

    public PegawaiTetap(String nip, String nama, double gajiPokok, int masaKerjaTahun) {
        // TODO 1: Selesai — panggil constructor induk sebagai pernyataan pertama.
        super(nip, nama, gajiPokok);

        if (masaKerjaTahun < 0) {
            throw new IllegalArgumentException("Masa kerja tidak boleh negatif.");
        }
        this.masaKerjaTahun = masaKerjaTahun;
    }

    // TODO 2: Selesai — tambahkan tunjangan masa kerja ke gaji dasar dari kelas induk.
    @Override
    public double hitungGaji() {
        double persentaseTunjangan = Math.min(
            masaKerjaTahun * TUNJANGAN_PER_TAHUN,
            TUNJANGAN_MAKSIMUM
        );
        return super.hitungGaji() * (1 + persentaseTunjangan);
    }

    @Override
    public String jenis() { return "TETAP"; }

    protected int getMasaKerjaTahun() { return masaKerjaTahun; }
}
