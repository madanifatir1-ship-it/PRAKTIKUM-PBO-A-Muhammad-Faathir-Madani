public class KomposisiVsWarisan {

    private static class Mesin {
        public String nyalakan() {
            return "Mesin menyala.";
        }
    }

    private static class SumberTenaga {
        private String nama;

        public SumberTenaga(String nama) {
            this.nama = nama;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }
    }

    // TODO 1: Selesai — ubah pewarisan Mobil extends Mesin menjadi komposisi.
    private static class Mobil {
        private final Mesin mesin;
        private SumberTenaga sumberTenaga;

        public Mobil(Mesin mesin, SumberTenaga sumberTenaga) {
            this.mesin = mesin;
            this.sumberTenaga = sumberTenaga;
        }

        public void setSumberTenaga(SumberTenaga sumberTenaga) {
            this.sumberTenaga = sumberTenaga;
        }

        public SumberTenaga getSumberTenaga() {
            return sumberTenaga;
        }

        public String berjalan() {
            return "Mobil berjalan menggunakan " + sumberTenaga.getNama() + ". " + mesin.nyalakan();
        }
    }

    public static void main(String[] args) {
        Mobil mobil = new Mobil(new Mesin(), new SumberTenaga("bensin"));
        System.out.println(mobil.berjalan());

        // TODO 2: Selesai — buat Mobil memiliki SumberTenaga dan ganti sumber tenaga
        //         saat program berjalan tanpa membuat objek Mobil baru.
        mobil.setSumberTenaga(new SumberTenaga("listrik"));
        System.out.println("Sumber tenaga diganti menjadi " + mobil.getSumberTenaga().getNama() + ".");
        System.out.println(mobil.berjalan());
    }
}
