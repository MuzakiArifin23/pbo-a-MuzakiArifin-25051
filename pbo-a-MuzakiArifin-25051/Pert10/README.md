# Sistem Perpustakaan — Exception Handling dan Pemrograman Defensif

**Pertemuan 10** · Teknik Informatika, Universitas Pancasila
**Nama:** Muzaki Arifin · **NPM:** 4525210051

Aplikasi perpustakaan sederhana dalam dua versi (**Java** dan **PHP**) yang memakai exception domain untuk menandai alasan gagalnya peminjaman buku, serta guard clause untuk menggantikan `if` bersarang.

## Struktur Proyek

```
Pert10_Perpustakaan/
├── README.md
├── skenario-uji.md              # keputusan desain, skenario uji, jawaban pertanyaan pemandu
├── docs/screenshots/            # screenshot hasil run (Windows)
├── java/
│   ├── pom.xml
│   └── src/main/java/id/ac/univpancasila/perpustakaan/
│       ├── Main.java
│       ├── exception/           # PerpustakaanException + 4 exception turunan
│       └── service/             # LibraryService, LibraryServiceKotor, PerpustakaanService
└── php/
    ├── composer.json
    ├── Main.php
    └── src/
        ├── Exception/
        └── Service/             # LibraryService, LibraryServiceKotor
```

## Hierarki Exception

| Class | Membawa data | Dilempar saat |
|-------|--------------|---------------|
| `PerpustakaanException` (abstract, unchecked) | — | Induk semua exception domain |
| `AnggotaTidakDitemukanException` | `idAnggota` | Anggota belum terdaftar |
| `BukuTidakDitemukanException` | `isbn` | ISBN tidak ada di katalog |
| `BukuSedangDipinjamException` | `isbn`, `judul` | Stok eksemplar habis |
| `BatasPinjamTerlampauiException` | `idAnggota`, `batas` | Anggota sudah mencapai 3 buku |

Urutan pemeriksaan di `pinjam()`: anggota → buku → stok → batas pinjam. Alasan memilih *unchecked* ada di [`skenario-uji.md`](skenario-uji.md).

---

## Menjalankan di Windows

### Prasyarat

| Kebutuhan | Versi | Cek |
|-----------|-------|-----|
| JDK | 17 atau lebih baru | `java -version` dan `javac -version` |
| PHP | 8.3 atau lebih baru | `php -v` |
| Composer (hanya untuk PHP) | 2.x | `composer -V` |

Buka **PowerShell** atau **Command Prompt**, lalu cek versi:

![Versi Java dan PHP](screenshots/01-versi-java-php.png)

### A. Versi Java

Dari folder `Pert10_Perpustakaan`:

**1. Kompilasi**

```powershell
cd java
mkdir out
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
```

Jika memakai Command Prompt (`cmd`), gunakan:

```bat
cd java
mkdir out
dir /s /b src\*.java > sources.txt
javac -d out @sources.txt
```

![Kompilasi Java](screenshots/02-compile-java.png)

**2. Jalankan**

```powershell
java -cp out id.ac.univpancasila.perpustakaan.Main
```

Menu yang tampil:

```
=== SISTEM PERPUSTAKAAN ===
1. Tambah Buku
2. Tambah Anggota
3. Pinjam Buku
0. Keluar
Pilih menu (0-3):
```

![Menu utama Java](screenshots/03-menu-java.png)

Contoh alur uji: tambah buku `111` "Laskar Pelangi" (1 eksemplar) dan `222` "Bumi Manusia" (5 eksemplar), daftarkan anggota `A-001` dan `A-002`, lalu pilih menu 3 dan coba kombinasi pada tabel skenario di bawah. Ketik `exit` untuk kembali ke menu, lalu `0` untuk keluar.

Contoh output kegagalan:

```
[Error Domain] Buku Laskar Pelangi dengan ISBN 111 sedang dipinjam
 -> Judul Buku: Laskar Pelangi (ISBN: 111)

[Error Domain] Buku dengan ISBN 999 tidak ditemukan
ISBN bermasalah: 999

[Error Domain] Anggota dengan ID X-999 tidak ditemukan
Id Anggota bermasalah: X-999

[Error Domain] Anggota dengan ID A-001 telah mencapai batas maksimum peminjaman
 -> Batas Maksimum: 3 buku
```

