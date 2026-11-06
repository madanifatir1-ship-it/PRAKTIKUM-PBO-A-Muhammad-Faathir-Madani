package id.ac.univpancasila.perpustakaan.exception;

/**
 * Exception domain yang membawa ISBN dan judul buku.
 */
public class BukuSedangDipinjamException extends PerpustakaanException {
    private final String isbn;
    private final String judul;

    public BukuSedangDipinjamException(String isbn, String judul) {
        super("Buku dengan ISBN [" + isbn + "] berjudul \"" + judul + "\" sedang habis/stok tidak tersedia");
        this.isbn = isbn;
        this.judul = judul;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getJudul() {
        return judul;
    }
}
