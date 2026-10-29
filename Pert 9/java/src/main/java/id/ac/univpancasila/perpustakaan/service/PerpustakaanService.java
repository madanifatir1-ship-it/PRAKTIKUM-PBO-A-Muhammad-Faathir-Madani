package id.ac.univpancasila.perpustakaan.service;

import id.ac.univpancasila.perpustakaan.model.Anggota;
import id.ac.univpancasila.perpustakaan.model.Buku;
import id.ac.univpancasila.perpustakaan.model.Peminjaman;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class PerpustakaanService {
    private final Map<String, Buku> daftarBuku = new LinkedHashMap<>();
    private final Map<String, Anggota> daftarAnggota = new LinkedHashMap<>();
    private final List<Peminjaman> riwayatPeminjaman = new ArrayList<>();

    public void tambahBuku(Buku buku) {
        Objects.requireNonNull(buku, "Buku tidak boleh null.");
        if (daftarBuku.putIfAbsent(buku.getKode(), buku) != null) {
            throw new IllegalArgumentException("Kode buku sudah terdaftar.");
        }
    }

    public void daftarkanAnggota(Anggota anggota) {
        Objects.requireNonNull(anggota, "Anggota tidak boleh null.");
        if (daftarAnggota.putIfAbsent(anggota.getNomorAnggota(), anggota) != null) {
            throw new IllegalArgumentException("Nomor anggota sudah terdaftar.");
        }
    }

    public Buku cariBuku(String kode) {
        Buku buku = daftarBuku.get(kode);
        if (buku == null) {
            throw new NoSuchElementException("Buku tidak ditemukan: " + kode);
        }
        return buku;
    }

    public Peminjaman pinjam(String nomorAnggota, String kodeBuku, LocalDate tanggalPinjam) {
        Anggota anggota = daftarAnggota.get(nomorAnggota);
        if (anggota == null) {
            throw new NoSuchElementException("Anggota tidak ditemukan: " + nomorAnggota);
        }

        Buku buku = cariBuku(kodeBuku);
        if (buku.getStok() == 0) {
            throw new IllegalStateException("Stok buku tidak tersedia.");
        }

        Peminjaman peminjaman = new Peminjaman(
                anggota,
                buku,
                Objects.requireNonNullElseGet(tanggalPinjam, LocalDate::now));
        buku.kurangiStok();
        riwayatPeminjaman.add(peminjaman);
        return peminjaman;
    }

    public List<Buku> getDaftarBuku() {
        return List.copyOf(daftarBuku.values());
    }

    public List<Peminjaman> getRiwayatPeminjaman() {
        return List.copyOf(riwayatPeminjaman);
    }
}
