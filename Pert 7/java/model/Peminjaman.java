package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Peminjaman {
    private final Anggota anggota;
    private final Koleksi koleksi;
    private final LocalDate tanggalPinjam;
    private final LocalDate tanggalJatuhTempo;
    private LocalDate tanggalKembali;
    private long denda;

    public Peminjaman(Anggota anggota, Koleksi koleksi, LocalDate tanggalPinjam) {
        this.anggota = Objects.requireNonNull(anggota, "Anggota tidak boleh null.");
        this.koleksi = Objects.requireNonNull(koleksi, "Koleksi tidak boleh null.");
        this.tanggalPinjam = Objects.requireNonNull(tanggalPinjam, "Tanggal pinjam tidak boleh null.");
        if (!koleksi.bolehDipinjam()) {
            throw new IllegalStateException("Koleksi ini tidak dapat dipinjam.");
        }
        if (!koleksi.tersedia()) {
            throw new IllegalStateException("Tidak ada eksemplar koleksi yang tersedia.");
        }
        int masaPinjam = koleksi.masaPinjamHari();
        if (masaPinjam < 0) {
            throw new IllegalStateException("Masa pinjam tidak boleh negatif.");
        }
        this.tanggalJatuhTempo = tanggalPinjam.plusDays(masaPinjam);
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public Koleksi getKoleksi() {
        return koleksi;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }

    public LocalDate getTanggalJatuhTempo() {
        return tanggalJatuhTempo;
    }

    public LocalDate getTanggalKembali() {
        return tanggalKembali;
    }

    public long getDenda() {
        return denda;
    }

    public boolean isAktif() {
        return tanggalKembali == null;
    }

    public long kembalikan(LocalDate tanggal) {
        Objects.requireNonNull(tanggal, "Tanggal kembali tidak boleh null.");
        if (!isAktif()) {
            throw new IllegalStateException("Peminjaman sudah dikembalikan.");
        }
        if (tanggal.isBefore(tanggalPinjam)) {
            throw new IllegalArgumentException("Tanggal kembali tidak boleh sebelum tanggal pinjam.");
        }

        tanggalKembali = tanggal;
        long hariTerlambat = Math.max(0, ChronoUnit.DAYS.between(tanggalJatuhTempo, tanggal));
        denda = Math.multiplyExact(
                hariTerlambat, anggota.getJenisKeanggotaan().getDendaPerHari());
        return denda;
    }
}
