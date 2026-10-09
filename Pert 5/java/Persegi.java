public class Persegi extends BangunDatar {

    private final double sisi;

    public Persegi(double sisi) {
        super("Persegi");
        // TODO 1: Pastikan panjang sisinya positif sebelum disimpan.
        if (sisi <= 0) {
            throw new IllegalArgumentException("Sisi harus lebih dari 0.");
        }
        this.sisi = sisi;
    }

    // TODO 2: Lengkapi hitungan luas dan keliling persegi dari panjang sisinya.
    @Override public double luas()     { return sisi * sisi; }
    @Override public double keliling() { return 4 * sisi; }
}
