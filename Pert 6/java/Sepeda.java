public class Sepeda extends Kendaraan implements Movable {

    public Sepeda(String merek, int tahun) {
        super(merek, tahun);
    }

    // TODO 1: sepeda punya dua roda, biar sesuai bentuknya.
    @Override public int jumlahRoda() { return 2; }

    // TODO 2: tampilkan cara sepeda bergerak tanpa perlu bahan bakar.
    @Override public void bergerak() {
        System.out.println(merek + " dikayuh santai");
    }

    // TODO 3: kembalikan kecepatan maksimum sepeda yang masuk akal.
    @Override public double kecepatanMaksimum() { return 25.0; }
}
