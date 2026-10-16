package model;

public class Majalah extends Koleksi {
    public Majalah(String nomorInduk, String judul, String penulis, int jumlahEksemplar) {
        super(nomorInduk, judul, penulis, jumlahEksemplar);
    }

    public Majalah(
            String nomorInduk,
            String judul,
            String penulis,
            int tahun,
            int jumlahEksemplar) {
        super(nomorInduk, judul, penulis, tahun, jumlahEksemplar);
    }

    @Override
    public int masaPinjamHari() {
        return 3;
    }

    @Override
    public boolean bolehDipinjam() {
        return getStatus() == StatusKoleksi.TERSEDIA;
    }
}
