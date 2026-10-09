public class Segitiga extends BangunDatar {

    private final double alas;
    private final double tinggi;
    private final double sisiMiring;

    public Segitiga(double alas, double tinggi, double sisiMiring) {
        super("Segitiga");
        if (alas <= 0 || tinggi <= 0 || sisiMiring <= 0) {
            throw new IllegalArgumentException("sisi segitiga: sisi harus lebih besar dari 0");
        }
        this.alas = alas;
        this.tinggi = tinggi;
        this.sisiMiring = sisiMiring;
    }

    @Override public double luas()     { return 0.5 * alas * tinggi; }
    @Override public double keliling() { return alas + tinggi + sisiMiring; }

    public double getAlas() { return alas; }
    public double getTinggi() { return tinggi; }
    public double getSisiMiring() { return sisiMiring; }
}
