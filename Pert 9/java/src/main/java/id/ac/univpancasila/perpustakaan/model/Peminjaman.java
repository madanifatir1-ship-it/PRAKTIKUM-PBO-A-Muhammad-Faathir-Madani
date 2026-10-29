package id.ac.univpancasila.perpustakaan.model;

import java.time.LocalDate;
import java.util.Objects;

public final class Peminjaman {
    private final Anggota anggota;
    private final Buku buku;
    private final LocalDate tanggalPinjam;
    private final LocalDate tanggalJatuhTempo;

    public Peminjaman(Anggota anggota, Buku buku, LocalDate tanggalPinjam) {
        this.anggota = Objects.requireNonNull(anggota, "Anggota tidak boleh null.");
        this.buku = Objects.requireNonNull(buku, "Buku tidak boleh null.");
        this.tanggalPinjam = Objects.requireNonNull(tanggalPinjam, "Tanggal pinjam tidak boleh null.");
        this.tanggalJatuhTempo = tanggalPinjam.plusDays(14);
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public Buku getBuku() {
        return buku;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }

    public LocalDate getTanggalJatuhTempo() {
        return tanggalJatuhTempo;
    }
}
