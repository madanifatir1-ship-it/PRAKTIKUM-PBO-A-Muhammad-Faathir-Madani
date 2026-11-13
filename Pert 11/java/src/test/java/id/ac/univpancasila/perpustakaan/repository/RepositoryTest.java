package id.ac.univpancasila.perpustakaan.repository;

import id.ac.univpancasila.perpustakaan.exception.AnggotaTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.BatasPinjamTerlampauiException;
import id.ac.univpancasila.perpustakaan.exception.BukuSedangDipinjamException;
import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.model.Buku;
import id.ac.univpancasila.perpustakaan.service.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RepositoryTest {
    record DataBuku(String isbn, String judul) {
    }

    private Repository<DataBuku> repo;

    @BeforeEach
    void siapkan() {
        repo = new Repository<>(DataBuku::isbn);
    }

    @Test
    @DisplayName("Item yang disimpan dapat ditemukan kembali")
    void simpanLaluTemukan() {
        repo.simpan(new DataBuku("978-1", "PBO"));
        assertTrue(repo.cariById("978-1").isPresent());
        assertEquals("PBO", repo.cariById("978-1").orElseThrow().judul());
    }

    @Test
    @DisplayName("Mencari id yang tidak ada mengembalikan Optional kosong")
    void cariYangTidakAda() {
        assertTrue(repo.cariById("tidak-ada").isEmpty());
    }

    @Test
    @DisplayName("Item yang dihapus tidak dapat ditemukan lagi")
    void hapusItem() {
        repo.simpan(new DataBuku("978-1", "PBO"));
        assertTrue(repo.hapus("978-1"));
        assertTrue(repo.cariById("978-1").isEmpty());
    }

    @Test
    @DisplayName("Repository baru berisi nol item")
    void repositoryKosong() {
        assertEquals(0, repo.jumlah());
        assertTrue(repo.cariSemua().isEmpty());
    }

    @Test
    @DisplayName("Koleksi yang dikembalikan cariSemua tidak dapat diubah dari luar")
    void koleksiTidakBocor() {
        repo.simpan(new DataBuku("978-1", "PBO"));
        assertThrows(UnsupportedOperationException.class,
                () -> repo.cariSemua().add(new DataBuku("978-2", "Java")));
    }

    @Test
    @DisplayName("Item dengan kunci sama menimpa data sebelumnya")
    void kunciSamaMenimpaData() {
        repo.simpan(new DataBuku("978-1", "PBO"));
        repo.simpan(new DataBuku("978-1", "PBO Lanjutan"));
        assertEquals(1, repo.jumlah());
        assertEquals("PBO Lanjutan", repo.cariById("978-1").orElseThrow().judul());
    }

    @Test
    @DisplayName("Menghapus id yang tidak ada menghasilkan false")
    void hapusIdTidakAda() {
        assertFalse(repo.hapus("tidak-ada"));
    }

    @Test
    @DisplayName("Peminjaman ISBN yang tidak terdaftar melempar exception")
    void isbnTidakTerdaftar() {
        LibraryService service = new LibraryService();
        service.daftarkanAnggota("A001");
        assertThrows(BukuTidakDitemukanException.class, () -> service.pinjam("A001", "ISBN-X"));
    }

    @Test
    @DisplayName("Peminjaman oleh anggota yang tidak terdaftar melempar exception")
    void anggotaTidakTerdaftar() {
        LibraryService service = new LibraryService();
        service.tambahBuku(new Buku("ISBN-1", "PBO", "Penulis", 1));
        assertThrows(AnggotaTidakDitemukanException.class, () -> service.pinjam("A-X", "ISBN-1"));
    }

    @Test
    @DisplayName("Peminjaman buku dengan stok habis melempar exception")
    void bukuHabis() {
        LibraryService service = new LibraryService();
        service.daftarkanAnggota("A001");
        service.tambahBuku(new Buku("ISBN-1", "PBO", "Penulis", 0));
        assertThrows(BukuSedangDipinjamException.class, () -> service.pinjam("A001", "ISBN-1"));
    }

    @Test
    @DisplayName("Peminjaman lebih dari batas anggota melempar exception")
    void batasPinjamAnggota() {
        LibraryService service = new LibraryService();
        service.daftarkanAnggota("A001");
        service.tambahBuku(new Buku("ISBN-1", "PBO", "Penulis", 4));
        service.pinjam("A001", "ISBN-1");
        service.pinjam("A001", "ISBN-1");
        service.pinjam("A001", "ISBN-1");
        assertThrows(BatasPinjamTerlampauiException.class, () -> service.pinjam("A001", "ISBN-1"));
    }
}
