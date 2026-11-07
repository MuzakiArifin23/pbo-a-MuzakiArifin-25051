# Skenario Uji — Pertemuan 10: Exception Handling dan Pemrograman Defensif

## 1. Keputusan desain: checked atau unchecked?

`PerpustakaanException` diturunkan dari **RuntimeException (unchecked)**.

Alasan:
- Kegagalannya (anggota tidak ditemukan, buku tidak ditemukan, stok habis, batas pinjam tercapai) menandakan **permintaan yang tidak sah**, bukan keadaan luar yang wajar seperti jaringan putus atau berkas hilang. Pemanggil tidak bisa "memulihkan" keadaan dengan mengulang pemanggilan yang sama.
- Dengan unchecked, setiap method tidak dipaksa menulis `throws` atau `try-catch`, sehingga kode tidak penuh penanganan yang hanya meneruskan exception.
- Penanganan cukup dilakukan di satu lapisan yang tepat (di sini `PerpustakaanService.pinjamBuku` dan `Main`).
- Induk bersama membuat pemanggil bisa menangkap seluruh kegagalan domain dengan satu `catch (PerpustakaanException e)` tanpa ikut menelan kesalahan pemrograman lain.
- PHP tidak mengenal checked exception, jadi versi PHP otomatis setara unchecked. Karena itu anotasi `@throws` pada PHPDoc dipakai sebagai pengganti peringatan kompilator.

## 2. Skenario uji (sama dengan array `$percobaan` di `Main.php`)

Data awal: buku `111` "Laskar Pelangi" (1 eksemplar), buku `222` "Bumi Manusia" (5 eksemplar), anggota `A-001` dan `A-002`. Batas pinjam = 3.

| No | Anggota | ISBN | Kondisi | Hasil yang diharapkan |
|----|---------|------|---------|-----------------------|
| 1 | A-001 | 111 | Normal | BERHASIL (stok 111 menjadi 0) |
| 2 | A-002 | 111 | Stok habis | GAGAL — `BukuSedangDipinjamException`: Buku "Laskar Pelangi" (ISBN 111) sedang dipinjam |
| 3 | A-002 | 999 | ISBN tidak ada | GAGAL — `BukuTidakDitemukanException`: Buku dengan ISBN 999 tidak ditemukan |
| 4 | X-999 | 222 | Anggota belum terdaftar | GAGAL — `AnggotaTidakDitemukanException`: Anggota dengan ID X-999 tidak ditemukan |
| 5 | A-001 | 222 | Normal (pinjaman ke-2) | BERHASIL |
| 6 | A-001 | 222 | Normal (pinjaman ke-3) | BERHASIL |
| 7 | A-001 | 222 | Batas tercapai | GAGAL — `BatasPinjamTerlampauiException`: batas 3 buku |

Urutan pemeriksaan di `pinjam()`: anggota, lalu buku ada, lalu stok, lalu batas pinjam.

Laporan akhir (`laporan_buku.txt`): ISBN 111 stok 0 "Tidak Tersedia"; ISBN 222 stok 3 "Tersedia".

Uji tambahan untuk versi Java (menu interaktif):
- Tambah anggota dengan ID kosong: ditolak.
- Tambah buku dengan eksemplar `abc` atau `0`: ditolak.
- Pinjam buku dengan ID kosong: peringatan, lalu kembali meminta ID (tidak lanjut ke ISBN).
- Ketik `exit` pada ID anggota: kembali ke menu utama.

## 3. Jawaban pertanyaan pemandu (LibraryServiceKotor)

1. **Logika utama** (`stok.merge` dan `pinjaman.merge`) berada di dalam **4 tingkat** `if`, jauh menjorok ke kanan, sehingga sulit ditemukan.
2. Jika satu syarat gagal, method hanya mengembalikan `false`. Pemanggil **tidak tahu alasannya**: anggota tidak ada, buku tidak ada, stok habis, atau batas tercapai, semuanya terlihat sama.
3. Menambah aturan baru berarti menambah tingkat `if` dan satu cabang `else` lagi, sehingga rawan salah kurung dan salah cabang. Pada versi guard clause cukup menambah satu blok `if (...) throw ...;` baru, dan alur lain tidak tersentuh.
