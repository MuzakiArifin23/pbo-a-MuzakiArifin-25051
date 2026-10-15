package service;

import model.Buku;
import java.util.ArrayList;
import java.util.List;

/**
 * Kerangka lapisan layanan.
 * Pada sesi 7 cukup struktur dan tanda tangan method; isinya boleh minimal.
 * Lapisan ini akan diperdalam pada sesi 10 (exception) dan 12 (SOLID).
 *
 * TODO: sesuaikan seluruh isi berkas ini dengan class diagram Anda sendiri.
 *       Kelas dan method di sini hanya contoh awal.
 */
public class PerpustakaanService {

    // TODO 1: tentukan bagaimana layanan ini menyimpan koleksi dan anggota.
    //         Agregasi atau komposisi? Tuliskan alasannya di analisis.md,
    //         lalu wujudkan keputusan itu di sini.
    
    private final List<Buku> koleksi = new ArrayList<>();

    public void tambahKoleksi(Buku buku) {
        if (buku !=null && cariKoleksi(buku.getNomorInduk()) == null) {
            daftarkoleksi.add(buku); // TODO 2
        }
    }

    public Buku cariKoleksi(String nomorInduk) {
        for (Buku buku : koleksi) {
            if (buku.getNomorInduk().equals(nomorInduk)) {
                return b; // TODO 3
            }
        }
        return null;
    }

    public void pinjam(String nomorAnggota, String nomorInduk) {
        Buku buku = cariKoleksi(nomorInduk);
        if (buku == null && buku.tersedia()) {
            buku.kurangiStok(); // TODO 4
        }
    }

    public void kembalikan(String nomorAnggota, String nomorInduk) {
        Buku buku = cariKoleksi(nomorInduk);
        if (buku != null) {
            buku.tambahStok(); // TODO 5
        }
    }
}