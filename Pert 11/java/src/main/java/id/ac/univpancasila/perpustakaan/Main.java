package id.ac.univpancasila.perpustakaan;

import id.ac.univpancasila.perpustakaan.exception.PerpustakaanException;
import id.ac.univpancasila.perpustakaan.model.Buku;
import id.ac.univpancasila.perpustakaan.service.LibraryService;
import id.ac.univpancasila.perpustakaan.service.PerpustakaanService;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        LibraryService library = new LibraryService();
        library.tambahBuku(new Buku("BKU-001", "Pemrograman Berorientasi Objek", "S. Suryana", 3));
        library.tambahBuku(new Buku("BKU-002", "Struktur Data", "A. Nugroho", 0));
        library.tambahBuku(new Buku("BKU-003", "Pemrograman Java", "S. Suryana", 2));
        library.daftarkanAnggota("A001");
        library.daftarkanAnggota("A002");
        PerpustakaanService service = new PerpustakaanService(library);

        try (Scanner scanner = new Scanner(System.in)) {
            boolean berjalan = true;
            while (berjalan) {
                System.out.println("\n=== SISTEM PERPUSTAKAAN ===");
                System.out.println("1. Pinjam buku");
                System.out.println("2. Lihat semua buku");
                System.out.println("3. Daftar anggota demo: A001, A002");
                System.out.println("4. Lihat laporan fungsional");
                System.out.println("0. Keluar");
                System.out.print("Pilihan: ");
                String pilihan = scanner.nextLine().trim();

                try {
                    switch (pilihan) {
                        case "1" -> {
                            System.out.print("ID anggota: ");
                            String anggota = scanner.nextLine();
                            System.out.print("ISBN/nomor induk buku: ");
                            String isbn = scanner.nextLine();
                            library.pinjam(anggota, isbn);
                            System.out.println("Peminjaman berhasil.");
                        }
                        case "2" -> library.getSemuaBuku().forEach(System.out::println);
                        case "3" -> System.out.println("Anggota yang terdaftar: A001, A002");
                        case "4" -> service.tampilkanLaporan();
                        case "0" -> berjalan = false;
                        default -> System.out.println("Pilihan menu tidak tersedia.");
                    }
                } catch (PerpustakaanException | IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } finally {
            try {
                service.eksporLaporan(Path.of("laporan_buku.txt"));
                System.out.println("Laporan diekspor ke laporan_buku.txt");
            } catch (IOException e) {
                System.err.println("Gagal mengekspor laporan: " + e.getMessage());
            }
        }
    }
}
