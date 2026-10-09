public class Lingkaran extends BangunDatar {

    private final double jariJari;

    public Lingkaran(double jariJari) {
        super("Lingkaran");
        // TODO 1: Pastikan jari-jarinya positif biar bentuknya valid.
        if (jariJari <= 0) {
            throw new IllegalArgumentException("Jari-jari harus lebih dari 0.");
        }
        this.jariJari = jariJari;
    }

    // TODO 2: Hitung luas dan keliling pakai Math.PI, jangan pakai angka 3.14.
    @Override public double luas()     { return Math.PI * jariJari * jariJari; }
    @Override public double keliling() { return 2 * Math.PI * jariJari; }

    public double getJariJari() { return jariJari; }
}
