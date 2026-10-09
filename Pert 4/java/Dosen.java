// TODO 1: Selesai — turunkan dosen dari pegawai tetap dan tambahkan tunjangan fungsional.
public class Dosen extends PegawaiTetap {

    private final String jabatanFungsional;
    private final double tunjanganFungsional;

    public Dosen(
        String nip,
        String nama,
        double gajiPokok,
        int masaKerjaTahun,
        String jabatanFungsional,
        double tunjanganFungsional
    ) {
        super(nip, nama, gajiPokok, masaKerjaTahun);
        if (tunjanganFungsional < 0) {
            throw new IllegalArgumentException("Tunjangan fungsional tidak boleh negatif.");
        }
        this.jabatanFungsional = jabatanFungsional;
        this.tunjanganFungsional = tunjanganFungsional;
    }

    @Override
    public double hitungGaji() {
        return super.hitungGaji() + tunjanganFungsional;
    }

    @Override
    public String jenis() { return "DOSEN"; }

    public String getJabatanFungsional() { return jabatanFungsional; }
    public double getTunjanganFungsional() { return tunjanganFungsional; }
}
