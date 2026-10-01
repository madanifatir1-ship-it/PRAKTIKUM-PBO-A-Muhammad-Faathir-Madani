public class PegawaiTetap extends Pegawai {

    /** Tunjangan masa kerja: 2% gaji pokok per tahun, maksimum 40%. */
    protected static final double TUNJANGAN_PER_TAHUN = 0.02;
    protected static final double TUNJANGAN_MAKSIMUM  = 0.40;

    private final int masaKerjaTahun;

    public PegawaiTetap(String nip, String nama, double gajiPokok, int masaKerjaTahun) {
<<<<<<< HEAD
        // Baris berikut WAJIB dan harus menjadi pernyataan pertama.
        // TODO 1 (Langkah 3): hapus sementara baris ini, kompilasi,
        //         salin pesan kesalahannya ke catatan.md, lalu kembalikan.
        super(nip, nama, gajiPokok);

=======
        super(nip, nama, gajiPokok);
>>>>>>> eb5ddc216217df4cb98736cbee4168732221a507
        this.masaKerjaTahun = masaKerjaTahun;
    }

    /**
<<<<<<< HEAD
     * TODO 2: hitung gaji = gaji dasar induk + tunjangan masa kerja.
     *
     * PENTING: panggil super.hitungGaji() untuk memperoleh gaji dasar.
     *          JANGAN menyalin rumus induk ke sini — itu yang dinilai.
     */
    @Override
    public double hitungGaji() {
        double tunjangan = Math.min(
            masaKerjaTahun * TUNJANGAN_PER_TAHUN,
            TUNJANGAN_MAKSIMUM
        ) * super.hitungGaji();
        return super.hitungGaji() + tunjangan;
=======
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
>>>>>>> eb5ddc216217df4cb98736cbee4168732221a507
    }

    @Override
    public String jenis() { return "TETAP"; }

    protected int getMasaKerjaTahun() { return masaKerjaTahun; }
}
