# Laporan Praktikum 02: Kelas, Objek, dan Enkapsulasi

| | |
|---|---|
| **Nama** | Muzaki Arifin |
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
**Kode awal:** [Mahasiswa.java (sebelum)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/codingan%20sebelum%20dibenerin/pertemuan02/Mahasiswa.java)
**Kode akhir:** [Mahasiswa.java (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin25051/Pert02/java/Mahasiswa.java)

Kondisi awal: kelas belum punya perhitungan nilai dan menerima data apa saja tanpa pengecekan.

Yang saya kerjakan:

- `nim` dan `nama` dideklarasikan `private final`, serta tidak dibuat `setNim()`, sehingga identitas mahasiswa tidak bisa diubah dari luar.
- Bobot disimpan sebagai konstanta `BOBOT_TUGAS`, `BOBOT_UTS`, dan `BOBOT_UAS` supaya tidak ada angka bobot yang tertulis langsung di dalam method.
- Constructor menolak NIM `null` atau kosong (`isBlank()`) dengan `IllegalArgumentException`.
- Validasi rentang nilai dipusatkan di method privat `pastikanNilaiSah()`, dipanggil untuk tugas, UTS, dan UAS, sehingga tidak ada kode yang berulang.
- `nilaiAkhir()` menjumlahkan nilai yang sudah dikalikan bobot, lalu `hurufMutu()` memetakan hasilnya ke A–E dengan rangkaian `if`.

---

## 3. Implementasi PHP

**Kode awal:** [Mahasiswa.php (sebelum)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/codingan%20sebelum%20dibenerin/pertemuan02/main.php)
**Kode akhir:** [Mahasiswa.php (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert02/java/Main.java)

Logikanya sama dengan versi Java, hanya dengan sintaks PHP:

- Constructor property promotion dipakai, dengan `readonly` pada `$nim` dan `$nama` sebagai padanan `final` di Java.
- NIM dicek setelah `trim()`; bila kosong, dilempar `InvalidArgumentException`.
- `pastikanNilaiSah()` memeriksa batas 0–100 untuk tiap komponen nilai.
- `nilaiAkhir()` memakai konstanta bobot, dan `hurufMutu()` memakai ekspresi `match (true)`.

---

## 4. Bukti Eksekusi

### Java

Sebelum perbaikan:

<img width="556" height="146" alt="sebelum java pert2" src="https://github.com/user-attachments/assets/8444cafb-a151-4341-909a-4bfe6e158f55" />


Sesudah perbaikan (`java Main`):


<img width="556" height="146" alt="sesudah java pert2" src="https://github.com/user-attachments/assets/cc5e0e35-542e-4186-9af8-dc5daa5ed341" />


### PHP

Sebelum perbaikan:

<img width="450" height="153" alt="sebelum php pert2" src="https://github.com/user-attachments/assets/d75e41ee-487b-422f-b12e-20d9b4c291b8" />


Sesudah perbaikan (`php main.php`):


<img width="450" height="153" alt="sesudah php pert2" src="https://github.com/user-attachments/assets/a1612e88-2fb7-40fa-8584-c2de23dbd0f4" />





=== Objek menolak data yang melanggar aturan ===
  Ditolak: Nilai tugas harus di rentang 0-100, diberikan: 150
  Ditolak: NIM tidak boleh kosong
```

---

## 5. Kesimpulan

Dengan enkapsulasi, aturan data dijaga oleh kelas itu sendiri: objek `Mahasiswa` yang tidak sah (nilai 150 atau NIM kosong) tidak akan pernah terbentuk. Setelah perbaikan, program Java maupun PHP menampilkan nilai akhir dan huruf mutu dengan benar, serta menolak kedua data tidak valid pada program uji.
