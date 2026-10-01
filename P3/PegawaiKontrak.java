public class PegawaiKontrak extends Pegawai {

    private final int bulanKontrak;

    public PegawaiKontrak(String nip, String nama, double gajiPokok, int bulanKontrak) {
        super(nip, nama, gajiPokok);
        this.bulanKontrak = bulanKontrak;
    }

<<<<<<< HEAD
    // TODO 2: pegawai kontrak TIDAK mendapat tunjangan masa kerja.
    //         Apakah method hitungGaji() perlu di-override di sini?
    //         Pikirkan dulu, lalu tuliskan alasannya di catatan.md.

=======
>>>>>>> eb5ddc216217df4cb98736cbee4168732221a507
    @Override
    public String jenis() { return "KONTRAK"; }

    public int getBulanKontrak() { return bulanKontrak; }
}
