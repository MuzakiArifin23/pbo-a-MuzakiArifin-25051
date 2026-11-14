package id.ac.univpancasila.perpustakaan;

/** Model buku untuk LibraryService. Record = tak-berubah (immutable). */
public record Buku(String isbn, String judul, String penulis, boolean tersedia) {
}
