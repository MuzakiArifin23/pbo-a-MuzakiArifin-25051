# Laporan Praktikum 05: Polimorfisme

| | |
|---|---|
| **Nama** | Muzaki Arifin |
| **NPM** | 4525210051 |
| **Kelas** | PBO A 2025/2026 |
| **Dosen Pengampu** | Adi Wahyu Pribadi, S.Si., M.Kom |

---

## 1. Ringkasan Soal

Kelas induk `BangunDatar` menetapkan kontrak (`luas()` dan `keliling()`), lalu setiap bangun datar mengisi caranya sendiri. Program uji hanya memegang variabel bertipe induk, dan perulangannya tidak boleh diubah saat bangun datar baru ditambahkan.

---

## 2. Implementasi Java

**Kode awal:** [Lingkaran.java (sebelum)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/codingan%20sebelum%20dibenerin/pertemuan05/Lingkaran.java) · [Persegi.java (sebelum)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/codingan%20sebelum%20dibenerin/pertemuan05/Persegi.java) · [Main.java (sebelum)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/codingan%20sebelum%20dibenerin/pertemuan05/Main.java)  
**Kode akhir:** [BangunDatar.java (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert05/src/BangunDatar.java) · [Lingkaran.java (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert05/src/Lingkaran.java) · [Persegi.java (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert05/src/Persegi.java) · [Segitiga.java (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert05/src/Segitiga.java) · [Trapesium.java (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert05/src/Trapesium.java) · [Main.java (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert05/src/Main.java)

Kondisi awal: luas dan keliling Lingkaran maupun Persegi tampil 0,00 dan total luas 0,00, karena `luas()` dan `keliling()` belum diisi; Segitiga dan Trapesium belum ada.

Yang saya kerjakan:

- `Lingkaran` dan `Persegi` menolak ukuran ≤ 0 dengan `IllegalArgumentException`; `Lingkaran` memakai `Math.PI`, bukan angka 3,14.
- `Segitiga` (alas, tinggi, sisi miring) dan `Trapesium` (sisi atas, sisi bawah, tinggi, sisi kiri, sisi kanan) ditambahkan sebagai kelas baru, dan `Main` cukup menambah baris pada array tanpa mengubah perulangan.
- `toString()` di `BangunDatar` memanggil `luas()` dan `keliling()` milik turunan lewat dynamic dispatch, jadi satu `toString()` bisa dipakai oleh semua bentuk. `Segitiga` dan `Trapesium` meng-override `toString()` dengan format yang menampilkan ukuran sisinya, sehingga barisnya tampil berbeda dari Lingkaran dan Persegi.
- `instanceof Lingkaran l` dipakai untuk downcasting hanya pada bagian yang memang butuh `getJariJari()`.

**Anti-pattern vs polimorfik:** [AntiPattern.java](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert05/src/AntiPattern.java) menghitung luas dengan rangkaian `instanceof`, sehingga setiap bangun baru memaksa method `hitungLuas()` disunting. [AntiPaternRefaktor.java](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert05/src/AntiPaternRefaktor.java) memindahkan rumus ke masing-masing tipe lewat interface `Bangun`, jadi menambah bangun baru cukup membuat satu kelas baru.

---

## 3. Implementasi PHP

**Kode awal:** [BangunDatar.php (sebelum)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/codingan%20sebelum%20dibenerin/pertemuan05/BangunDatar.php) · [main.php (sebelum)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/codingan%20sebelum%20dibenerin/pertemuan05/main.php) · [notifikasi.php (sebelum)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/codingan%20sebelum%20dibenerin/pertemuan05/notifikasi.php)  
**Kode akhir:** [BangunDatar.php (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert05/src/php/BangunDatar.php) · [main.php (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert05/src/php/main.php) · [notifikasi.php (sesudah)](https://github.com/MuzakiArifin23/pbo-a-MuzakiArifin-25051/blob/main/pbo-a-MuzakiArifin-25051/Pert05/src/php/notifikasi.php)

Kondisi awal: sama seperti Java, luas dan keliling tampil 0,00 dan total luas 0,00.

- Hierarki `BangunDatar` → `Lingkaran`, `Persegi`, `Segitiga`, `Trapesium` ditulis dalam satu berkas, dengan `M_PI` untuk lingkaran.
- `Segitiga` memakai rumus Heron dan menolak sisi yang tidak membentuk segitiga (jumlah dua sisi harus lebih besar dari sisi ketiga).
- `Trapesium` menerima dua sisi sejajar, dua sisi miring, dan tinggi, serta menolak nilai ≤ 0. `main.php` hanya menguji Lingkaran, Persegi, dan Segitiga.
- **Latihan notifikasi:** kelas abstrak `Notifikasi` punya turunan `Email`, `SMS`, dan `WhatsApp`. Fungsi `kirimSemua()` cukup memanggil `kirim()` pada tiap objek tanpa `instanceof` atau `match` atas jenis notifikasi.

---

## 4. Bukti Eksekusi

### Java

Sebelum perbaikan:

<img width="508" height="200" alt="sebelum java pert5" src="https://github.com/user-attachments/assets/0a01396a-ba6c-4fa8-9639-48a863dcd501" />


Sesudah perbaikan (`java Main`):


<img width="755" height="239" alt="setelah java pert5" src="https://github.com/user-attachments/assets/135680c0-457e-4975-8efc-eaa9566f77ad" />


Perbandingan anti-pattern dan refaktor polimorfik (`java AntiPattern` dan `java AntiPaternRefaktor`):

```text
Total luas (cara anti-pattern): 184,94
Total luas (cara polimorfik): 184,94
```

### PHP

Sebelum perbaikan:

<img width="509" height="126" alt="sebelum php pert5" src="https://github.com/user-attachments/assets/95f43480-1502-4096-bf89-e3d6651180ec" />


Sesudah perbaikan (`php main.php`):


<img width="445" height="197" alt="setelah php pert5" src="https://github.com/user-attachments/assets/cf07bfa2-b0d9-4131-b7e4-e99047fc008a" />


Latihan notifikasi (`php notifikasi.php`):

<!-- TODO: blok ini ditulis dari membaca kode, bukan dari hasil menjalankan. Jalankan `php notifikasi.php`, ambil screenshot, lalu ganti blok teks ini dengan gambarnya. -->
```text
[Email] Mengirim email ke <ani@univpancasila.ac.id>:
"Buku yang Anda pesan sudah tersedia."

[SMS] SMS terkirim ke nomor 081234567890: Buku yang Anda pesan sudah tersedia.

[WhatsApp] WA chat ke 081234567890 -> Buku yang Anda pesan sudah tersedia.
```

---

## 5. Kesimpulan

Dengan polimorfisme, kode yang memakai `BangunDatar` atau `Notifikasi` tidak perlu tahu kelas konkretnya, sehingga bentuk atau saluran baru bisa ditambahkan tanpa menyunting perulangan yang sudah ada. Total luas Java (224,94) berbeda dari PHP (184,94) karena `main.php` hanya menguji tiga bangun, sedangkan `Main.java` juga memuat Trapesium (luas 40,00); luas Lingkaran (153,94), Persegi (25,00), dan Segitiga (6,00) sama di kedua versi.
