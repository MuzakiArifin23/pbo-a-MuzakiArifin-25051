<?php
declare(strict_types=1);

namespace Perpustakaan\Tests;

use PHPUnit\Framework\TestCase;
use Perpustakaan\Model\Buku;
use Perpustakaan\Model\KoleksiBuku;

/**
 * Sesi 11 — unit test PHPUnit.
 *
 * Letakkan di tests/ dan pastikan autoload-dev pada composer.json sudah
 * memetakan Perpustakaan\Tests\ ke tests/.
 *
 * Jalankan: ./vendor/bin/phpunit tests
 */
final class KoleksiBukuTest extends TestCase
{
    private KoleksiBuku $koleksi;

    protected function setUp(): void
    {
        // Dijalankan ulang sebelum SETIAP pengujian.
        $this->koleksi = new KoleksiBuku();
    }

    public function testItemYangDisimpanDapatDitemukanKembali(): void
    {
        // TODO 1
        $this->koleksi->simpan(new Buku('978-1', 'PBO', 'Penulis', 50000));
        $this->assertSame('PBO', $this->koleksi->cariByIsbn('978-1')?->judul);
    }

    public function testMencariIsbnYangTidakAdaMengembalikanNull(): void
    {
        // TODO 2
        $this->assertNull($this->koleksi->cariByIsbn('tidak-ada'));
    }

    public function testKoleksiBaruBerisiNolItem(): void
    {
        // TODO 3: perhatikan bahwa count($this->koleksi) bekerja
        //         karena kelasnya implements Countable.
        $this->assertCount(0, $this->koleksi);
    }

    public function testFilterHanyaMengembalikanYangLolosPredikat(): void
    {
        // TODO 4
        $this->koleksi->simpan(new Buku('1', 'A', 'X', 5000));
        $this->koleksi->simpan(new Buku('2', 'B', 'X', 20000));
        $hasil = $this->koleksi->filter(fn (Buku $b): bool => $b->harga > 10000);
        $this->assertCount(1, $hasil);
        $this->assertSame(['B'], $hasil->map(fn (Buku $b): string => $b->judul));
    }

    public function testJumlahkanMenghitungTotalDenganBenar(): void
    {
        // TODO 5
        $this->koleksi->simpan(new Buku('1', 'A', 'X', 5000));
        $this->koleksi->simpan(new Buku('2', 'B', 'X', 20000));
        $this->assertSame(25000.0, $this->koleksi->jumlahkan(fn (Buku $b): int => $b->harga));
    }

    // TODO 6 (bagian KoleksiBuku): uji tambahan.

    public function testSemuaMengembalikanLarikBerindeksUrut(): void
    {
        $this->koleksi->simpan(new Buku('1', 'A', 'X', 1000));
        $this->koleksi->simpan(new Buku('2', 'B', 'Y', 2000));
        $this->assertSame([0, 1], array_keys($this->koleksi->semua()));
    }

    public function testIsbnSamaMenimpaItemLama(): void
    {
        $this->koleksi->simpan(new Buku('1', 'Lama', 'X', 1000));
        $this->koleksi->simpan(new Buku('1', 'Baru', 'X', 1000));
        $this->assertCount(1, $this->koleksi);
        $this->assertSame('Baru', $this->koleksi->cariByIsbn('1')?->judul);
    }

    public function testFilterTidakMeninggalkanIndeksBerlubang(): void
    {
        // array_filter tanpa array_values menghasilkan indeks [1], bukan [0].
        $this->koleksi->simpan(new Buku('1', 'A', 'X', 5000));
        $this->koleksi->simpan(new Buku('2', 'B', 'X', 20000));
        $hasil = $this->koleksi->filter(fn (Buku $b): bool => $b->harga > 10000);
        $this->assertSame([0], array_keys($hasil->semua()));
    }

    public function testKelompokkanBerdasarkanPenulis(): void
    {
        $this->koleksi->simpan(new Buku('1', 'A', 'Adi', 1000));
        $this->koleksi->simpan(new Buku('2', 'B', 'Budi', 1000));
        $this->koleksi->simpan(new Buku('3', 'C', 'Adi', 1000));
        $kelompok = $this->koleksi->kelompokkan(fn (Buku $b): string => $b->penulis);
        $this->assertSame(['Adi', 'Budi'], array_keys($kelompok));
        $this->assertCount(2, $kelompok['Adi']);
    }

    public function testKoleksiDapatDiiterasiDenganForeach(): void
    {
        $this->koleksi->simpan(new Buku('1', 'A', 'X', 1000));
        $this->koleksi->simpan(new Buku('2', 'B', 'X', 1000));
        $judul = [];
        foreach ($this->koleksi as $buku) {
            $judul[] = $buku->judul;
        }
        $this->assertSame(['A', 'B'], $judul);
    }
}