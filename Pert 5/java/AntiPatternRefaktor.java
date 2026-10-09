public class AntiPatternRefaktor {

    public static void main(String[] args) {
        // TODO 1: Bikin daftar bertipe BangunDatar, lalu masukin objek bangunnya ke sini.
        BangunDatar[] daftar = {
            new Lingkaran(7),
            new Persegi(5),
            new Segitiga(4, 3, 5)
        };

        // TODO 2: Jumlahkan luas semua bangun lewat polimorfisme, tanpa instanceof atau else-if.
        double total = 0;
        for (BangunDatar bangun : daftar) {
            total += bangun.luas();
        }
        System.out.printf("Total luas (versi polimorfik): %.2f%n", total);
    }
}
