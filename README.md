# Praktikum Pemrograman Berorientasi Objek (PBO)

Kumpulan tugas praktikum PBO yang dikerjakan dalam dua bahasa, **Java** dan **PHP**, supaya konsep yang sama bisa dibandingkan langsung.

| | |
|---|---|
| **Nama** | Muzaki Arifin |
| **NPM** | 4525210051 |
| **Kelas** | PBO A 2025/2026 |
| **Dosen Pengampu** | Adi Wahyu Pribadi, S.Si., M.Kom |

---

## Daftar Pertemuan

| Pertemuan | Topik | Studi kasus | Laporan |
|:---:|---|---|---|
| 01 | Git & GitHub | Instalasi Git, SSH key, push ke GitHub | [pert01](pert01/README.md) |
| 02 | Kelas, Objek, dan Enkapsulasi | `Mahasiswa`: validasi nilai, nilai akhir, huruf mutu | [Pert02](Pert02/README.md) |
| 03 | Constructor, Anggota Statis, dan Konstanta | `RekeningBank`: constructor berdelegasi, penghitung statis | [Pert03](Pert03/README.md) |
| 04 | Pewarisan (Inheritance) | `Pegawai`, `PegawaiTetap`, `PegawaiKontrak` | [Pert04](Pert04/README.md) |
| 05 | Polimorfisme | `BangunDatar` dan turunannya, hierarki `Notifikasi` | [Pert05](Pert05/README.md) |
| 06 | Abstraksi: Interface, Enum, dan Trait | `Movable`, `Fuelable`, `TipeBahanBakar`, `Loggable` | [Pert06](Pert06/README.md) |

Setiap laporan berisi ringkasan soal, penjelasan implementasi Java dan PHP, screenshot hasil eksekusi, dan kesimpulan.

---

## Struktur Repositori

```text
pbo-a-MuzakiArifin-25051/
├── pert01/            laporan Git & GitHub (Word) + README
├── Pert02/            java/  php/  images/  README.md
├── Pert03/            java/  php/  images/  README.md
├── Pert04/            src/ (Java)  src/php/  images/  README.md
├── Pert05/            src/ (Java)  src/php/  images/  README.md
└── Pert06/            src/java/  src/php/  images/  README.md
```

Folder `images/` berisi screenshot hasil eksekusi yang dipakai di README tiap pertemuan.

---

## Cara Menjalankan

**Kebutuhan:** JDK 17 atau lebih baru (memakai `record` dan `instanceof` dengan pola) dan PHP 8.3 atau lebih baru (memakai konstanta kelas bertipe dan enum).

**Java**, dari folder yang berisi file `.java`:

```bash
javac *.java
java Main
```

**PHP**, dari folder yang berisi `main.php`:

```bash
php main.php
```

Folder yang dijalankan per pertemuan:

| Pertemuan | Java | PHP |
|:---:|---|---|
| 02 | `Pert02/java` | `Pert02/php` |
| 03 | `Pert03/java` | `Pert03/php` |
| 04 | `Pert04/src` | `Pert04/src/php` |
| 05 | `Pert05/src` | `Pert05/src/php` |
| 06 | `Pert06/src/java` | `Pert06/src/php` |

Catatan khusus: di `Pert05/src`, `AntiPattern.java` dan `AntiPaternRefaktor.java` punya `main` sendiri (`java AntiPattern`, `java AntiPaternRefaktor`), dan di `Pert05/src/php` ada `notifikasi.php` yang dijalankan terpisah dengan `php notifikasi.php`.

---

## Konsep yang Dipelajari

1. **Enkapsulasi:** atribut dibuat `private`/`final`, dan aturan data (invariant) dijaga oleh kelas itu sendiri lewat validasi di constructor.
2. **Constructor dan anggota statis:** constructor saling mendelegasikan, konstanta bernama menggantikan angka ajaib, dan `static` dipakai untuk hal yang tidak bergantung pada satu objek.
3. **Pewarisan:** bagian yang sama ditulis sekali di kelas induk, lalu turunan menambah perilaku lewat `super` / `parent`.
4. **Polimorfisme:** kode memegang tipe induk, sehingga tipe baru bisa ditambahkan tanpa menyunting logika yang sudah ada.
5. **Abstraksi:** interface sebagai kontrak, enum untuk nilai terbatas yang punya perilaku, dan trait (khas PHP) untuk penggunaan ulang horizontal.