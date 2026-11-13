package id.ac.univpancasila.perpustakaan.model;

import java.time.LocalDate;
import java.util.Objects;

public record Peminjaman(Anggota anggota, Buku buku, LocalDate tanggalPinjam) {
    public Peminjaman {
        Objects.requireNonNull(anggota);
        Objects.requireNonNull(buku);
        Objects.requireNonNull(tanggalPinjam);
    }
}
