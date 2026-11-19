package id.ac.univpancasila.perpustakaan.model;

public class Buku {
    private final String nomorInduk;
    private final String judul;
    private final String penulis;
    private int jumlahEksemplar;

    public Buku(String nomorInduk, String judul, String penulis, int jumlahEksemplar) {
        if (nomorInduk == null || nomorInduk.isBlank()) {
            throw new IllegalArgumentException("ISBN / Nomor Induk buku tidak boleh kosong.");
        }
        if (judul == null || judul.isBlank()) {
            throw new IllegalArgumentException("Judul buku tidak boleh kosong.");
        }
        if (penulis == null || penulis.isBlank()) {
            throw new IllegalArgumentException("Penulis buku tidak boleh kosong.");
        }
        if (jumlahEksemplar < 0) {
            throw new IllegalArgumentException("Jumlah eksemplar tidak boleh negatif.");
        }
        this.nomorInduk = nomorInduk.trim();
        this.judul = judul.trim();
        this.penulis = penulis.trim();
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

    public void tambahStok() {
        this.jumlahEksemplar++;
    }

    public void kurangiStok() {
        if (this.jumlahEksemplar <= 0) {
            throw new IllegalStateException("Stok buku '" + judul + "' sudah habis.");
        }
        this.jumlahEksemplar--;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (Stok: %d)", nomorInduk, judul, penulis, jumlahEksemplar);
    }
}
