package id.ac.univpancasila.perpustakaan.service;

import id.ac.univpancasila.perpustakaan.exception.AnggotaTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.BatasPinjamTerlampauiException;
import id.ac.univpancasila.perpustakaan.exception.BukuSedangDipinjamException;
import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.model.Anggota;
import id.ac.univpancasila.perpustakaan.model.Buku;
import id.ac.univpancasila.perpustakaan.repository.Repository;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class LibraryService {
    public static final int BATAS_PINJAM = 3;

    private final Repository<Buku> bukuRepository = new Repository<>(Buku::getNomorInduk);
    private final Map<String, Integer> pinjaman = new LinkedHashMap<>();

    public void tambahBuku(Buku buku) {
        bukuRepository.simpan(buku);
    }

    public void daftarkanAnggota(String idAnggota) {
        if (idAnggota == null || idAnggota.isBlank()) {
            throw new IllegalArgumentException("ID anggota tidak boleh kosong");
        }
        pinjaman.putIfAbsent(idAnggota.trim(), 0);
    }

    public void pinjam(String idAnggota, String isbn) {
        if (idAnggota == null || idAnggota.isBlank()) {
            throw new IllegalArgumentException("ID anggota tidak boleh kosong");
        }
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN tidak boleh kosong");
        }
        String anggotaValid = idAnggota.trim();
        if (!pinjaman.containsKey(anggotaValid)) {
            throw new AnggotaTidakDitemukanException(anggotaValid);
        }
        String isbnValid = isbn.trim();
        Buku buku = bukuRepository.cariById(isbnValid)
                .orElseThrow(() -> new BukuTidakDitemukanException(isbnValid));
        if (pinjaman.get(anggotaValid) >= BATAS_PINJAM) {
            throw new BatasPinjamTerlampauiException(anggotaValid, BATAS_PINJAM);
        }
        if (!buku.tersedia()) {
            throw new BukuSedangDipinjamException(buku.getNomorInduk(), buku.getJudul());
        }
        buku.pinjam();
        pinjaman.merge(anggotaValid, 1, Integer::sum);
    }

    public List<Buku> getBukuTersedia() {
        return bukuRepository.cariSemua().stream()
                .filter(Buku::tersedia)
                .toList();
    }

    public String getSemuaJudulDigabung() {
        return bukuRepository.cariSemua().stream()
                .map(Buku::getJudul)
                .collect(Collectors.joining(", "));
    }

    public Map<String, Long> getJumlahBukuPerPenulis() {
        return bukuRepository.cariSemua().stream()
                .collect(Collectors.groupingBy(Buku::getPenulis, Collectors.counting()));
    }

    public List<Buku> getSemuaBuku() {
        return bukuRepository.cariSemua();
    }
}
