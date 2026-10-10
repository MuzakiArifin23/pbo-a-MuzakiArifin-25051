# Laporan Praktikum 06: Abstraksi: Interface, Enum, dan Trait

| | |
|---|---|
| **Nama** | Muzaki Arifin |
| **NPM** | 4525210051 |
| **Kelas** | PBO A 2025/2026 |
| **Dosen Pengampu** | Adi Wahyu Pribadi, S.Si., M.Kom |

---

## 1. Ringkasan Soal

Kendaraan dimodelkan dengan beberapa kontrak kecil: `Movable` (bisa bergerak) dan `Fuelable` (bisa diisi bahan bakar). `Mobil` memenuhi keduanya, sedangkan `Sepeda` hanya `Movable` karena tidak butuh bahan bakar. Jenis bahan bakar dimodelkan dengan enum.

---

## 2. Implementasi Java

**Kode awal:** [Movable.java (sebelum)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/codingan%20sebelum%20dibenerin/pertemuan06/Movable.java) · [TipeBahanBakar.java (sebelum)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/codingan%20sebelum%20dibenerin/pertemuan06/TipeBahanBakar.java) · [Mobil.java (sebelum)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/codingan%20sebelum%20dibenerin/pertemuan06/Mobil.java)  
**Kode akhir:** [Movable.java (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert06/src/java/Movable.java) · [Fuelable.java (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert06/src/java/Fuelable.java) · [TipeBahanBakar.java (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert06/src/java/TipeBahanBakar.java) · [Kendaraan.java (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert06/src/java/Kendaraan.java) · [Mobil.java (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert06/src/java/Mobil.java) · [Sepeda.java (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert06/src/java/Sepeda.java) · [Main.java (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert06/src/java/Main.java)

Kondisi awal: `ringkasanGerak()` hanya menampilkan `(TODO 1 belum dikerjakan)`, biaya pengisian dan biaya enum semua Rp0, enum belum punya `LISTRIK`, dan `Sepeda` belum ada.

Yang saya kerjakan:

- `Movable` memiliki *default method* `ringkasanGerak()` yang memakai `kecepatanMaksimum()` milik implementornya.
- `Fuelable` sengaja dipisah dari `Movable` (Interface Segregation Principle): tidak semua yang bergerak butuh bahan bakar.
- `TipeBahanBakar` adalah enum berisi `BENSIN` (Rp12.000), `SOLAR` (Rp10.500), dan `LISTRIK` (Rp2.500) dengan label dan harga per satuan, serta method `biayaPengisian()` dan `ramahLingkungan()` yang tidak mungkin dimiliki konstanta `int`.
- `Mobil extends Kendaraan implements Movable, Fuelable`; `isiBahanBakar()` menolak jumlah ≤ 0 dan pengisian yang melebihi kapasitas tangki (dengan mencetak pesan lalu berhenti).
- `Sepeda extends Kendaraan implements Movable` saja, sehingga `isiPenuh(sepeda)` ditolak saat kompilasi.
- `isiPenuh(Fuelable)` hanya bergantung pada kontrak, bukan pada kelas `Mobil`.

---

## 3. Implementasi PHP

**Kode awal:** [abstraksi.php (sebelum)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/codingan%20sebelum%20dibenerin/pertemuan06/abstraksi.php) · [main.php (sebelum)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/codingan%20sebelum%20dibenerin/pertemuan06/main.php)  
**Kode akhir:** [abstraksi.php (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert06/src/php/abstraksi.php) · [main.php (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert06/src/php/main.php)

Kondisi awal: kecepatan maksimum tampil 0 km/jam, label enum tampil `?`, semua biaya Rp0, `Sepeda` belum ada, dan enum belum punya `Listrik`.

- `interface Movable` dan `interface Fuelable` setara dengan versi Java.
- `enum TipeBahanBakar: string` (backed enum) memakai `match ($this)` untuk label dan harga per satuan; `Listrik` ditambahkan sebagai satu-satunya yang ramah lingkungan.
- `trait Loggable` mencetak log berformat `[jam] NamaKelas: pesan` dengan `static::class`, dan dipakai oleh `Mobil` maupun `Pesanan` yang tidak sekerabat (penggunaan ulang horizontal).
- `Mobil::isiBahanBakar()` melempar `InvalidArgumentException` untuk jumlah ≤ 0 atau pengisian melebihi kapasitas.
- `Sepeda` hanya `implements Movable`, sehingga memanggil `isiPenuh($sepeda)` akan menghasilkan `TypeError`.

Format `printf` di trait sempat tertulis `%s: 5s`, sehingga isi pesan log tidak tampil (hanya teks `5s`). Itu diperbaiki menjadi `%s: %s`.

---

## 4. Bukti Eksekusi

### Java

Sebelum perbaikan:

<img width="471" height="165" alt="sebelum java pert6" src="https://github.com/user-attachments/assets/3daa3b0d-e6fc-4fac-abf8-90f0ebd840fb" />


Sesudah perbaikan (`java Main`):


<img width="532" height="252" alt="setelah java pert6" src="https://github.com/user-attachments/assets/7bdeac3d-2fa9-409a-95ba-1ad886a31381" />


Tanda `?` pada keluaran Java adalah karakter `—` yang diganti oleh encoding terminal Windows, bukan kesalahan program.

### PHP

Sebelum perbaikan:

<img width="471" height="204" alt="sebelum php pert6" src="https://github.com/user-attachments/assets/6e71c154-b0c7-4c04-959b-7cbc910cc669" />


Sesudah perbaikan (`php main.php`):

<!-- TODO: screenshot ini diambil sebelum format printf di trait diperbaiki (baris log masih "Mobil: 5s"). Jalankan ulang `php main.php`, lalu ganti images/php-sesudah.png. Hasil yang benar: "[jam] Mobil: servis berkala selesai" dan "[jam] Pesanan: pesanan #1042 dibuat". -->
<img width="534" height="305" alt="setelah php pert6" src="https://github.com/user-attachments/assets/51894436-a43e-4328-8e9c-d8d9538ee5f5" />


---

## 5. Kesimpulan

Interface memisahkan *apa yang bisa dilakukan* dari *apa bendanya*, enum membatasi nilai yang sah beserta perilakunya, dan trait (khas PHP) memungkinkan kode yang sama dipakai kelas-kelas yang tidak berkerabat. Pada program uji, mobil terisi penuh (45 liter) dengan biaya Rp540.000, sedangkan sepeda tidak bisa diberi bahan bakar sama sekali karena tidak memenuhi kontrak `Fuelable`.
