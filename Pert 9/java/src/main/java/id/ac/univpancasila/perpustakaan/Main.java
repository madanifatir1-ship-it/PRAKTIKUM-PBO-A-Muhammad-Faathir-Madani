package id.ac.univpancasila.perpustakaan;

import com.github.freva.asciitable.AsciiTable;
import id.ac.univpancasila.perpustakaan.model.Anggota;
import id.ac.univpancasila.perpustakaan.model.Buku;
import id.ac.univpancasila.perpustakaan.model.Jurusan;
import id.ac.univpancasila.perpustakaan.service.PerpustakaanService;
import java.time.LocalDateTime;
import net.datafaker.Faker;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        System.out.println("=== Komposisi: Buku memiliki Bab ===");
        Buku buku = new Buku(
                "PBO-001",
                "Pemrograman Berorientasi Objek",
                "A. W. Pribadi",
                3);
        buku.tambahBab(1, "Paradigma Objek");
        buku.tambahBab(2, "Enkapsulasi");
        buku.tambahBab(3, "Pewarisan dan Polimorfisme");
        System.out.println("  " + buku);
        buku.getBab().forEach(bab -> System.out.println("    " + bab));

        System.out.println();
        System.out.println("  Mencoba mengubah koleksi internal dari luar...");
        try {
            buku.getBab().clear();
            System.out.println("    MASALAH: koleksi internal berhasil dikosongkan dari luar!");
        } catch (UnsupportedOperationException exception) {
            System.out.println("    Ditolak - koleksi bab tidak bisa diubah dari luar. Benar.");
        }

        System.out.println();
        System.out.println("=== Agregasi: Jurusan menaungi Mahasiswa ===");
        Faker faker = new Faker();

        Jurusan.Mahasiswa ani = new Jurusan.Mahasiswa("2024001", "Ani Lestari");
        Jurusan.Mahasiswa budi = new Jurusan.Mahasiswa("2024002", "Budi Santoso");
        Jurusan.Mahasiswa mahasiswaFaker = new Jurusan.Mahasiswa("2024003", faker.name().fullName());

        Jurusan informatika = new Jurusan("TI", "Teknik Informatika");
        informatika.daftarkan(ani);
        informatika.daftarkan(budi);
        informatika.daftarkan(mahasiswaFaker);

        System.out.println("  " + informatika);
        informatika.keluarkan(budi);
        System.out.println("  Setelah Budi pindah: " + informatika);
        System.out.println("  Budi tetap ada sebagai objek: " + budi);
        System.out.println();
        System.out.println("  Itulah bedanya: Bab tidak bisa hidup di luar Buku,");
        System.out.println("  tetapi Mahasiswa tetap ada di luar Jurusan.");

        System.out.println();
        System.out.println("========== Perpustakaan Teknik =============");
        PerpustakaanService perpustakaan = new PerpustakaanService();
        perpustakaan.tambahBuku(buku);

        System.out.println("Hasil pencarian buku anda: " + perpustakaan.cariBuku("PBO-001"));
        perpustakaan.pinjam("IT-4554", "PBO-001");
        perpustakaan.kembalikan("IT-4554", "PBO-001");

        System.out.println();
        System.out.println("========== Keanggotaan Perpustakaan Teknik =============");
        LocalDateTime sekarang = LocalDateTime.now();
        Anggota anggota = new Anggota("IT", informatika.getNama(), "045");
        anggota.daftarkan(mahasiswaFaker, sekarang);
        anggota.getKeanggotaan();
        System.out.println("Status keanggotaan: " + anggota.checkMasaKeanggotaan());

        String[][] data = {
                {"Mahasiswa Faker", mahasiswaFaker.getNama()},
                {"Jurusan", informatika.getNama()},
                {"Sisa Stok Buku", String.valueOf(buku.getStok())},
                {"Status Keanggotaan", anggota.checkMasaKeanggotaan()}
        };
        System.out.println(AsciiTable.getTable(new String[]{"Ringkasan", "Nilai"}, data));
    }
}
