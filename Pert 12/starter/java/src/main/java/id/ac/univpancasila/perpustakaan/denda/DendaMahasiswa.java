package id.ac.univpancasila.perpustakaan.denda;

public class DendaMahasiswa implements AturanDenda {
    private static final double TARIF_PER_HARI = 1000.0;

    @Override
    public double hitung(int hariTerlambat) {
        return Math.max(0, hariTerlambat) * TARIF_PER_HARI;
    }

    @Override
    public String nama() {
        return "Mahasiswa (Rp1.000/hari)";
    }
}
