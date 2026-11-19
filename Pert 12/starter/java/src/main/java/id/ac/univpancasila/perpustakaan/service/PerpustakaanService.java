package id.ac.univpancasila.perpustakaan.service;

import id.ac.univpancasila.perpustakaan.exception.PerpustakaanException;
import id.ac.univpancasila.perpustakaan.model.Buku;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PerpustakaanService {

    public static void tambahBuku(LibraryService service, Scanner scanner) {
        try {
            System.out.print("Masukkan ISBN: ");
            String isbn = scanner.nextLine().trim();
            System.out.print("Masukkan Judul Buku: ");
            String judul = scanner.nextLine().trim();
            System.out.print("Masukkan Penulis: ");
            String penulis = scanner.nextLine().trim();
            System.out.print("Masukkan Jumlah Eksemplar: ");
            int eksemplar = Integer.parseInt(scanner.nextLine().trim());

            service.tambahBuku(isbn, judul, penulis, eksemplar);
            System.out.println("[BERHASIL] Buku '" + judul + "' berhasil ditambahkan.");
        } catch (NumberFormatException e) {
            System.out.println("[GAGAL] Jumlah eksemplar harus berupa angka.");
        } catch (IllegalArgumentException e) {
            System.out.println("[GAGAL] " + e.getMessage());
        }
    }

    public static void tambahAnggota(LibraryService service, Scanner scanner) {
        System.out.print("Masukkan ID Anggota: ");
        String idAnggota = scanner.nextLine().trim();
        if (idAnggota.isEmpty()) {
            System.out.println("[GAGAL] ID Anggota tidak boleh kosong.");
            return;
        }
        service.daftarkanAnggota(idAnggota);
        System.out.println("[BERHASIL] Anggota '" + idAnggota + "' berhasil didaftarkan.");
    }

    public static void pinjamBuku(LibraryService service, Scanner scanner) {
        System.out.print("Masukkan ID Anggota: ");
        String idAnggota = scanner.nextLine().trim();
        System.out.print("Masukkan ISBN Buku: ");
        String isbn = scanner.nextLine().trim();

        try {
            service.pinjam(idAnggota, isbn);
            System.out.println("[BERHASIL] Peminjaman berhasil! Sisa stok: " + service.sisaEksemplar(isbn));
        } catch (PerpustakaanException e) {
            System.out.println("[GAGAL] " + e.getMessage());
        }
    }

    public static void tampilkanLaporanFungsional(LibraryService service) {
        System.out.println("\n--- LAPORAN FUNGSIONAL PERPUSTAKAAN ---");
        List<Buku> tersedia = service.getBukuTersedia();
        System.out.println("1. Buku Tersedia (" + tersedia.size() + "):");
        if (tersedia.isEmpty()) {
            System.out.println("   (Tidak ada buku tersedia)");
        } else {
            for (Buku b : tersedia) {
                System.out.println("   - " + b);
            }
        }

        String semuaJudul = service.getSemuaJudulDigabung();
        System.out.println("2. Semua Judul Digabung: " + (semuaJudul.isEmpty() ? "-" : semuaJudul));

        Map<String, Long> perPenulis = service.getJumlahBukuPerPenulis();
        System.out.println("3. Jumlah Judul per Penulis: " + perPenulis);
        System.out.printf("4. Simulasi Denda 3 Hari (%s): Rp%,.0f%n",
                service.getNamaAturanDenda(), service.hitungDenda(3));
    }
}
