package id.ac.univpancasila.perpustakaan;

/** Dilempar bila ISBN yang dicari/dipinjam tidak terdaftar (skenario sesi 10). */
public class BukuTidakDitemukanException extends RuntimeException {
    public BukuTidakDitemukanException(String isbn) {
        super("Buku dengan ISBN " + isbn + " tidak ditemukan");
    }
}
