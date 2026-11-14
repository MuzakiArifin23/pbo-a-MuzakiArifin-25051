package id.ac.univpancasila.perpustakaan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Sesi 11 — unit test pertama Anda.
 *
 * Letakkan berkas ini di src/test/java/id/ac/univpancasila/perpustakaan/
 * Jalankan dengan: mvn test
 *
 * Konvensi Maven: hanya berkas di src/test/java yang bernama *Test
 * yang akan dijalankan sebagai pengujian.
 */
class RepositoryTest {

    /** Objek sederhana untuk diuji. */
    record Buku(String isbn, String judul) {}

    private Repository<Buku> repo;

    @BeforeEach
    void siapkan() {
        // Dijalankan ulang sebelum SETIAP pengujian, sehingga tiap uji
        // berangkat dari keadaan yang bersih dan tidak saling memengaruhi.
        repo = new Repository<>(Buku::isbn);
    }

    @Test
    @DisplayName("Item yang disimpan dapat ditemukan kembali")
    void simpanLaluTemukan() {
        repo.simpan(new Buku("978-1", "PBO"));

        // TODO 1: pastikan cariById("978-1") berisi nilai,
        //         dan judulnya "PBO".
        //         Petunjuk: assertTrue(...isPresent()) dan assertEquals(...)
        assertTrue(repo.cariById("978-1").isPresent());
        assertEquals("PBO", repo.cariById("978-1").get().judul());
    }

    @Test
    @DisplayName("Mencari id yang tidak ada mengembalikan Optional kosong")
    void cariYangTidakAda() {
        // TODO 2
        assertTrue(repo.cariById("tidak-ada").isEmpty());
    }

    @Test
    @DisplayName("Item yang dihapus tidak dapat ditemukan lagi")
    void hapusItem() {
        // TODO 3
        repo.simpan(new Buku("978-1", "PBO"));
        assertTrue(repo.hapus("978-1"));
        assertTrue(repo.cariById("978-1").isEmpty());
    }

    @Test
    @DisplayName("Repository baru berisi nol item")
    void repositoryKosong() {
        // TODO 4
        assertEquals(0, repo.jumlah());
        assertTrue(repo.cariSemua().isEmpty());
    }

    @Test
    @DisplayName("Koleksi yang dikembalikan cariSemua tidak dapat diubah dari luar")
    void koleksiTidakBocor() {
        repo.simpan(new Buku("978-1", "PBO"));

        // TODO 5: pastikan memanggil .add(...) pada hasil cariSemua()
        //         melemparkan UnsupportedOperationException.
        //         Petunjuk: assertThrows(UnsupportedOperationException.class, () -> ...)
        assertThrows(UnsupportedOperationException.class,
                () -> repo.cariSemua().add(new Buku("978-2", "Basis Data")));
    }

    @Test
    @DisplayName("Kunci yang sama menimpa item lama")
    void kunciSamaMenimpa() {
        repo.simpan(new Buku("978-1", "PBO"));
        repo.simpan(new Buku("978-1", "PBO Edisi 2"));
        assertEquals(1, repo.jumlah());
        assertEquals("PBO Edisi 2", repo.cariById("978-1").get().judul());
    }

    @Test
    @DisplayName("Menghapus id yang tidak ada mengembalikan false")
    void hapusYangTidakAda() {
        assertFalse(repo.hapus("tidak-ada"));
    }

    @Test
    @DisplayName("cariSemua mempertahankan urutan penyimpanan")
    void urutanTerjaga() {
        repo.simpan(new Buku("b", "Kedua"));
        repo.simpan(new Buku("a", "Pertama"));
        assertEquals(List.of("Kedua", "Pertama"),
                repo.cariSemua().stream().map(Buku::judul).toList());
    }

    // TODO 6 (sisa): tambahkan uji dari daftar skenario sesi 10 (exception pinjam()) dari daftar skenario
    //         yang Anda susun pada sesi 10, termasuk satu yang memastikan
    //         exception yang benar dilemparkan.
}
