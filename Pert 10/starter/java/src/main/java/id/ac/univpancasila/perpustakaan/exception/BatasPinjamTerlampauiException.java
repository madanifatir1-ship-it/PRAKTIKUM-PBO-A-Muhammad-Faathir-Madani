package id.ac.univpancasila.perpustakaan.exception;

/**
 * Exception domain yang membawa ID anggota dan batas peminjaman.
 */
public class BatasPinjamTerlampauiException extends PerpustakaanException {
    private final String idAnggota;
    private final int batas;

    public BatasPinjamTerlampauiException(String idAnggota, int batas) {
        super("Anggota dengan ID [" + idAnggota + "] telah mencapai batas pinjam [" + batas + "]");
        this.idAnggota = idAnggota;
        this.batas = batas;
    }

    public String getIdAnggota() {
        return idAnggota;
    }

    public int getBatas() {
        return batas;
    }
}
