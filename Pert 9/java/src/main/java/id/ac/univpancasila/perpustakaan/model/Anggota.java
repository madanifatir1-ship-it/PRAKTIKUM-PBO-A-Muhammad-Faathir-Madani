package id.ac.univpancasila.perpustakaan.model;

import java.util.Objects;

public final class Anggota {
    private final String nomorAnggota;
    private final String nama;

    public Anggota(String nomorAnggota, String nama) {
        this.nomorAnggota = requireText(nomorAnggota, "Nomor anggota");
        this.nama = requireText(nama, "Nama anggota");
    }

    public String getNomorAnggota() {
        return nomorAnggota;
    }

    public String getNama() {
        return nama;
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
        return nomorAnggota + " - " + nama;
    }
}
