package id.ac.univpancasila.perpustakaan.service;

import id.ac.univpancasila.perpustakaan.model.Buku;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public final class PerpustakaanService {
    private final LibraryService libraryService;

    public PerpustakaanService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public void tampilkanLaporan() {
        System.out.println("Buku tersedia:");
        libraryService.getBukuTersedia().forEach(System.out::println);
        System.out.println("Semua judul: " + libraryService.getSemuaJudulDigabung());
        System.out.println("Jumlah judul per penulis:");
        libraryService.getJumlahBukuPerPenulis()
                .forEach((penulis, jumlah) -> System.out.println(penulis + ": " + jumlah));
    }

    public void eksporLaporan(Path tujuan) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(tujuan, StandardCharsets.UTF_8)) {
            writer.write("LAPORAN PERPUSTAKAAN");
            writer.newLine();
            writer.write("Buku tersedia:");
            writer.newLine();
            for (Buku buku : libraryService.getBukuTersedia()) {
                writer.write(buku.toString());
                writer.newLine();
            }
            writer.write("Semua judul: " + libraryService.getSemuaJudulDigabung());
            writer.newLine();
            writer.write("Jumlah judul per penulis:");
            writer.newLine();
            for (Map.Entry<String, Long> entry : libraryService.getJumlahBukuPerPenulis().entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        }
    }
}
