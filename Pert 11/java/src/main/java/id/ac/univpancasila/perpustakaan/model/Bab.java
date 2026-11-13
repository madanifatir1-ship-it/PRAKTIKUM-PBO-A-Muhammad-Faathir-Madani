package id.ac.univpancasila.perpustakaan.model;

public record Bab(String judul, int halaman) {
    public Bab {
        if (judul == null || judul.isBlank()) {
            throw new IllegalArgumentException("Judul bab tidak boleh kosong");
        }
        if (halaman < 1) {
            throw new IllegalArgumentException("Halaman bab harus positif");
        }
    }
}
