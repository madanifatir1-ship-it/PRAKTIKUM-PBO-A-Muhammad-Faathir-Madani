// TODO 1: Selesai — hitung gaji pegawai paruh waktu berdasarkan tarif dan jam kerja.
public class PegawaiParuhWaktu extends Pegawai {

    private final double gajiPerJam;
    private final int jamKerja;

    public PegawaiParuhWaktu(String nip, String nama, double gajiPerJam, int jamKerja) {
        super(nip, nama, gajiPerJam);
        if (gajiPerJam < 0) {
            throw new IllegalArgumentException("Gaji per jam tidak boleh negatif.");
        }
        if (jamKerja < 0) {
            throw new IllegalArgumentException("Jam kerja tidak boleh negatif.");
        }
        this.gajiPerJam = gajiPerJam;
        this.jamKerja = jamKerja;
    }

    @Override
    public double hitungGaji() {
        // TODO 2: Selesai — kembalikan hasil perkalian tarif per jam dengan jam kerja.
        return gajiPerJam * jamKerja;
    }

    @Override
    public String jenis() { return "PARUH WAKTU"; }

    public double getGajiPerJam() { return gajiPerJam; }
    public int getJamKerja() { return jamKerja; }
}
