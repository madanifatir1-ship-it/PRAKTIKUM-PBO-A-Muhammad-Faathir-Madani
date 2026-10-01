public class Dosen extends PegawaiTetap {
    private final double tunjanganFungsional;

    public Dosen(String nip, String nama, double gajiPokok, int masaKerjaTahun, double tunjanganFungsional) {
        super(nip, nama, gajiPokok, masaKerjaTahun);
<<<<<<< HEAD

        
=======
>>>>>>> eb5ddc216217df4cb98736cbee4168732221a507
        this.tunjanganFungsional = tunjanganFungsional;
    }

    @Override
    public double hitungGaji() {
<<<<<<< HEAD
        return super.hitungGaji() + this.tunjanganFungsional;
=======
        return super.hitungGaji() + tunjanganFungsional;
>>>>>>> eb5ddc216217df4cb98736cbee4168732221a507
    }

    @Override
    public String jenis() { return "DOSEN"; }

    public double getTunjanganFungsional() { return tunjanganFungsional; }
}
