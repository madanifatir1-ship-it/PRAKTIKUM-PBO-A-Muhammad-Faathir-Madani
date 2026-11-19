package id.ac.univpancasila.perpustakaan.exception;

public class BatasPinjamTerlampauiException extends PerpustakaanException {
    public BatasPinjamTerlampauiException(String idAnggota, int batasPinjam) {
        super("Anggota '" + idAnggota + "' telah mencapai batas maksimal peminjaman (" + batasPinjam + " buku).");
    }
}
