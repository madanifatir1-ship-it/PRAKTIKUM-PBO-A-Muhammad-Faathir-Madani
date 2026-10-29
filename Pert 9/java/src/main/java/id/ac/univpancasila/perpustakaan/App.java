package id.ac.univpancasila.perpustakaan;

import com.github.freva.asciitable.AsciiTable;
import id.ac.univpancasila.perpustakaan.model.Anggota;
import id.ac.univpancasila.perpustakaan.model.Buku;
import id.ac.univpancasila.perpustakaan.model.Peminjaman;
import id.ac.univpancasila.perpustakaan.service.PerpustakaanService;
import id.ac.univpancasila.perpustakaan.util.Format;
import java.time.LocalDate;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        PerpustakaanService service = new PerpustakaanService();

        Buku buku = new Buku(
                "BKU-001",
                "Pemrograman Berorientasi Objek",
                "Muhammad Faathir Madani",
                125000L,
                3);
        Anggota anggota = new Anggota("ANG-001", "M. Faathir Madani");

        service.tambahBuku(buku);
        service.daftarkanAnggota(anggota);

        LocalDate tanggalPinjam = LocalDate.now();
        Peminjaman peminjaman = service.pinjam(
                anggota.getNomorAnggota(),
                buku.getKode(),
                tanggalPinjam);

        String[][] data = {
                {"Universitas", "Universitas Pancasila"},
                {"Anggota", anggota.getNama() + " (" + anggota.getNomorAnggota() + ")"},
                {"Buku", buku.getJudul()},
                {"Harga", Format.formatRupiah(buku.getHarga())},
                {"Tanggal Pinjam", Format.formatTanggal(peminjaman.getTanggalPinjam())},
                {"Jatuh Tempo", Format.formatTanggal(peminjaman.getTanggalJatuhTempo())},
                {"Sisa Stok", String.valueOf(buku.getStok())}
        };
        String[] header = {"SISTEM PERPUSTAKAAN", "Detail"};

        System.out.println(AsciiTable.getTable(header, data));
    }
}
