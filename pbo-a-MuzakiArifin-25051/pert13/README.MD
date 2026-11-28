# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
| :--- | :--- |
| **Nama** | *Prevansyah Sjafar* |
| **NPM** | *4525210057* |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | 13 — FRAMEWORK LARAVEL |
| **Tanggal** | *Kamis, 26 November 2026* |
| **Dosen Pengampu** | *Adi Wahyu Pribadi, S.Si., M.Kom* |

---

## 1. Panduan Instalasi

**Kebutuhan:** PHP >= 8.2, Composer, Node.js & NPM, serta database (MySQL/SQLite).

1. Clone atau ekstrak project, lalu masuk ke folder project:
   ```bash
   cd example--app
   ```
2. Install dependensi PHP:
   ```bash
   composer install
   ```
3. Salin file environment:
   ```bash
   cp .env.example .env
   ```
4. Generate application key:
   ```bash
   php artisan key:generate
   ```
5. Atur koneksi database pada file `.env` (`DB_CONNECTION`, `DB_DATABASE`, `DB_USERNAME`, `DB_PASSWORD`), lalu buat database-nya jika memakai MySQL.
6. Jalankan migrasi sekaligus seeder untuk membuat tabel dan data awal:
   ```bash
   php artisan migrate --seed
   ```
7. (Opsional) Install dependensi frontend:
   ```bash
   npm install
   ```

---

## 2. Panduan Running Laravel

1. Jalankan server pengembangan:
   ```bash
   php artisan serve
   ```
2. Aplikasi dapat diakses pada `http://127.0.0.1:8000`, sehingga base URL API adalah:
   ```
   http://127.0.0.1:8000/api
   ```
3. Uji endpoint menggunakan aplikasi API client seperti Thunder Client, Postman, atau Insomnia.
4. (Opsional) Jalankan pengujian otomatis:
   ```bash
   php artisan test
   ```

---

## 3. Screenshot Route (`routes/api.php`)

| Resource | Method | Endpoint | Controller |
| :--- | :--- | :--- | :--- |
| Buku | GET | `/api/buku/get` | `BukuController@index` |
| Buku | POST | `/api/buku/create` | `BukuController@store` |
| Buku | PATCH | `/api/buku/update/{id}` | `BukuController@update` |
| Buku | DELETE | `/api/buku/delete/{id}` | `BukuController@destroy` |
| Anggota | GET | `/api/anggota/get` | `AnggotaController@index` |
| Anggota | POST | `/api/anggota/create` | `AnggotaController@store` |
| Anggota | PATCH | `/api/anggota/update/{id}` | `AnggotaController@update` |
| Anggota | DELETE | `/api/anggota/delete/{id}` | `AnggotaController@destroy` |
| Pinjam Buku | GET | `/api/pinjam-buku/get` | `PeminjamanBukuController@index` |
| Pinjam Buku | POST | `/api/pinjam-buku/create` | `PeminjamanBukuController@store` |
| Pinjam Buku | PATCH | `/api/pinjam-buku/update/{id}` | `PeminjamanBukuController@update` |
| Pinjam Buku | DELETE | `/api/pinjam-buku/delete/{id}` | `PeminjamanBukuController@destroy` |

![api.php](../img/after/pert13/api.php.png)

---

## 4. Percobaan Hasil API per Route

### 4.1. Route Buku

**GET `/api/buku/get`**

![GET Buku](../img/after/pert13/getbuku.png)

**POST `/api/buku/create`**

![POST Buku](../img/after/pert13/postbuku.png)

**PATCH `/api/buku/update/{id}`**

![PATCH Buku](../img/after/pert13/patchbuku.png)

**DELETE `/api/buku/delete/{id}`**

![DELETE Buku](../img/after/pert13/deletebuku.png)

### 4.2. Route Anggota

**GET `/api/anggota/get`**

![GET Anggota](../img/after/pert13/getanggota.png)

**POST `/api/anggota/create`**

![POST Anggota](../img/after/pert13/postanggota.png)

**PATCH `/api/anggota/update/{id}`**

![PATCH Anggota](../img/after/pert13/patchanggota.png)

**DELETE `/api/anggota/delete/{id}`**

![DELETE Anggota](../img/after/pert13/deleteanggota.png)

### 4.3. Route Pinjam Buku

**GET `/api/pinjam-buku/get`**

![GET Pinjam Buku](../img/after/pert13/getpinjambuku.png)

**POST `/api/pinjam-buku/create`**

![POST Pinjam Buku](../img/after/pert13/postpinjambuku.png)

**PATCH `/api/pinjam-buku/update/{id}`**

![PATCH Pinjam Buku](../img/after/pert13/patchpinjambuku.png)

**DELETE `/api/pinjam-buku/delete/{id}`**

![DELETE Pinjam Buku](../img/after/pert13/deletepinjambuku.png)