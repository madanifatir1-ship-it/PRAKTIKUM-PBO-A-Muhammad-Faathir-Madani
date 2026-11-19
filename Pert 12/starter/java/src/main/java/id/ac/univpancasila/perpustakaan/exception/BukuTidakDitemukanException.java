package id.ac.univpancasila.perpustakaan.exception;

public class BukuTidakDitemukanException extends PerpustakaanException {
    public BukuTidakDitemukanException(String isbn) {
        super("Buku dengan ISBN '" + isbn + "' tidak ditemukan di katalog.");
    }
}
