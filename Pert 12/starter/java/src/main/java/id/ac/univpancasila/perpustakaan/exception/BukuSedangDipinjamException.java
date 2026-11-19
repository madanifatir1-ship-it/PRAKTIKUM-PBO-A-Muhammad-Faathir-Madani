package id.ac.univpancasila.perpustakaan.exception;

public class BukuSedangDipinjamException extends PerpustakaanException {
    public BukuSedangDipinjamException(String isbn, String judul) {
        super("Buku '" + judul + "' (ISBN: " + isbn + ") sedang dipinjam semua / stok habis.");
    }

    public BukuSedangDipinjamException(String isbn) {
        super("Buku dengan ISBN '" + isbn + "' sedang dipinjam semua / stok habis.");
    }
}
