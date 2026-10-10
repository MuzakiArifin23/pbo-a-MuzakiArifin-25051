# Laporan Praktikum 03: Constructor, Anggota Statis, dan Konstanta

| | |
|---|---|
| **Nama** | Joni |
| **NPM** | 4525210051 |
| **Kelas** | PBO A 2025/2026 |
| **Dosen Pengampu** | Adi Wahyu Pribadi, S.Si., M.Kom |

---

## 1. Ringkasan Soal

Kelas `RekeningBank` dibuat untuk menyimpan nomor rekening, nama pemilik, dan saldo. Tiga aturan (invariant) yang harus selalu terjaga:

1. Saldo tidak pernah negatif.
2. Nomor rekening tidak berubah setelah objek dibuat.
3. Setoran dan penarikan selalu bernilai positif.

Materi yang dilatih pada praktikum ini: constructor yang saling mendelegasikan, atribut dan method `static`, serta konstanta bernama untuk menggantikan angka ajaib.

---

## 2. Implementasi Java

**Kode akhir:** [RekeningBank.java](src/java/RekeningBank.java) · [Main.java](src/java/Main.java)

Perubahan yang saya lakukan pada kode awal:

- **Constructor ringkas** `RekeningBank(nomor, pemilik)` sekarang memanggil `this(nomor, pemilik, 0)`, sehingga validasi hanya ada di satu tempat (constructor lengkap) dan tidak disalin.
- **Validasi constructor lengkap:** nomor `null` atau kosong ditolak, dan saldo awal negatif ditolak. Pesan error untuk saldo negatif juga saya perbaiki karena sebelumnya salah menyebut "nomor rekening".
- **Penghitung `jumlahRekening`** dinaikkan hanya di constructor lengkap. Kalau dinaikkan di kedua constructor, rekening yang dibuat lewat constructor ringkas terhitung dua kali, sehingga jumlahnya jadi 4 padahal seharusnya 3 (inilah yang dimaksud keterangan "bukan 4" di `Main.java`).
- **Konstanta** `bunga_tahunan`, `administrasi`, dan `batas_penarikan_sekali` dipakai di dalam method, jadi tidak ada angka literal di badan method.
- `setor()` dan `tarik()` menolak jumlah ≤ 0, `tarik()` juga menolak jumlah yang melebihi saldo atau batas sekali transaksi, dan `potongBiayaAdmin()` memakai `Math.max(0, ...)` supaya saldo tidak negatif.
- `JumlahRekening()` dan `bungaSetahun()` berupa method `static` karena keduanya tidak membaca keadaan objek tertentu.

---

## 3. Implementasi PHP

**Kode akhir:** [RekeningBank.php](src/php/RekeningBank.php) · [main.php](src/php/main.php)

PHP tidak punya constructor overloading, jadi padanannya dibuat dengan dua cara:

- **Default parameter** `float $saldoAwal = 0` menggantikan constructor ringkas.
- **Named constructor** `rekeningPelajar()` membuat rekening dengan saldo awal nol memakai `new static()`, bukan `new self()`, agar subclass menghasilkan objek dari kelasnya sendiri (late static binding).

Bagian lainnya sama dengan Java: konstanta `BUNGA_TAHUNAN`, `BIAYA_ADMIN`, dan `BATAS_PENARIKAN_SEKALI`, penghitung `private static int $jumlahRekening`, validasi nomor dan saldo awal, serta `InvalidArgumentException` untuk setiap pelanggaran aturan. Saldo dijaga tidak negatif dengan `max(0, ...)`.

---

## 4. Bukti Eksekusi

### Java

```text
Jumlah rekening di awal: 0
Rekening[111] Ani            Rp1.000.000,00
Rekening[222] Budi           Rp0,00
Rekening[333] Citra          Rp250.000,00
Jumlah rekening sekarang: 3(seharusnya 3, bukan 4)

=== Operasi ===
Setelah setor 500.000  -> Rekening[111] Ani            Rp1.500.000,00
  Ditolak: saldo tidak mencukupi
Budi setelah potong admin: Rekening[222] Budi           Rp0,00   (saldo tidak boleh negatif)
Bunga setahun dari saldo Ani: Rp37.500,00
```

### PHP

```text
Jumlah rekening di awal: 0
Rekening[111] Ani            Rp1.000.000,00
Rekening[222] Budi           Rp0,00
Rekening[333] Citra          Rp0,00
Jumlah rekening sekarang: 3 (seharusnya 3)

=== Operasi ===
Setelah setor 500.000  -> Rekening[111] Ani            Rp1.500.000,00
  Ditolak: Saldo tidak mencukupi
Budi setelah potong admin: Rekening[222] Budi           Rp0,00   (saldo tidak boleh negatif)
Bunga setahun dari saldo Ani: Rp37.500,00
```

---

## 5. Kesimpulan

Constructor yang berdelegasi membuat validasi dan penghitung cukup ditulis sekali, sehingga jumlah rekening tetap akurat berapa pun cara objek dibuat. Konstanta bernama membuat aturan bisnis mudah dibaca dan diubah di satu tempat, sedangkan method `static` cocok untuk hal yang tidak bergantung pada satu objek. Kedua versi, Java dan PHP, menghasilkan perilaku yang sama dan menolak setiap data yang melanggar aturan.
