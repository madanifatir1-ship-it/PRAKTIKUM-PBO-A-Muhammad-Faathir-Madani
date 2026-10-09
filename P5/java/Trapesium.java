public class Trapesium extends BangunDatar {

    private final double alasAtas;
    private final double alasBawah;
    private final double sisiMiring1;
    private final double sisiMiring2;
    private final double tinggi;

    public Trapesium(double alasAtas, double alasBawah, double sisiMiring1,
                     double sisiMiring2, double tinggi) {
        super("Trapesium");
        // TODO 1: Pastikan semua ukuran trapesium lebih dari nol.
        if (alasAtas <= 0 || alasBawah <= 0 || sisiMiring1 <= 0
                || sisiMiring2 <= 0 || tinggi <= 0) {
            throw new IllegalArgumentException("Semua ukuran trapesium harus lebih dari 0.");
        }
        this.alasAtas = alasAtas;
        this.alasBawah = alasBawah;
        this.sisiMiring1 = sisiMiring1;
        this.sisiMiring2 = sisiMiring2;
        this.tinggi = tinggi;
    }

    @Override
    public double luas() {
        // TODO 2: Hitung luas dari alas atas, alas bawah, dan tinggi.
        return (alasAtas + alasBawah) * tinggi / 2;
    }

    @Override
    public double keliling() {
        // TODO 3: Jumlahkan dua alas dan dua sisi miring buat kelilingnya.
        return alasAtas + alasBawah + sisiMiring1 + sisiMiring2;
    }
}
