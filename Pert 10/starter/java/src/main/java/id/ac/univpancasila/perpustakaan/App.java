package id.ac.univpancasila.perpustakaan;

import id.ac.univpancasila.perpustakaan.exception.AnggotaTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.BatasPinjamTerlampauiException;
import id.ac.univpancasila.perpustakaan.exception.BukuSedangDipinjamException;
import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.PerpustakaanException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Scanner;

public final class App {
    private static final Path LOG_PATH = Path.of("perpustakaan.log");

    private App() {
    }

    public static void main(String[] args) {
        LibraryService service = new LibraryService();
        service.tambahBuku("BKU-001", "Pemrograman Berorientasi Objek", 3);
        service.tambahBuku("BKU-002", "Struktur Data", 0);
        service.daftarkanAnggota("A001");
        service.daftarkanAnggota("A002");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean lanjut = true;
            while (lanjut) {
                System.out.println("\n=== SISTEM PERPUSTAKAAN ===");
                System.out.println("1. Pinjam buku");
                System.out.println("2. Ekspor daftar buku");
                System.out.println("0. Keluar");
                System.out.print("Pilihan: ");

                try {
                    String inputMenu = scanner.nextLine();
                    if (inputMenu.trim().isEmpty()) {
                        throw new IllegalArgumentException("Pilihan menu tidak boleh kosong");
                    }

                    int pilihan;
                    try {
                        pilihan = Integer.parseInt(inputMenu.trim());
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Pilihan menu harus berupa angka", e);
                    }

                    switch (pilihan) {
                        case 1 -> prosesPinjam(scanner, service);
                        case 2 -> prosesEkspor(service);
                        case 0 -> lanjut = false;
                        default -> throw new IllegalArgumentException("Pilihan menu tidak tersedia");
                    }
                } catch (PerpustakaanException e) {
                    tanganiDomain(e);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    catatLog(e);
                } catch (IOException e) {
                    System.out.println("Gagal mengakses berkas: " + e.getMessage());
                    catatLog(e);
                }
            }
        }
    }

    private static void prosesPinjam(Scanner scanner, LibraryService service) throws IOException {
        System.out.print("ID anggota: ");
        String idAnggota = scanner.nextLine().trim();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine().trim();
        service.pinjam(idAnggota, isbn);
        System.out.println("Peminjaman berhasil");
    }

    private static void prosesEkspor(LibraryService service) throws IOException {
        Path tujuan = Path.of("daftar-buku.txt");
        service.eksporDaftarBuku(tujuan);
        System.out.println("Daftar buku diekspor ke " + tujuan.toAbsolutePath());
    }

    private static void tanganiDomain(PerpustakaanException e) {
        if (e instanceof BukuTidakDitemukanException ex) {
            System.out.println("Buku dengan ISBN [" + ex.getIsbn() + "] tidak ditemukan");
        } else if (e instanceof AnggotaTidakDitemukanException ex) {
            System.out.println("Anggota dengan ID [" + ex.getIdAnggota() + "] tidak terdaftar");
        } else if (e instanceof BukuSedangDipinjamException ex) {
            System.out.println("Buku [" + ex.getIsbn() + "] berjudul \"" + ex.getJudul()
                    + "\" sedang habis/stok tidak tersedia");
        } else if (e instanceof BatasPinjamTerlampauiException ex) {
            System.out.println("Anggota [" + ex.getIdAnggota() + "] telah mencapai batas "
                    + "pinjam [" + ex.getBatas() + "]");
        } else {
            System.out.println(e.getMessage());
        }
        catatLog(e);
    }

    private static void catatLog(Exception e) {
        String data = LocalDateTime.now() + " | " + e.getClass().getSimpleName()
                + " | " + e.getMessage();
        try {
            Files.writeString(LOG_PATH, data + System.lineSeparator(),
                    StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException loggingError) {
            System.err.println("Tidak dapat menulis log: " + loggingError.getMessage());
        }
    }
}
