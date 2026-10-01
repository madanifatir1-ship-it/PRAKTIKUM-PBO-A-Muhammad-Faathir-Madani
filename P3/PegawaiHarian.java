public class PegawaiHarian extends Pegawai {
<<<<<<< HEAD
    private final double gajiPerHari;
=======
>>>>>>> eb5ddc216217df4cb98736cbee4168732221a507
    private final int hariKerja;

    public PegawaiHarian(String nip, String nama, double gajiPerHari, int hariKerja) {
        super(nip, nama, gajiPerHari);
<<<<<<< HEAD
        this.gajiPerHari = gajiPerHari;
=======
>>>>>>> eb5ddc216217df4cb98736cbee4168732221a507
        this.hariKerja = hariKerja;
    }

    @Override
    public double hitungGaji() {
<<<<<<< HEAD
        return gajiPerHari * hariKerja;
=======
        return gajiPokok * hariKerja;
>>>>>>> eb5ddc216217df4cb98736cbee4168732221a507
    }

    @Override
    public String jenis() { return "HARIAN"; }

<<<<<<< HEAD
    public double getGajiPerHari() { return gajiPerHari; }
=======
>>>>>>> eb5ddc216217df4cb98736cbee4168732221a507
    public int getHariKerja() { return hariKerja; }
}
