package id.ac.univpancasila.perpustakaan;

import id.ac.univpancasila.perpustakaan.exception.AnggotaTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.BatasPinjamTerlampauiException;
import id.ac.univpancasila.perpustakaan.exception.BukuSedangDipinjamException;
import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Path;

public class LibraryService {
    public static final int BATAS_PINJAM = 3;

    private final Map<String, Integer> stok = new HashMap<>();
    private final Map<String, String> judul = new HashMap<>();
    private final Map<String, Integer> pinjaman = new HashMap<>();

    public void tambahBuku(String isbn, String judulBuku, int eksemplar) {
        validasiTeks(isbn, "ISBN");
        validasiTeks(judulBuku, "Judul buku");
        if (eksemplar < 0) {
            throw new IllegalArgumentException("Jumlah eksemplar tidak boleh negatif");
        }
        String isbnValid = isbn.trim();
        String judulValid = judulBuku.trim();
        stok.merge(isbnValid, eksemplar, Integer::sum);
        judul.put(isbnValid, judulValid);
    }

    public void daftarkanAnggota(String idAnggota) {
        validasiTeks(idAnggota, "ID anggota");
        pinjaman.putIfAbsent(idAnggota.trim(), 0);
    }

    public boolean pinjam(String idAnggota, String isbn) {
        validasiTeks(idAnggota, "ID anggota");
        validasiTeks(isbn, "ISBN");
        String idAnggotaValid = idAnggota.trim();
        String isbnValid = isbn.trim();

        if (!pinjaman.containsKey(idAnggotaValid)) {
            throw new AnggotaTidakDitemukanException(idAnggotaValid);
        }

        if (!stok.containsKey(isbnValid)) {
            throw new BukuTidakDitemukanException(isbnValid);
        }

        if (pinjaman.get(idAnggotaValid) >= BATAS_PINJAM) {
            throw new BatasPinjamTerlampauiException(idAnggotaValid, BATAS_PINJAM);
        }

        if (stok.get(isbnValid) <= 0) {
            throw new BukuSedangDipinjamException(isbnValid, judul.get(isbnValid));
        }

        stok.merge(isbnValid, -1, Integer::sum);
        pinjaman.merge(idAnggotaValid, 1, Integer::sum);
        return true;
    }

    public int sisaEksemplar(String isbn) {
        return stok.getOrDefault(isbn, 0);
    }

    public String judulDari(String isbn) {
        return judul.get(isbn);
    }

    public void eksporDaftarBuku(Path tujuan) throws IOException {
        if (tujuan == null) {
            throw new IllegalArgumentException("Lokasi file ekspor tidak boleh kosong");
        }
        try (BufferedWriter writer = Files.newBufferedWriter(
                tujuan, StandardCharsets.UTF_8)) {
            writer.write("ISBN\tJudul\tSisa Eksemplar");
            writer.newLine();
            for (Map.Entry<String, String> entry : judul.entrySet()) {
                writer.write(entry.getKey() + "\t"
                        + entry.getValue() + "\t"
                        + sisaEksemplar(entry.getKey()));
                writer.newLine();
            }
        }
    }

    private void validasiTeks(String nilai, String namaField) {
        if (nilai == null || nilai.trim().isEmpty()) {
            throw new IllegalArgumentException(namaField + " tidak boleh kosong");
        }
    }
}
