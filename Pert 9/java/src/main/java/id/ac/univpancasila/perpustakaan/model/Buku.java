package id.ac.univpancasila.perpustakaan.model;

import java.util.Objects;

public final class Buku {
    private final String kode;
    private final String judul;
    private final String penulis;
    private final long harga;
    private int stok;

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
}
