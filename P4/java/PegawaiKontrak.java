// TODO 1: Selesai — gunakan hitungGaji() dasar; pegawai kontrak tidak menerima tunjangan masa kerja.
public class PegawaiKontrak extends Pegawai {

    private final int bulanKontrak;

    public PegawaiKontrak(String nip, String nama, double gajiPokok, int bulanKontrak) {
        super(nip, nama, gajiPokok);
        if (bulanKontrak < 0) {
            throw new IllegalArgumentException("Bulan kontrak tidak boleh negatif.");
        }
        this.bulanKontrak = bulanKontrak;
    }

    @Override
    public String jenis() { return "KONTRAK"; }

    public int getBulanKontrak() { return bulanKontrak; }
}
