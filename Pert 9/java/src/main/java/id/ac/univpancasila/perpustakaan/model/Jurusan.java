package id.ac.univpancasila.perpustakaan.model;

import java.util.ArrayList;
import java.util.List;

public class Jurusan {
    private final String kode;
    private final String nama;
    private final List<Mahasiswa> mahasiswa = new ArrayList<>();

    public Jurusan(String kode, String nama) {
        if (kode == null || kode.isBlank()) {
            throw new IllegalArgumentException("Kode jurusan tidak boleh kosong.");
        }
        if (nama == null || nama.isBlank()) {
            throw new IllegalArgumentException("Nama jurusan tidak boleh kosong.");
        }
        this.kode = kode;
        this.nama = nama;
    }

    public void daftarkan(Mahasiswa mahasiswa) {
        if (mahasiswa == null) {
            throw new IllegalArgumentException("Mahasiswa tidak boleh null.");
        }
        for (Mahasiswa item : mahasiswaListSnapshot()) {
            if (item.getNim().equals(mahasiswa.getNim())) {
                return;
            }
        }
        this.mahasiswa.add(mahasiswa);
    }

    private List<Mahasiswa> mahasiswaListSnapshot() {
        return List.copyOf(mahasiswa);
    }

    public void keluarkan(Mahasiswa mahasiswa) {
        if (mahasiswa != null) {
            this.mahasiswa.removeIf(item -> item.getNim().equals(mahasiswa.getNim()));
        }
    }

    public List<Mahasiswa> getMahasiswa() {
        return List.copyOf(mahasiswa);
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return kode + " - " + nama + " (" + mahasiswa.size() + " mahasiswa)";
    }

    public static final class Mahasiswa {
        private final String nim;
        private final String nama;

        public Mahasiswa(String nim, String nama) {
            if (nim == null || nim.isBlank()) {
                throw new IllegalArgumentException("NIM tidak boleh kosong.");
            }
            if (nama == null || nama.isBlank()) {
                throw new IllegalArgumentException("Nama mahasiswa tidak boleh kosong.");
            }
            this.nim = nim;
            this.nama = nama;
        }

        public String getNim() {
            return nim;
        }

        public String getNama() {
            return nama;
        }

        @Override
        public String toString() {
            return nim + " " + nama;
        }
    }
}
