package id.ac.univpancasila.perpustakaan.service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import id.ac.univpancasila.perpustakaan.exception.AnggotaTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.BatasPinjamTerlampauiException;
import id.ac.univpancasila.perpustakaan.exception.BukuSedangDipinjamException;
import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.PerpustakaanException;
import id.ac.univpancasila.perpustakaan.model.Buku;

public class PerpustakaanService {

    public static void tambahAnggota(LibraryService libService, Scanner scanner) {
        System.out.println("\n--- Form Tambah Anggota ---");
        System.out.print("Masukkan ID Anggota: ");
        String idAnggota = scanner.nextLine().trim();

        if (idAnggota.isEmpty()) {
            System.out.println("[Gagal] ID Anggota tidak boleh kosong!");
            return;
        }

        libService.daftarkanAnggota(idAnggota);
        System.out.println(">> BERHASIL: Anggota dengan ID '" + idAnggota + "' telah terdaftar.");
    }

    public static void tambahBuku(LibraryService libService, Scanner scanner) {
        System.out.println("\n--- Form Tambah Buku ---");
        System.out.print("Masukkan ISBN Buku: ");
        String isbn = scanner.nextLine().trim();

        if (isbn.isEmpty()) {
            System.out.println("[Gagal] ISBN tidak boleh kosong!");
            return;
        }

        System.out.print("Masukkan Judul Buku: ");
        String judul = scanner.nextLine().trim();

        System.out.print("Masukkan Penulis Buku: ");
        String penulis = scanner.nextLine().trim();

        System.out.print("Masukkan Jumlah Eksemplar: ");
        int eksemplar = 0;
        try {
            eksemplar = Integer.parseInt(scanner.nextLine().trim());
            if (eksemplar <= 0) {
                System.out.println("[Gagal] Jumlah eksemplar harus lebih dari 0!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("[Error Input] Jumlah eksemplar harus berupa angka!");
            return;
        }

        try {
            libService.tambahBuku(isbn, judul, penulis, eksemplar);
            System.out.println(">> BERHASIL: Buku '" + judul + "' (" + eksemplar + " eksemplar) ditambahkan.");
        } catch (IllegalArgumentException e) {
            System.out.println("[Gagal] " + e.getMessage());
        }
    }

    public static void pinjamBuku(LibraryService libService, Scanner scanner) {
        System.out.println("=== FORM PEMINJAMAN BUKU ===");

        while (true) {
            try {
                System.out.println("\n--- Peminjaman Buku ---");
                System.out.print("Masukkan ID Anggota (Ketik 'exit' untuk keluar): ");
                String idAnggota = scanner.nextLine().trim();

                if (idAnggota.equalsIgnoreCase("exit")) {
                    break;
                }

                if (idAnggota.isEmpty()) {
                    System.out.println("[Peringatan] ID Anggota tidak boleh kosong!");
                    continue;
                }

                System.out.print("Masukkan ISBN Buku: ");
                String isbn = scanner.nextLine().trim();

                libService.pinjam(idAnggota, isbn);
                System.out.println(">> Peminjaman Berhasil! Sisa stok: " + libService.sisaEksemplar(isbn));
            } catch (PerpustakaanException e) {
                System.out.println("[Error Domain] " + e.getMessage());

                if (e instanceof BukuTidakDitemukanException ex) {
                    System.out.println("  -> ISBN bermasalah: " + ex.getIsbn());
                } else if (e instanceof AnggotaTidakDitemukanException ex) {
                    System.out.println("  -> ID Anggota bermasalah: " + ex.getIdAnggota());
                } else if (e instanceof BukuSedangDipinjamException ex) {
                    System.out.println("  -> Judul Buku: " + ex.getJudul() + " (ISBN: " + ex.getIsbn() + ")");
                } else if (e instanceof BatasPinjamTerlampauiException ex) {
                    System.out.println("  -> Batas Maksimum: " + ex.getBatas() + " buku");
                }
            } catch (Exception e) {
                System.out.println("[Error Sistem] Terjadi kesalahan input: " + e.getMessage());
            }
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
