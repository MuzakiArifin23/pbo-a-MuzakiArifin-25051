# Laporan Praktikum 02: Kelas, Objek, dan Enkapsulasi

| | |
|---|---|
| **Nama** | Joni |
| **NPM** | 4525210051 |
| **Kelas** | PBO A 2025/2026 |
| **Dosen Pengampu** | Adi Wahyu Pribadi, S.Si., M.Kom |

---

## 1. Ringkasan Soal

Sistem akademik menyimpan data mahasiswa berupa NIM, nama, serta tiga nilai (tugas, UTS, UAS). Aturan yang harus dijaga oleh kelas `Mahasiswa`:

1. NIM tidak boleh kosong dan tidak berubah setelah objek dibuat.
2. Setiap nilai harus berada pada rentang 0–100.
3. Nilai akhir = 30% tugas + 30% UTS + 40% UAS.
4. Huruf mutu: ≥ 80 = A, ≥ 70 = B, ≥ 60 = C, ≥ 50 = D, selain itu E.

---

## 2. Implementasi Java

**Kode awal:** [Mahasiswa.java (sebelum)](../../Materi-Pembelajaran&Codingan-sebelumdibenerin/codingansebelum/pertemuan02/java/Mahasiswa.java)
**Kode akhir:** [Mahasiswa.java (sesudah)](src/java/Mahasiswa.java)

Kondisi awal: kelas belum punya perhitungan nilai dan menerima data apa saja tanpa pengecekan.

Yang saya kerjakan:

- `nim` dan `nama` dideklarasikan `private final`, serta tidak dibuat `setNim()`, sehingga identitas mahasiswa tidak bisa diubah dari luar.
- Bobot disimpan sebagai konstanta `BOBOT_TUGAS`, `BOBOT_UTS`, dan `BOBOT_UAS` supaya tidak ada angka bobot yang tertulis langsung di dalam method.
- Constructor menolak NIM `null` atau kosong (`isBlank()`) dengan `IllegalArgumentException`.
- Validasi rentang nilai dipusatkan di method privat `pastikanNilaiSah()`, dipanggil untuk tugas, UTS, dan UAS, sehingga tidak ada kode yang berulang.
- `nilaiAkhir()` menjumlahkan nilai yang sudah dikalikan bobot, lalu `hurufMutu()` memetakan hasilnya ke A–E dengan rangkaian `if`.

---

## 3. Implementasi PHP

**Kode awal:** [Mahasiswa.php (sebelum)](../../Materi-Pembelajaran&Codingan-sebelumdibenerin/codingansebelum/pertemuan02/php/Mahasiswa.php)
**Kode akhir:** [Mahasiswa.php (sesudah)](src/php/Mahasiswa.php)

Logikanya sama dengan versi Java, hanya dengan sintaks PHP:

- Constructor property promotion dipakai, dengan `readonly` pada `$nim` dan `$nama` sebagai padanan `final` di Java.
- NIM dicek setelah `trim()`; bila kosong, dilempar `InvalidArgumentException`.
- `pastikanNilaiSah()` memeriksa batas 0–100 untuk tiap komponen nilai.
- `nilaiAkhir()` memakai konstanta bobot, dan `hurufMutu()` memakai ekspresi `match (true)`.

---

## 4. Bukti Eksekusi

### Java

Sebelum perbaikan:

![Java sebelum](../../ssan-sebelum/images-java/pertemuan02.png)

Sesudah perbaikan (`java Main`):

```text
=== Rekap Nilai ===
  2024001    Ani Lestari        akhir= 84.90  mutu=A
  2024002    Budi Santoso       akhir= 59.30  mutu=D
  2024003    Citra Wijaya       akhir= 92.00  mutu=A

=== Objek menolak data yang melanggar aturan ===
  Ditolak: Nilai tugas harus di rentang 0.0-100.0, diberikan: 150.0
  Ditolak: NIM tidak boleh kosong atau null
```

### PHP

Sebelum perbaikan:

![PHP sebelum](../../ssan-sebelum/images-php/pert-php-02.png)

Sesudah perbaikan (`php main.php`):

```text
=== Rekap Nilai ===
  2024001    Ani Lestari        akhir= 84.90  mutu=A
  2024002    Budi Santoso       akhir= 59.30  mutu=D
  2024003    Citra Wijaya       akhir= 92.00  mutu=A

=== Objek menolak data yang melanggar aturan ===
  Ditolak: Nilai tugas harus di rentang 0-100, diberikan: 150
  Ditolak: NIM tidak boleh kosong
```

---

## 5. Kesimpulan

Dengan enkapsulasi, aturan data dijaga oleh kelas itu sendiri: objek `Mahasiswa` yang tidak sah (nilai 150 atau NIM kosong) tidak akan pernah terbentuk. Setelah perbaikan, program Java maupun PHP menampilkan nilai akhir dan huruf mutu dengan benar, serta menolak kedua data tidak valid pada program uji.
