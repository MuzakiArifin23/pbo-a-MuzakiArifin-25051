package id.ac.univpancasila.perpustakaan;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Sesi 11 — LibraryService memakai Repository<Buku> (Map di dalamnya),
 * jadi pencarian ISBN tidak lagi perulangan linear.
 */
public class LibraryService {

    private final Repository<Buku> repo = new Repository<>(Buku::isbn);

    public void tambah(Buku buku) {
        repo.simpan(buku);
    }

    /** Tidak mengembalikan null: ISBN tak terdaftar -> exception yang jelas. */
    public Buku cari(String isbn) {
        return repo.cariById(isbn)
                .orElseThrow(() -> new BukuTidakDitemukanException(isbn));
    }

    public Buku pinjam(String isbn) {
        Buku buku = cari(isbn);
        if (!buku.tersedia()) {
            throw new IllegalStateException("Buku " + isbn + " sedang dipinjam");
        }
        Buku dipinjam = new Buku(buku.isbn(), buku.judul(), buku.penulis(), false);
        repo.simpan(dipinjam);          // kunci sama -> menimpa
        return dipinjam;
    }

    /** Laporan 1: buku yang tersedia (filter). */
    public List<Buku> daftarTersedia() {
        return repo.cariSemua().stream().filter(Buku::tersedia).toList();
    }

    /** Laporan 2: judul saja, digabung dengan pemisah (map + join). */
    public String daftarJudul(String pemisah) {
        return repo.cariSemua().stream().map(Buku::judul).collect(Collectors.joining(pemisah));
    }

    /** Laporan 3: jumlah buku per penulis (groupingBy). */
    public Map<String, Long> jumlahPerPenulis() {
        return repo.cariSemua().stream()
                .collect(Collectors.groupingBy(Buku::penulis, Collectors.counting()));
    }
}
