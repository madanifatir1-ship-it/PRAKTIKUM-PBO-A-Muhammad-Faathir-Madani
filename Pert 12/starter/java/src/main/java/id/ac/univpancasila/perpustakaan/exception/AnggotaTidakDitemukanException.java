package id.ac.univpancasila.perpustakaan.exception;

public class AnggotaTidakDitemukanException extends PerpustakaanException {
    public AnggotaTidakDitemukanException(String idAnggota) {
        super("Anggota dengan ID '" + idAnggota + "' tidak terdaftar dalam sistem.");
    }
}
