public class Buku {
    // TODO 1: Enkapsulasi data dan tetapkan ISBN serta jumlah awal sebagai atribut yang tidak berubah.
    private final String isbn;
    private final String judul;
    private final String penulis;
    private final int jumlahEksemplar;
    private int jumlahTersedia;

    // TODO 2: Tolak ISBN, judul, atau penulis kosong dan jumlah eksemplar negatif.
    public Buku(String isbn, String judul, String penulis, int jumlahEksemplar) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN tidak boleh null atau kosong.");
        }
        if (judul == null || judul.trim().isEmpty()) {
            throw new IllegalArgumentException("Judul tidak boleh null atau kosong.");
        }
        if (penulis == null || penulis.trim().isEmpty()) {
            throw new IllegalArgumentException("Penulis tidak boleh null atau kosong.");
        }
        if (jumlahEksemplar < 0) {
            throw new IllegalArgumentException("Jumlah eksemplar tidak boleh negatif.");
        }

        this.isbn = isbn;
        this.judul = judul;
        this.penulis = penulis;
        this.jumlahEksemplar = jumlahEksemplar;
        this.jumlahTersedia = jumlahEksemplar;
    }

    // TODO 3: Lengkapi getter untuk seluruh atribut.
    public String getIsbn() { return isbn; }
    public String getJudul() { return judul; }
    public String getPenulis() { return penulis; }
    public int getJumlahEksemplar() { return jumlahEksemplar; }
    public int getJumlahTersedia() { return jumlahTersedia; }

    // TODO 4: Tolak peminjaman saat stok habis dan kurangi jumlah tersedia.
    public void pinjam() {
        if (jumlahTersedia == 0) {
            throw new IllegalStateException("Tidak ada eksemplar buku yang tersedia untuk dipinjam.");
        }
        jumlahTersedia--;
    }

    // TODO 5: Validasi pengembalian dan tambah jumlah tersedia.
    public void kembalikan() {
        if (jumlahTersedia == jumlahEksemplar) {
            throw new IllegalStateException("Tidak ada peminjaman yang perlu dikembalikan.");
        }
        jumlahTersedia++;
    }
}