public class Trapesium extends BangunDatar {

    private final double sisiAtas;
    private final double sisiBawah;
    private final double tinggi;
    private final double sisiKiri;
    private final double sisiKanan;

    public Trapesium(double sisiAtas, double sisiBawah, double tinggi, double sisiKiri, double sisiKanan) {
        super("Trapesium");
        if (sisiAtas <= 0 || sisiBawah <= 0 || tinggi <= 0 || sisiKiri <= 0 || sisiKanan <= 0) {
            throw new IllegalArgumentException("sisi trapesium: sisi harus lebih besar dari 0");
        }
        this.sisiAtas = sisiAtas;
        this.sisiBawah = sisiBawah;
        this.tinggi = tinggi;
        this.sisiKiri = sisiKiri;
        this.sisiKanan = sisiKanan;
    }

    @Override public double luas()     { return ((sisiAtas + sisiBawah) / 2.0) * tinggi; }
    @Override public double keliling() { return sisiAtas + sisiBawah + sisiKiri + sisiKanan; }

    public double getSisiAtas() { return sisiAtas; }
    public double getSisiBawah() { return sisiBawah; }
    public double getTinggi() { return tinggi; }
    public double getSisiKiri() { return sisiKiri; }
    public double getSisiKanan() { return sisiKanan; }
}
