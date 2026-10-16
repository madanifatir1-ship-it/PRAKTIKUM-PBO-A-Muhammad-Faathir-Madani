package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Anggota {
    private static final int MAKS_PEMINJAMAN_AKTIF = 3;

    private final String nomorAnggota;
    private final String nama;
    private final JenisKeanggotaan jenisKeanggotaan;
    private final List<Peminjaman> peminjamanAktif = new ArrayList<>();

    public Anggota(String nomorAnggota, String nama, JenisKeanggotaan jenisKeanggotaan) {
        this.nomorAnggota = requireText(nomorAnggota, "Nomor anggota");
        this.nama = requireText(nama, "Nama anggota");
        this.jenisKeanggotaan = Objects.requireNonNull(
                jenisKeanggotaan, "Jenis keanggotaan tidak boleh null.");
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " tidak boleh kosong.");
        }
        return value;
    }

    public String getNomorAnggota() {
        return nomorAnggota;
    }

    public String getNama() {
        return nama;
    }

    public JenisKeanggotaan getJenisKeanggotaan() {
        return jenisKeanggotaan;
    }

    public List<Peminjaman> getPeminjamanAktif() {
        return List.copyOf(peminjamanAktif);
    }

    public void mulaiMeminjam(Peminjaman peminjaman) {
        Objects.requireNonNull(peminjaman, "Peminjaman tidak boleh null.");
        if (peminjaman.getAnggota() != this) {
            throw new IllegalArgumentException("Peminjaman tercatat atas anggota lain.");
        }
        if (!peminjaman.isAktif()) {
            throw new IllegalArgumentException("Peminjaman yang sudah selesai tidak dapat ditambahkan.");
        }
        if (peminjamanAktif.contains(peminjaman)) {
            throw new IllegalArgumentException("Peminjaman sudah tercatat sebagai peminjaman aktif.");
        }
        if (peminjamanAktif.size() >= MAKS_PEMINJAMAN_AKTIF) {
            throw new IllegalStateException("Anggota tidak boleh meminjam lebih dari tiga koleksi.");
        }
        peminjamanAktif.add(peminjaman);
    }

    public void selesaiMeminjam(Peminjaman peminjaman) {
        Objects.requireNonNull(peminjaman, "Peminjaman tidak boleh null.");
        if (peminjaman.getAnggota() != this) {
            throw new IllegalArgumentException("Peminjaman aktif anggota tidak ditemukan.");
        }
        if (peminjaman.isAktif()) {
            throw new IllegalStateException("Peminjaman harus dikembalikan sebelum ditutup.");
        }
        if (!peminjamanAktif.remove(peminjaman)) {
            throw new IllegalArgumentException("Peminjaman aktif anggota tidak ditemukan.");
        }
    }

    @Override
    public String toString() {
        return nomorAnggota + " - " + nama + " (" + jenisKeanggotaan + ")";
    }
}
