public class Segitiga extends BangunDatar {

    private final double a;
    private final double b;
    private final double c;

    public Segitiga(double a, double b, double c) {
        super("Segitiga");
        // TODO 1: Cek dulu semua sisi positif dan memenuhi ketaksamaan segitiga.
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Semua sisi segitiga harus lebih dari 0.");
        }
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Panjang sisi tidak memenuhi ketaksamaan segitiga.");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double luas() {
        // TODO 2: Hitung luas segitiga pakai rumus Heron.
        double setengahKeliling = keliling() / 2;
        return Math.sqrt(setengahKeliling
                * (setengahKeliling - a)
                * (setengahKeliling - b)
                * (setengahKeliling - c));
    }

    @Override
    public double keliling() {
        // TODO 3: Jumlahkan ketiga sisinya buat mendapatkan keliling.
        return a + b + c;
    }
}
