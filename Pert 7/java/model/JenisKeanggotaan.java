package model;

public enum JenisKeanggotaan {
    MAHASISWA(1_000),
    DOSEN(500),
    ANGGOTA_KEHORMATAN(0);

    private final long dendaPerHari;

    JenisKeanggotaan(long dendaPerHari) {
        this.dendaPerHari = dendaPerHari;
    }

    public long getDendaPerHari() {
        return dendaPerHari;
    }
}