![Peminjaman Java](screenshots/04-pinjam-java.png)

**3. Laporan**

Saat keluar, program menulis `laporan_buku.txt` di folder tempat perintah `java` dijalankan (`java\`):

```powershell
type laporan_buku.txt
```

![Laporan Java](screenshots/05-laporan-java.png)

> Alternatif dengan Maven: `mvn compile`, lalu `java -cp target\classes id.ac.univpancasila.perpustakaan.Main`.

### B. Versi PHP

Dari folder `Pert10_Perpustakaan`:

```powershell
cd php
composer install
php Main.php
```

Output yang diharapkan:

```
Anggota A-001 meminjam ISBN '111' ...
[BERHASIL]
Anggota A-002 meminjam ISBN '111' ...
[GAGAL] Buku "Laskar Pelangi" (ISBN 111) sedang dipinjam.
Anggota A-002 meminjam ISBN '999' ...
[GAGAL] Buku dengan ISBN 999 tidak ditemukan.
Anggota X-999 meminjam ISBN '222' ...
[GAGAL] Anggota dengan ID X-999 tidak ditemukan.
Anggota A-001 meminjam ISBN '222' ...
[BERHASIL]
Anggota A-001 meminjam ISBN '222' ...
[BERHASIL]
Anggota A-001 meminjam ISBN '222' ...
[GAGAL] Anggota A-001 telah mencapai batas peminjaman (3 buku).

--- Ekspor Laporan Buku ---
>> BERHASIL: Laporan buku beserta statusnya disimpan ke berkas 'laporan_buku.txt'.
[INFO] File 'laporan_buku.txt' telah ditutup dengan aman melalui blok finally.
```

![Output PHP](screenshots/06-run-php.png)

Laporan tersimpan di `php\laporan_buku.txt`:

![Laporan PHP](screenshots/07-laporan-php.png)

---

## Skenario Uji

Data awal: buku `111` (1 eksemplar), `222` (5 eksemplar); anggota `A-001`, `A-002`; batas pinjam 3.

| No | Anggota | ISBN | Hasil |
|----|---------|------|-------|
| 1 | A-001 | 111 | Berhasil |
| 2 | A-002 | 111 | `BukuSedangDipinjamException` |
| 3 | A-002 | 999 | `BukuTidakDitemukanException` |
| 4 | X-999 | 222 | `AnggotaTidakDitemukanException` |
| 5 | A-001 | 222 | Berhasil |
| 6 | A-001 | 222 | Berhasil |
| 7 | A-001 | 222 | `BatasPinjamTerlampauiException` |

Isi `laporan_buku.txt` setelah skenario di atas:

```
==========================================================================
                     LAPORAN DAFTAR BUKU PERPUSTAKAAN
==========================================================================
ISBN         | JUDUL BUKU                          | STOK     | STATUS
--------------------------------------------------------------------------
111          | Laskar Pelangi                      | 0        | Tidak Tersedia
222          | Bumi Manusia                        | 3        | Tersedia
==========================================================================
```

## Catatan Refactoring

`LibraryServiceKotor` (Java dan PHP) sengaja dipertahankan sebagai bahan perbandingan: `pinjam()` bersarang empat tingkat dan hanya mengembalikan `false` tanpa alasan. `LibraryService` adalah versi guard clause yang melempar exception sesuai penyebab kegagalan.

## Pemecahan Masalah (Windows)

| Masalah | Solusi |
|---------|--------|
| `'javac' is not recognized` | Pasang JDK (bukan hanya JRE), lalu tambahkan folder `bin` JDK ke `PATH` dan buka terminal baru |
| `'php' is not recognized` | Tambahkan folder PHP ke `PATH`, atau jalankan dengan path lengkap `C:\php\php.exe` |
| Error `const int` di PHP | Versi PHP di bawah 8.3; perbarui ke 8.3+ |
| `vendor/autoload.php` tidak ditemukan | Jalankan `composer install` di folder `php` |
| `composer install` gagal karena Carbon | Hapus baris `nesbot/carbon` di `composer.json` (Carbon tidak dipakai kode) |
| Karakter `—` atau `→` rusak di terminal | Jalankan `chcp 65001` sebelum `java` atau `php` |