public class PegawaiHarian extends Pegawai {
    private final double gajiPerHari;
    private final int hariKerja;

    public PegawaiHarian(String nip, String nama, double gajiPerHari, int hariKerja) {
        super(nip, nama, gajiPerHari);
        this.gajiPerHari = gajiPerHari;
        this.hariKerja = hariKerja;
    }

    @Override
    public double hitungGaji() {
        return gajiPerHari * hariKerja;
    }

    @Override
    public String jenis() { return "HARIAN"; }

    public double getGajiPerHari() { return gajiPerHari; }
    public int getHariKerja() { return hariKerja; }
}
