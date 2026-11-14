package id.ac.univpancasila.perpustakaan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Sesi 11 — TODO 6: skenario kegagalan sesi 10 yang dijalankan sungguhan.
 * Letakkan di src/test/java/id/ac/univpancasila/perpustakaan/
 */
class LibraryServiceTest {

    private LibraryService service;

    @BeforeEach
    void siapkan() {
        service = new LibraryService();
        service.tambah(new Buku("978-1", "PBO", "Adi", true));
        service.tambah(new Buku("978-2", "Basis Data", "Budi", true));
        service.tambah(new Buku("978-3", "Jaringan", "Adi", false));
    }

    @Test
    @DisplayName("pinjam() ISBN tidak terdaftar melempar BukuTidakDitemukanException")
    void pinjamIsbnTidakTerdaftar() {
        BukuTidakDitemukanException e = assertThrows(BukuTidakDitemukanException.class,
                () -> service.pinjam("000-0"));
        assertTrue(e.getMessage().contains("000-0"));
    }

    @Test
    @DisplayName("pinjam() buku tersedia mengubah statusnya menjadi tidak tersedia")
    void pinjamBerhasil() {
        Buku hasil = service.pinjam("978-1");
        assertFalse(hasil.tersedia());
        assertFalse(service.cari("978-1").tersedia());
    }

    @Test
    @DisplayName("pinjam() buku yang sedang dipinjam melempar IllegalStateException")
    void pinjamDuaKali() {
        assertThrows(IllegalStateException.class, () -> service.pinjam("978-3"));
    }

    @Test
    @DisplayName("Laporan 1: hanya buku tersedia")
    void laporanTersedia() {
        assertEquals(List.of("PBO", "Basis Data"),
                service.daftarTersedia().stream().map(Buku::judul).toList());
    }

    @Test
    @DisplayName("Laporan 2: judul digabung dengan pemisah")
    void laporanJudul() {
        assertEquals("PBO, Basis Data, Jaringan", service.daftarJudul(", "));
    }

    @Test
    @DisplayName("Laporan 3: jumlah buku per penulis")
    void laporanPerPenulis() {
        var hasil = service.jumlahPerPenulis();
        assertEquals(2L, hasil.get("Adi"));
        assertEquals(1L, hasil.get("Budi"));
    }
}
