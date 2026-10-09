import java.util.List;

public class Main {

    /**
     * Perhatikan tipe parameternya: Fuelable, bukan Mobil.
     * Method ini tidak peduli kelas konkretnya — hanya peduli kontraknya.
     * Kelas baru yang implements Fuelable langsung bisa dipakai di sini,
     * tanpa menyunting satu baris pun.
     */
    static void isiPenuh(Fuelable kendaraan) {
        kendaraan.isiBahanBakar(kendaraan.kapasitasTangki());
        double biaya = kendaraan.tipeBahanBakar().biayaPengisian(kendaraan.kapasitasTangki());
        System.out.printf("  Diisi penuh %s — biaya Rp%,.0f%n",
                kendaraan.tipeBahanBakar().getLabel(), biaya);
    }

    public static void main(String[] args) {
        Mobil mobil = new Mobil("Toyota Avanza", 2022, 45);
        Sepeda sepeda = new Sepeda("Sepeda", 2024);

        System.out.println("=== Semua Movable ===");
        // TODO 4: ikutkan sepeda biar semua yang bisa bergerak tampil.
        for (Movable m : List.of(mobil, sepeda)) {
            m.bergerak();
            System.out.println("    " + m.ringkasanGerak());
        }

        System.out.println();
        System.out.println("=== Hanya yang Fuelable ===");
        isiPenuh(mobil);

        // TODO 4: kalau penasaran, buka komentar ini untuk melihat compiler menolak Sepeda.
        //                 Baris sengaja tetap dinonaktifkan supaya contoh utama tetap bisa dikompilasi.
        // isiPenuh(sepeda); // Compile-time error: Sepeda bukan Fuelable.

        System.out.println();
        System.out.println("=== Enum punya perilaku ===");
        for (TipeBahanBakar t : TipeBahanBakar.values()) {
            System.out.printf("  %-8s ramah lingkungan? %-5s  biaya 10 satuan: Rp%,.0f%n",
                    t.getLabel(), t.ramahLingkungan(), t.biayaPengisian(10));
        }
    }
}
