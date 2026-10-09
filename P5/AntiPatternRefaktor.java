/**
 * Versi refaktor: sudah memakai polimorfisme.
 *
 * Tujuan: menghilangkan if/else-if yang harus diubah setiap kali ada bangun datar baru.
 */
public class AntiPatternRefaktor {

    interface BangunDatar {
        double luas();
    }

    record LingkaranData(double r) implements BangunDatar {
        @Override public double luas() { return Math.PI * r * r; }
    }

    record PersegiData(double sisi) implements BangunDatar {
        @Override public double luas() { return sisi * sisi; }
    }

    record SegitigaData(double alas, double tinggi) implements BangunDatar {
        @Override public double luas() { return 0.5 * alas * tinggi; }
    }

    public static void main(String[] args) {
        BangunDatar[] daftar = {
            new LingkaranData(7),
            new PersegiData(5),
            new SegitigaData(4, 3)
        };

        double total = 0;
        for (BangunDatar b : daftar) total += b.luas();

        System.out.printf("Total luas (versi polimorfik): %.2f%n", total);
        System.out.println();
        System.out.println("Catatan: untuk menambah bangun baru, cukup tambahkan kelas baru " +
            "yang implementasi BangunDatar tanpa mengubah fungsi utama.");
    }
}
