package id.ac.univpancasila.perpustakaan.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Buku {
    private final String kode;
    private final String judul;
    private final String penulis;
    private final long harga;
    private int stok;
    private final List<Bab> bab = new ArrayList<>();

    public Buku(String kode, String judul, String penulis, int stok) {
        this(kode, judul, penulis, 0L, stok);
    }

    public Buku(String kode, String judul, String penulis, long harga, int stok) {
        this.kode = requireText(kode, "Kode buku");
        this.judul = requireText(judul, "Judul buku");
        this.penulis = requireText(penulis, "Penulis buku");
        if (harga < 0) {
            throw new IllegalArgumentException("Harga buku tidak boleh negatif.");
        }
        if (stok < 0) {
            throw new IllegalArgumentException("Stok buku tidak boleh negatif.");
        }
        this.harga = harga;
        this.stok = stok;
    }

    public String getKode() {
        return kode;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public long getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public void kurangiStok() {
        if (stok == 0) {
            throw new IllegalStateException("Stok buku sudah habis.");
        }
        stok--;
    }

    public void tambahStok() {
        stok++;
    }

    public void tambahBab(int nomor, String judulBab) {
        if (nomor <= 0) {
            throw new IllegalArgumentException("Nomor bab harus lebih dari nol.");
        }
        if (judulBab == null || judulBab.isBlank()) {
            throw new IllegalArgumentException("Judul bab tidak boleh kosong.");
        }
        bab.add(new Bab(nomor, judulBab));
    }

    public List<Bab> getBab() {
        return List.copyOf(bab);
    }

    private static String requireText(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " tidak boleh null.");
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " tidak boleh kosong.");
        }
        return value;
    }

    @Override
    public String toString() {
        return kode + " - " + judul + " (" + penulis + ")";
    }

    public static final class Bab {
        private final int nomor;
        private final String judul;

        private Bab(int nomor, String judul) {
            this.nomor = nomor;
            this.judul = judul;
        }

        public int getNomor() {
            return nomor;
        }

        public String getJudul() {
            return judul;
        }

        @Override
        public String toString() {
            return "Bab " + nomor + ": " + judul;
        }
    }
}
