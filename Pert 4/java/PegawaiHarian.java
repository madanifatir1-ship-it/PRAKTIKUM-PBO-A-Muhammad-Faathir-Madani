// TODO 1: Selesai — hitung gaji pegawai harian berdasarkan tarif harian dan hari kerja.
public class PegawaiHarian extends Pegawai {

    private final double gajiPerHari;
    private final int hariKerja;

    public PegawaiHarian(String nip, String nama, double gajiPerHari, int hariKerja) {
        super(nip, nama, gajiPerHari);
        if (hariKerja < 0) {
            throw new IllegalArgumentException("Hari kerja tidak boleh negatif.");
        }
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
