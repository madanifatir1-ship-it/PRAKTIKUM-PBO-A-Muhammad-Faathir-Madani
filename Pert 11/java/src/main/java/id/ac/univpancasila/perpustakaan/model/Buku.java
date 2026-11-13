package id.ac.univpancasila.perpustakaan.model;

import java.util.Objects;

public final class Buku {
    private final String nomorInduk;
    private final String judul;
    private final String penulis;
    private int jumlahEksemplar;

    public Buku(String nomorInduk, String judul, String penulis, int jumlahEksemplar) {
        this.nomorInduk = requireText(nomorInduk, "Nomor induk");
        this.judul = requireText(judul, "Judul");
        this.penulis = requireText(penulis, "Penulis");
        if (jumlahEksemplar < 0) {
            throw new IllegalArgumentException("Jumlah eksemplar tidak boleh negatif");
        }
        this.jumlahEksemplar = jumlahEksemplar;
    }

    public String getNomorInduk() {
        return nomorInduk;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public int getJumlahEksemplar() {
        return jumlahEksemplar;
    }

    public boolean tersedia() {
        return jumlahEksemplar > 0;
    }

    public void pinjam() {
        if (!tersedia()) {
            throw new IllegalStateException("Stok buku tidak tersedia: " + nomorInduk);
        }
        jumlahEksemplar--;
    }

    private static String requireText(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " tidak boleh null");
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " tidak boleh kosong");
        }
        return value.trim();
    }

    @Override
    public String toString() {
        return nomorInduk + " - " + judul + " (" + penulis + "), tersedia: " + jumlahEksemplar;
    }
}
