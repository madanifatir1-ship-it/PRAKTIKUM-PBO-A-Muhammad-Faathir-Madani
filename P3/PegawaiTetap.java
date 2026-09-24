public class PegawaiTetap extends Pegawai {

    /** Tunjangan masa kerja: 2% gaji pokok per tahun, maksimum 40%. */
    protected static final double TUNJANGAN_PER_TAHUN = 0.02;
    protected static final double TUNJANGAN_MAKSIMUM  = 0.40;

    private final int masaKerjaTahun;

    public PegawaiTetap(String nip, String nama, double gajiPokok, int masaKerjaTahun) {
        super(nip, nama, gajiPokok);
        this.masaKerjaTahun = masaKerjaTahun;
    }

    /**
     * Gaji dasar induk + tunjangan masa kerja.
     * Panggil super.hitungGaji() untuk memperoleh gaji dasar.
     */
    @Override
    public double hitungGaji() {
        double gajiDasar = super.hitungGaji();
        double tunjangan = gajiDasar * TUNJANGAN_PER_TAHUN * masaKerjaTahun;
        double maksimum = gajiDasar * TUNJANGAN_MAKSIMUM;

        if (tunjangan > maksimum) {
            tunjangan = maksimum;
        }

        return gajiDasar + tunjangan;
    }

    @Override
    public String jenis() { return "TETAP"; }

    protected int getMasaKerjaTahun() { return masaKerjaTahun; }
}
