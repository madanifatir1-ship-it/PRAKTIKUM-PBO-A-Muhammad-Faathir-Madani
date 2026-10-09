    <?php
declare(strict_types=1);

// ══ INTERFACE — kontrak "apa yang bisa dilakukan" ═══════════════
interface Movable
{
    public function bergerak(): void;
    public function kecepatanMaksimum(): float;
}

interface Fuelable
{
    public function isiBahanBakar(float $jumlah): void;
    public function kapasitasTangki(): float;
    public function tipeBahanBakar(): TipeBahanBakar;
}

// ══ ENUM (PHP 8.1+) — backed enum, punya nilai string ══════════
enum TipeBahanBakar: string
{
    case Bensin  = 'bensin';
    case Solar   = 'solar';
    // TODO 1 (Langkah 2): tambahkan listrik supaya pilihan enum makin lengkap.
    case Listrik = 'listrik';

    /** TODO 2: ubah tiap case jadi label yang enak dibaca, simpel saja. */
    public function label(): string
    {
        return match ($this) {
            self::Bensin => 'Bensin',
            self::Solar => 'Solar',
            self::Listrik => 'Listrik',
        };
    }

    /** TODO 3: pasangkan masing-masing jenis dengan tarif yang sesuai. */
    public function hargaPerSatuan(): float
    {
        return match ($this) {
            self::Bensin => 12000,
            self::Solar => 10500,
            self::Listrik => 2500,
        };
    }

    /** TODO 4: hitung biaya totalnya dengan mengalikan jumlah dan tarif. */
    public function biayaPengisian(float $jumlah): float
    {
        return $jumlah * $this->hargaPerSatuan();
    }

    /** TODO 5: cuma listrik yang kita tandai ramah lingkungan. */
    public function ramahLingkungan(): bool
    {
        return $this === self::Listrik;
    }
}

// ══ TRAIT — penggunaan ulang horizontal, khas PHP ══════════════
trait Loggable
{
    /**
     * TODO 6: cetak baris log yang rapi dengan jam dan nama kelas.
     *         [14:32:05] Mobil: servis berkala selesai
     *
     * Petunjuk: static::class memberi nama kelas yang memakai trait ini.
     */
    public function log(string $pesan): void
    {
        printf('[%s] %s: %s%s', date('H:i:s'), static::class, $pesan, PHP_EOL);
    }
}

// ══ ABSTRACT CLASS — kode yang benar-benar sama ═══════════════
abstract class Kendaraan
{
    public function __construct(
        protected readonly string $merek,
        protected readonly int    $tahun,
    ) {}

    /** TODO 7: hitung umur kendaraan, tapi jangan sampai nilainya minus. */
    public function umur(int $tahunSekarang): int
    {
        return max(0, $tahunSekarang - $this->tahun);
    }

    abstract public function jumlahRoda(): int;

    public function __toString(): string
    {
        return sprintf('%s (%d, %d roda)', $this->merek, $this->tahun, $this->jumlahRoda());
    }
}

final class Mobil extends Kendaraan implements Movable, Fuelable
{
    use Loggable;                       // trait disisipkan

    private float $isiTangki = 0;

    public function __construct(string $merek, int $tahun, private readonly float $kapasitas)
    {
        parent::__construct($merek, $tahun);
    }

    public function jumlahRoda(): int { return 4; }

    // TODO 8: lengkapi gerak dan pengisian; tangki jangan sampai luber.
    public function bergerak(): void
    {
        printf('%s melaju di jalan raya%s', $this->merek, PHP_EOL);
    }

    public function kecepatanMaksimum(): float { return 180.0; }

    public function isiBahanBakar(float $jumlah): void
    {
        if ($jumlah <= 0) {
            throw new InvalidArgumentException('Jumlah bahan bakar harus lebih dari 0.');
        }
        if ($this->isiTangki + $jumlah > $this->kapasitas) {
            throw new InvalidArgumentException('Pengisian melebihi kapasitas tangki.');
        }
        $this->isiTangki += $jumlah;
    }

    public function kapasitasTangki(): float { return $this->kapasitas; }
    public function tipeBahanBakar(): TipeBahanBakar { return TipeBahanBakar::Bensin; }
    public function getIsiTangki(): float { return $this->isiTangki; }
}

// TODO 9 (Langkah 4): bikin sepeda yang bisa bergerak, tapi nggak perlu isi bahan bakar.
final class Sepeda extends Kendaraan implements Movable
{
    public function jumlahRoda(): int { return 2; }

    public function bergerak(): void
    {
        printf('%s dikayuh santai%s', $this->merek, PHP_EOL);
    }

    public function kecepatanMaksimum(): float { return 25.0; }
}

/**
 * TODO 10 (Langkah 5): bikin pesanan ikut pakai Loggable tanpa jadi kendaraan;
 *                 begini trait bisa dipakai lintas kelas yang nggak sekerabat.
 */
final class Pesanan
{
    use Loggable;
}
