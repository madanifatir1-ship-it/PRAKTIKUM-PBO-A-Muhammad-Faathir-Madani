package model;

public class Skripsi extends Koleksi {
    public Skripsi(String nomorInduk, String judul, String penulis, int jumlahEksemplar) {
        super(nomorInduk, judul, penulis, jumlahEksemplar);
    }

    public Skripsi(
            String nomorInduk,
            String judul,
            String penulis,
            int tahun,
            int jumlahEksemplar) {
        super(nomorInduk, judul, penulis, tahun, jumlahEksemplar);
    }

    @Override
    public int masaPinjamHari() {
        return 0;
    }

    @Override
    public boolean bolehDipinjam() {
        return false;
    }
}
