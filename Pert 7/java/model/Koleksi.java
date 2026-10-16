package model;

import java.util.Objects;

public abstract class Koleksi {
    private final String nomorInduk;
    private final String judul;
    private final String penulis;
    private final int tahun;
    private int jumlahEksemplar;
    private StatusKoleksi status;

    protected Koleksi(String nomorInduk, String judul, String penulis, int jumlahEksemplar) {
        this(nomorInduk, judul, penulis, 0, jumlahEksemplar);
    }

    protected Koleksi(
            String nomorInduk,
            String judul,
            String penulis,
            int tahun,
            int jumlahEksemplar) {
        this.nomorInduk = requireText(nomorInduk, "Nomor induk");
        this.judul = requireText(judul, "Judul");
        this.penulis = requireText(penulis, "Penulis");
        if (tahun < 0) {
            throw new IllegalArgumentException("Tahun terbit tidak valid.");
        }
        if (jumlahEksemplar < 0) {
            throw new IllegalArgumentException("Jumlah eksemplar tidak boleh negatif.");
        }
        this.tahun = tahun;
        this.jumlahEksemplar = jumlahEksemplar;
        this.status = StatusKoleksi.TERSEDIA;
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " tidak boleh kosong.");
        }
        return value;
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

    public int getTahun() {
        return tahun;
    }

    public int getJumlahEksemplar() {
        return jumlahEksemplar;
    }

    public StatusKoleksi getStatus() {
        return status;
    }

    public void setStatus(StatusKoleksi status) {
        this.status = Objects.requireNonNull(status, "Status koleksi tidak boleh null.");
    }

    public boolean tersedia() {
        return status == StatusKoleksi.TERSEDIA && jumlahEksemplar > 0;
    }

    public void kurangiStok() {
        if (!tersedia()) {
            throw new IllegalStateException("Tidak ada eksemplar koleksi yang tersedia.");
        }
        jumlahEksemplar--;
    }

    public void tambahStok() {
        if (jumlahEksemplar == Integer.MAX_VALUE) {
            throw new IllegalStateException("Jumlah eksemplar sudah mencapai batas maksimum.");
        }
        jumlahEksemplar++;
    }

    public abstract int masaPinjamHari();

    public abstract boolean bolehDipinjam();

    @Override
    public String toString() {
        return String.format("%s - %s (%d)", nomorInduk, judul, tahun);
    }
}
