package id.ac.univpancasila.perpustakaan.service;

import java.util.HashMap;
import java.util.Map;

public class LibraryServiceKotor {
    public static final int BATAS_PINJAM = 3;

    private final Map<String, Integer> stok = new HashMap<>();
    private final Map<String, String> judul = new HashMap<>();
    private final Map<String, Integer> pinjaman = new HashMap<>();
    private final String jenisAnggota;

    public LibraryServiceKotor(String jenisAnggota) {
        this.jenisAnggota = jenisAnggota;
    }

    public void tambahBuku(String isbn, String judulBuku, int eksemplar) {
        stok.merge(isbn, eksemplar, Integer::sum);
        judul.put(isbn, judulBuku);
    }

    public void daftarkanAnggota(String idAnggota) {
        pinjaman.putIfAbsent(idAnggota, 0);
    }

    /** INILAH yang harus Anda refaktor. */
    public boolean pinjam(String idAnggota, String isbn) {
        if (pinjaman.containsKey(idAnggota)) {
            if (stok.containsKey(isbn)) {
                if (stok.get(isbn) > 0) {
                    if (pinjaman.get(idAnggota) < BATAS_PINJAM) {
                        stok.put(isbn, stok.get(isbn) - 1);
                        pinjaman.merge(idAnggota, 1, Integer::sum);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public double hitungDenda(int hariTerlambat) {
        if (hariTerlambat <= 0) {
            return 0.0;
        }
        switch (jenisAnggota.toLowerCase()) {
            case "mahasiswa":
                return hariTerlambat * 1000.0;
            case "dosen":
                return hariTerlambat * 500.0;
            case "kehormatan":
                return 0.0;
            default:
                throw new IllegalArgumentException("Jenis anggota tidak dikenal: " + jenisAnggota);
        }
    }

    public int sisaEksemplar(String isbn) {
        return stok.getOrDefault(isbn, 0);
    }

    public String judulDari(String isbn) {
        return judul.get(isbn);
    }
}
