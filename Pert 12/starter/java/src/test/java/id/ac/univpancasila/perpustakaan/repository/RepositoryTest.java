package id.ac.univpancasila.perpustakaan.repository;

import id.ac.univpancasila.perpustakaan.denda.DendaMahasiswa;
import id.ac.univpancasila.perpustakaan.exception.AnggotaTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.BatasPinjamTerlampauiException;
import id.ac.univpancasila.perpustakaan.exception.BukuSedangDipinjamException;
import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.model.Buku;
import id.ac.univpancasila.perpustakaan.service.LibraryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    @Test
    @DisplayName("InMemoryBookRepository menyimpan, mencari, dan mengurangi stok buku dengan benar")
    void simpanCariDanKurangiStok() {
        BookRepository repo = new InMemoryBookRepository();
        Buku buku = new Buku("978-1", "Clean Code", "Robert C. Martin", 2);
        repo.simpan(buku);

        Optional<Buku> ditemukan = repo.cariByIsbn("978-1");
        assertTrue(ditemukan.isPresent());
        assertEquals("Clean Code", ditemukan.get().getJudul());
        assertEquals(1, repo.cariSemua().size());

        repo.kurangiStok(ditemukan.get());
        assertEquals(1, ditemukan.get().getJumlahEksemplar());
    }

    @Test
    @DisplayName("Exception: Meminjam dengan ID Anggota yang tidak terdaftar")
    void exceptionPinjamAnggotaTidakAda() {
        LibraryService service = new LibraryService(new InMemoryBookRepository(), new DendaMahasiswa());
        assertThrows(AnggotaTidakDitemukanException.class, () -> {
            service.pinjam("ANG-NGASAL", "978-1");
        }, "Harus melempar AnggotaTidakDitemukanException jika Anggota tidak terdaftar.");
    }

    @Test
    @DisplayName("Exception: Meminjam buku tidak ditemukan, stok habis, dan melampaui batas pinjam")
    void exceptionPinjamValidasiBisnis() {
        LibraryService service = new LibraryService(new InMemoryBookRepository(), new DendaMahasiswa());
        service.daftarkanAnggota("A-001");
        service.daftarkanAnggota("A-002");
        service.tambahBuku("111", "Laskar Pelangi", "Andrea Hirata", 1);
        service.tambahBuku("222", "Bumi Manusia", "Pramoedya", 5);

        assertThrows(BukuTidakDitemukanException.class, () -> service.pinjam("A-001", "999"));

        assertTrue(service.pinjam("A-001", "111"));
        assertThrows(BukuSedangDipinjamException.class, () -> service.pinjam("A-002", "111"));

        assertTrue(service.pinjam("A-001", "222"));
        assertTrue(service.pinjam("A-001", "222"));
        assertThrows(BatasPinjamTerlampauiException.class, () -> service.pinjam("A-001", "222"));
    }
}
