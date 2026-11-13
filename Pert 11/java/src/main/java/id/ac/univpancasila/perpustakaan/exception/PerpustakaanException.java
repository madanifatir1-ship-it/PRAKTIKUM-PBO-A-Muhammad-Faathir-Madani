package id.ac.univpancasila.perpustakaan.exception;

public abstract class PerpustakaanException extends RuntimeException {
    protected PerpustakaanException(String pesan) {
        super(pesan);
    }
}
