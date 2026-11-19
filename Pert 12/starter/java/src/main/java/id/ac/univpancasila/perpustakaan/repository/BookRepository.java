package id.ac.univpancasila.perpustakaan.repository;

import id.ac.univpancasila.perpustakaan.model.Buku;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    void simpan(Buku buku);

    Optional<Buku> cariByIsbn(String isbn);

    List<Buku> cariSemua();

    void kurangiStok(Buku buku);
}
