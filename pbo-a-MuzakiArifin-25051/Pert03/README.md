# Laporan Praktikum 03: Constructor, Anggota Statis, dan Konstanta

| | |
|---|---|
| **Nama** | Muzaki Arifin |
| **NPM** | 4525210051 |
| **Kelas** | PBO A 2025/2026 |
| **Dosen Pengampu** | Adi Wahyu Pribadi, S.Si., M.Kom |

---

## 1. Ringkasan Soal

Kelas `RekeningBank` menyimpan nomor rekening, nama pemilik, dan saldo. Tiga aturan (invariant) yang harus selalu terjaga:

1. Saldo tidak pernah negatif.
2. Nomor rekening tidak berubah setelah objek dibuat.
3. Setoran dan penarikan selalu bernilai positif.

Materi yang dilatih: constructor yang saling mendelegasikan, atribut dan method `static`, serta konstanta bernama untuk menggantikan angka ajaib.

---

## 2. Implementasi Java

**Kode awal:** [RekeningBank.java (sebelum)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/codingan%20sebelum%20dibenerin/pertemuan03/RekeningBank.java) · [Main.java (sebelum)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/codingan%20sebelum%20dibenerin/pertemuan03/Main.java)  
**Kode akhir:** [RekeningBank.java (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert03/java/RekeningBank.java) · [Main.java (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert03/java/Main.java)

Kondisi awal: penghitung rekening menampilkan `-1` (seharusnya 3), `setor()` tidak menambah saldo, penarikan melebihi batas tidak ditolak, dan `bungaSetahun()` selalu menghasilkan Rp0,00.

Yang saya kerjakan:

- **Constructor ringkas** `RekeningBank(nomor, pemilik)` memanggil `this(nomor, pemilik, 0)`, sehingga validasi hanya ada di constructor lengkap.
- **Constructor lengkap** menolak nomor `null`/kosong (`isBlank()`) dan saldo awal negatif dengan `IllegalArgumentException`. `nomor` dan `pemilik` dibuat `private final`.
- **`jumlahRekening`** (`private static`, nilai awal 0) dinaikkan hanya di constructor lengkap. Kalau dinaikkan di kedua constructor, rekening yang dibuat lewat constructor ringkas terhitung dua kali (jumlahnya jadi 4, bukan 3).
- **Konstanta** `bunga_tahunan` (2,5%), `administrasi` (Rp5.000), dan `batas_penarikan_sekali` (Rp5.000.000) dipakai di dalam method, sehingga tidak ada angka literal di badan method.
- `setor()` menolak jumlah ≤ 0. `tarik()` menolak jumlah ≤ 0, jumlah yang melebihi saldo, dan jumlah yang melebihi batas sekali transaksi (dicek dalam urutan itu).
- `potongBiayaAdmin()` memakai `Math.max(0, saldo - administrasi)` supaya saldo tidak pernah negatif.
- `JumlahRekening()` dan `bungaSetahun()` berupa method `static` karena tidak membaca keadaan objek tertentu.

---

## 3. Implementasi PHP

**Kode awal:** [RekeningBank.php (sebelum)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/codingan%20sebelum%20dibenerin/pertemuan03/RekeningBank.php) · [main.php (sebelum)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/codingan%20sebelum%20dibenerin/pertemuan03/main.php)  
**Kode akhir:** [RekeningBank.php (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert03/php/RekeningBank.php) · [main.php (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert03/php/main.php)

Kondisi awal: program berhenti dengan *Fatal error* (`TODO 5 belum dikerjakan`) saat memanggil `RekeningBank::rekeningPelajar()`, dan penghitung rekening juga menampilkan `-1`.

PHP tidak punya constructor overloading, jadi padanannya:

- **Default parameter** `float $saldoAwal = 0` menggantikan constructor ringkas. `$nomor` dan `$pemilik` dibuat `private readonly` lewat constructor property promotion.
- **Named constructor** `rekeningPelajar()` membuat rekening bersaldo nol memakai `new static()`, bukan `new self()`, agar subclass menghasilkan objek dari kelasnya sendiri (late static binding).
- Konstanta kelas `BUNGA_TAHUNAN`, `BIAYA_ADMIN`, dan `BATAS_PENARIKAN_SEKALI`, penghitung `private static int $jumlahRekening`, validasi, dan `InvalidArgumentException` setara dengan versi Java.
- Saldo ditampilkan dengan `number_format($saldo, 2, ',', '.')` lewat `__toString()`.

---

## 4. Bukti Eksekusi

### Java

Sebelum perbaikan:

<img width="759" height="202" alt="sebelum java pert3" src="https://github.com/user-attachments/assets/ceb17fc5-191e-4fb7-b3d2-d2078fd0e226" />


Sesudah perbaikan (`java Main`):


<img width="761" height="203" alt="sesudah java pert3" src="https://github.com/user-attachments/assets/b4853027-753e-4cea-8420-b02127af26dd" />


### PHP

Sebelum perbaikan:

<img width="1371" height="216" alt="sebelum php pert3" src="https://github.com/user-attachments/assets/650a7d72-9f07-4ff0-9e33-0b5c4307eb7d" />


Sesudah perbaikan (`php main.php`):


<img width="756" height="203" alt="sesudah php pert3" src="https://github.com/user-attachments/assets/06af67d6-42cb-4eed-a5ee-5a09c6a394ca" />


---

## 5. Kesimpulan

Constructor yang berdelegasi membuat validasi dan penghitung cukup ditulis sekali, sehingga jumlah rekening tetap 3 berapa pun cara objek dibuat. Konstanta bernama membuat aturan bisnis mudah dibaca dan diubah di satu tempat, sedangkan method `static` cocok untuk hal yang tidak bergantung pada satu objek.

Setelah perbaikan, saldo Ani menjadi Rp1.500.000 setelah setoran, penarikan Rp9.999.999 ditolak (karena saldo tidak mencukupi), saldo Budi tetap Rp0 setelah potong biaya admin, dan bunga setahun dari saldo Ani Rp37.500. Perbedaan kecil antara kedua bahasa ada pada Citra: Rp250.000 di Java (constructor tiga argumen) dan Rp0 di PHP (dibuat lewat `rekeningPelajar()`).
