/**
 * Program uji — JANGAN DIUBAH pada Langkah 1 sampai 4.
 * Kalau kode Anda benar, seluruh keluaran di bawah akan masuk akal.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("=== Rekap Nilai ===");
        Mahasiswa[] kelas = {
            new Mahasiswa("2024001", "Ani Lestari",  85, 78, 90),
            new Mahasiswa("2024002", "Budi Santoso", 60, 55, 62),
            new Mahasiswa("2024003", "Citra Wijaya", 92, 88, 95)
        };
        for (Mahasiswa m : kelas) {
            System.out.println("  " + m);
        }

        System.out.println();
        System.out.println("=== Objek menolak data yang melanggar aturan ===");

        try {
            System.out.println(new Mahasiswa("2024004", "Salah Nilai", 150, 80, 80));
            System.out.println("  MASALAH: nilai 150 seharusnya ditolak!");
        } catch (IllegalArgumentException e) {
            System.out.println("  Ditolak: " + e.getMessage());
        }

        try {
            System.out.println(new Mahasiswa("", "NIM Kosong", 80, 80, 80));
            System.out.println("  MASALAH: NIM kosong seharusnya ditolak!");
        } catch (IllegalArgumentException e) {
            System.out.println("  Ditolak: " + e.getMessage());
        }

        try {
            kelas[0].setNilaiTugas(150);
            System.out.println("  MASALAH: setter seharusnya menolak nilai 150!");
        } catch (IllegalArgumentException e) {
            System.out.println("  Ditolak setter: " + e.getMessage());
        }

        System.out.println();
        System.out.println("=== Uji peminjaman buku ===");
        Buku buku = new Buku("978-602-000000-0", "Pemrograman Berorientasi Objek", "Tim Praktikum", 1);
        buku.pinjam();
        System.out.println("  Sisa setelah dipinjam: " + buku.getJumlahTersedia());
        try {
            buku.pinjam();
            System.out.println("  MASALAH: buku habis seharusnya tidak dapat dipinjam!");
        } catch (IllegalStateException e) {
            System.out.println("  Ditolak: " + e.getMessage());
        }
        buku.kembalikan();
        System.out.println("  Sisa setelah dikembalikan: " + buku.getJumlahTersedia());
    }
}
