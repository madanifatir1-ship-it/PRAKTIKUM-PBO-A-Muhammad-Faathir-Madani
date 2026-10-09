import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        // TODO 1: Selesai — rekap seluruh turunan Pegawai melalui satu daftar polimorfik.
        Pegawai[] daftar = {
            new PegawaiTetap("198701012010", "Ani Lestari",  6_000_000, 15),
            new PegawaiKontrak("K-2024-007", "Budi Santoso", 5_000_000, 12),
            new Dosen("D-2024-001", "Citra Dewi", 7_000_000, 10, "Lektor Kepala", 1_500_000),
            new PegawaiHarian("H-2024-003", "Dedi Pratama", 200_000, 20),
            new PegawaiParuhWaktu("P-2024-005", "Eka Putri", 75_000, 40)
        };

        System.out.println("=== Daftar Gaji ===");
        for (Pegawai p : daftar) {
            System.out.println("  " + p);
        }

        double total = 0;
        for (Pegawai p : daftar) total += p.hitungGaji();
        System.out.printf(Locale.forLanguageTag("id-ID"),
            "%n  Total beban gaji: Rp%,.2f%n", total);

        System.out.println();
        System.out.println("Periksa: Ani (pokok 6.000.000, masa kerja 15 tahun)");
        System.out.println("  tunjangan 15 x 2% = 30%, jadi gaji seharusnya Rp7.800.000,00");

        // Percobaan Langkah 1: hapus komentar baris berikut, kompilasi, catat pesannya.
        // Pegawai langsung = new Pegawai("X", "Y", 1000) { public String jenis() { return "?"; } };
    }
}
