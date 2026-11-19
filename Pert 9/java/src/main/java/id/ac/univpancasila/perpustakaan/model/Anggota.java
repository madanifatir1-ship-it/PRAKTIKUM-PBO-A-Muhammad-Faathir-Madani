package id.ac.univpancasila.perpustakaan.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Anggota extends Jurusan {
    private final String kodeAnggota;
    private LocalDateTime anggotaSejak;
    private Jurusan.Mahasiswa anggotaPerpus;

    public Anggota(String kodeJurusan, String namaJurusan, String kodeAnggota) {
        super(kodeJurusan, namaJurusan);
        this.kodeAnggota = requireText(kodeAnggota, "Kode anggota");
    }

    public void daftarkan(Jurusan.Mahasiswa mahasiswa, LocalDateTime masaKeanggotaan) {
        Objects.requireNonNull(mahasiswa, "Mahasiswa tidak boleh null.");
        if (masaKeanggotaan == null) {
            throw new IllegalArgumentException("Tanggal keanggotaan tidak boleh null.");
        }
        this.anggotaPerpus = mahasiswa;
        this.anggotaSejak = masaKeanggotaan;
    }

    public String checkMasaKeanggotaan() {
        if (anggotaSejak == null) {
            throw new IllegalStateException("Mahasiswa belum didaftarkan sebagai anggota.");
        }
        LocalDateTime sekarang = LocalDateTime.now();
        return sekarang.isAfter(anggotaSejak.plusYears(3))
                ? "Keanggotaan tidak valid (lebih dari 3 tahun)"
                : "Keanggotaan valid";
    }

    public void getKeanggotaan() {
        if (anggotaPerpus == null || anggotaSejak == null) {
            throw new IllegalStateException("Data keanggotaan belum lengkap.");
        }
        System.out.println("Nama Anggota: " + anggotaPerpus.getNama());
        System.out.println("Jurusan: " + super.getNama());
        System.out.println("Kode Anggota: " + kodeAnggota);
        System.out.println("Anggota Perpustakaan Sejak: " + formatDateMasaAnggota(anggotaSejak));
    }

    public String getKodeAnggota() {
        return kodeAnggota;
    }

    public LocalDateTime getAnggotaSejak() {
        return anggotaSejak;
    }

    private String formatDateMasaAnggota(LocalDateTime tanggal) {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy").format(tanggal);
    }

    private static String requireText(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " tidak boleh null.");
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " tidak boleh kosong.");
        }
        return value;
    }
}
