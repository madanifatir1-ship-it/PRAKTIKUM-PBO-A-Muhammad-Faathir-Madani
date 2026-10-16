package service;

import model.Anggota;
import model.Koleksi;
import model.Peminjaman;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Layanan untuk mengelola koleksi, anggota, dan peminjaman perpustakaan.
 */
public class PerpustakaanService {
    private final Map<String, Koleksi> koleksi = new LinkedHashMap<>();
    private final Map<String, Anggota> anggota = new LinkedHashMap<>();
    private final List<Peminjaman> riwayatPeminjaman = new ArrayList<>();

    public void tambahKoleksi(Koleksi k) {
        Objects.requireNonNull(k, "Koleksi tidak boleh null.");
        if (koleksi.putIfAbsent(k.getNomorInduk(), k) != null) {
            throw new IllegalArgumentException("Nomor induk koleksi sudah terdaftar.");
        }
    }

    public void daftarkanAnggota(Anggota a) {
        Objects.requireNonNull(a, "Anggota tidak boleh null.");
        if (anggota.putIfAbsent(a.getNomorAnggota(), a) != null) {
            throw new IllegalArgumentException("Nomor anggota sudah terdaftar.");
        }
    }

    public Koleksi cariKoleksi(String nomorInduk) {
        Koleksi hasil = koleksi.get(requireText(nomorInduk, "Nomor induk"));
        if (hasil == null) {
            throw new NoSuchElementException("Koleksi tidak ditemukan: " + nomorInduk);
        }
        return hasil;
    }

    public Peminjaman pinjam(String nomorAnggota, String nomorInduk) {
        Anggota peminjam = cariAnggota(nomorAnggota);
        Koleksi item = cariKoleksi(nomorInduk);

        if (peminjam.getPeminjamanAktif().size() >= 3) {
            throw new IllegalStateException("Anggota tidak boleh meminjam lebih dari tiga koleksi.");
        }
        if (!item.bolehDipinjam()) {
            throw new IllegalStateException("Koleksi ini tidak dapat dipinjam.");
        }
        if (!item.tersedia()) {
            throw new IllegalStateException("Tidak ada eksemplar koleksi yang tersedia.");
        }

        Peminjaman pinjaman = new Peminjaman(peminjam, item, LocalDate.now());
        item.kurangiStok();
        peminjam.mulaiMeminjam(pinjaman);
        riwayatPeminjaman.add(pinjaman);
        return pinjaman;
    }

    public void kembalikan(String nomorAnggota, String nomorInduk) {
        Anggota peminjam = cariAnggota(nomorAnggota);
        Koleksi item = cariKoleksi(nomorInduk);
        Peminjaman pinjaman = riwayatPeminjaman.stream()
                .filter(p -> p.isAktif()
                        && p.getAnggota() == peminjam
                        && p.getKoleksi() == item)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Peminjaman aktif tidak ditemukan."));

        pinjaman.kembalikan(LocalDate.now());
        item.tambahStok();
        peminjam.selesaiMeminjam(pinjaman);
    }

    public List<Koleksi> getKoleksi() {
        return List.copyOf(koleksi.values());
    }

    public List<Peminjaman> getRiwayatPeminjaman(String nomorAnggota) {
        Anggota peminjam = cariAnggota(nomorAnggota);
        return riwayatPeminjaman.stream()
                .filter(p -> p.getAnggota() == peminjam)
                .toList();
    }

    private Anggota cariAnggota(String nomorAnggota) {
        Anggota hasil = anggota.get(requireText(nomorAnggota, "Nomor anggota"));
        if (hasil == null) {
            throw new NoSuchElementException("Anggota tidak ditemukan: " + nomorAnggota);
        }
        return hasil;
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " tidak boleh kosong.");
        }
        return value;
    }
}
