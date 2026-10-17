# Praktikum Desain Web A: Pertemuan 5

**Wireframe dan Struktur Informasi Website** (HTML5, CSS3, Flexbox, CSS Grid, Media Query)

> Repositori ini berisi tiga halaman wireframe yang saya kerjakan pada Pertemuan 5: dua contoh dari modul (halaman sekolah) dan satu tugas mandiri (toko tanaman hias online).

| | |
|---|---|
| Nama | **[Muzaki Arifin]** |
| NIM | [4525210051] |
| Program Studi | [Teknik Informatika] |
| Kampus | [Universitas Pancasila] |
| Mata Kuliah | Praktikum Desain Web A |
| Topik | Wireframe, struktur informasi, dan layout responsif |

---

## Daftar Isi

1. [Sekilas Proyek](#sekilas-proyek)
2. [Isi Repositori](#isi-repositori)
3. [Konsep yang Dipakai](#konsep-yang-dipakai)
4. [Contoh 1: Wireframe Low Fidelity](#contoh-1-wireframe-low-fidelity)
5. [Contoh 2: Wireframe Portal Sekolah](#contoh-2-wireframe-portal-sekolah)
6. [Tugas: Wireframe Toko Online Rimbun](#tugas-wireframe-toko-online-rimbun)
7. [Perbandingan Ketiga Halaman](#perbandingan-ketiga-halaman)
8. [Yang Saya Pelajari](#yang-saya-pelajari)
9. [Menjalankan Proyek](#menjalankan-proyek)

---

## Sekilas Proyek

Wireframe adalah gambaran kasar tata letak sebuah halaman: di mana header, menu, konten, dan footer diletakkan, sebelum urusan warna dan gambar dipikirkan. Di pertemuan ini saya mencoba tiga tingkat kerapian yang berbeda:

- **Contoh 1** adalah wireframe paling sederhana. Layout memakai **Flexbox** dan warna dikelola lewat variabel CSS.
- **Contoh 2** adalah wireframe portal sekolah yang lebih lengkap. Layout memakai **CSS Grid** dengan dua *breakpoint*.
- **Tugas** adalah wireframe toko online tanaman hias bernama **Rimbun**. Setiap area diberi label bernomor dan ada bagian anotasi yang menjelaskan fungsi tiap area.

Semua halaman memisahkan struktur (`index.html`) dari tampilan (`css/style.css`).

---

## Isi Repositori

```
Pertemuan 5/
├── contoh 1/
│   ├── index.html
│   ├── css/style.css
│   ├── img/              logo, spmb, seragam, kepsek
│   └── dokumentasi/      Web.png, mobile1.png, mobile2.png
├── contoh 2/
│   ├── index.html
│   ├── css/style.css
│   ├── img/              logo, spmb, seragam, kepsek
│   └── dokumentasi/      web.png, mobile1.png sampai mobile5.png
├── Tugas/
│   ├── index.html
│   ├── css/style.css
│   ├── images/           RumahcHIjau, Monstera Deliciosa, Lidah Mertua,
│   │                     Kaktus Mini, Pot Keramik Putih
│   └── dokumentasi/      versi WEB.png, mobile1.png sampai mobile4.png
├── Materi/               modul dan petunjuk praktikum (PDF)
└── README.md
```

---

## Konsep yang Dipakai

### Elemen struktur informasi

| Elemen | Peran di halaman |
|---|---|
| Header | Menampilkan identitas situs (logo, nama) |
| Navbar | Menu utama untuk berpindah bagian |
| Sidebar | Menu atau filter tambahan di samping konten |
| Hero | Pesan pembuka dan tombol ajakan (*call to action*) |
| Card | Kotak informasi singkat yang bisa diulang |
| Footer | Kontak dan keterangan penutup |

### Flexbox atau Grid?

Aturan yang saya pegang setelah mengerjakan ketiganya:

- **Flexbox** dipakai saat elemen tersusun searah, misalnya baris menu, header, atau deretan card sederhana.
- **Grid** dipakai saat butuh kolom dan baris sekaligus, misalnya kerangka halaman (sidebar + konten) atau galeri card.

### Media query

Aturan CSS yang hanya aktif pada lebar layar tertentu. Pola dasarnya:

```css
@media (max-width: 768px) {
  /* aturan untuk layar yang lebih sempit */
}
```

Wajib ada tag viewport di setiap `<head>` supaya layar ponsel tidak menampilkan halaman dengan skala desktop:

```html
<meta name="viewport" content="width=device-width, initial-scale=1.0">
```

---

## Contoh 1: Wireframe Low Fidelity

Halaman informasi SMAN 5 Bekasi dengan sidebar dan tiga card.

**Susunan halaman**

```
header          logo sekolah
nav.navbar      Beranda | Tentang | Produk | Kontak
main.layout
 ├─ aside       sidebar (Menu 1 sampai Menu 4)
 └─ section     hero (gambar, judul, deskripsi, tombol)
                card-container (SPMB, Seragam, Kepala Sekolah)
footer          telepon, WhatsApp, email
```

**Cara kerjanya**

Warna dikumpulkan sebagai variabel di `:root`, jadi mengganti tema cukup mengubah satu blok:

```css
:root {
  --biru-tua: #1f3a5f;
  --latar:    #f3f5f8;
  --aksen:    #e0a526;
  /* ... total 8 variabel warna */
}
```

Sidebar punya lebar tetap, sedangkan konten mengambil sisa ruang. Card dibuat sama lebar dengan `flex: 1`:

```css
.layout         { display: flex; gap: 20px; }
.sidebar        { width: 220px; flex-shrink: 0; }
.content        { flex: 1; min-width: 0; }
.card-container { display: flex; gap: 20px; }
.card           { flex: 1; }
```

**Responsif (1 breakpoint, 768px)**: sidebar naik ke atas konten, card menumpuk jadi satu kolom, dan menu navbar boleh turun baris.

| Desktop | Mobile | Mobile |
|---|---|---|
| <img src="contoh%201/dokumentasi/Web.png" width="420" alt="Contoh 1 desktop"> | <img src="contoh%201/dokumentasi/mobile1.png" width="180" alt="Contoh 1 mobile bagian atas"> | <img src="contoh%201/dokumentasi/mobile2.png" width="180" alt="Contoh 1 mobile bagian bawah"> |

---

## Contoh 2: Wireframe Portal Sekolah

Versi yang lebih lengkap: ada profil pengguna, menu siswa, dan bagian layanan.

**Susunan halaman**

```
header          logo + "Portal Sekolah" + label profil "Siswa"
nav             Beranda | Informasi | Jadwal | Layanan | Kontak  (anchor link)
main.main-layout
 ├─ aside       Menu Sekolah (6 tautan)
 └─ section
     ├─ #beranda    hero dua kolom (teks + logo)
     ├─ #informasi  3 card: SPMB, Seragam, Kepala Sekolah
     └─ #layanan    4 kotak layanan siswa
footer#kontak   nama portal + email
```

**Cara kerjanya**

`.container` dipakai berulang di header, navbar, konten, dan footer agar lebar isinya seragam:

```css
.container { width: 90%; max-width: 1200px; margin: auto; padding: 20px; }
```

Seluruh pembagian kolom memakai Grid, dengan satuan `fr` untuk membagi ruang secara proporsional:

```css
.main-layout  { display: grid; grid-template-columns: 230px 1fr; gap: 25px; }
.hero         { display: grid; grid-template-columns: 2fr 1fr; }
.card-grid    { display: grid; grid-template-columns: repeat(3, 1fr); }
.layanan-grid { display: grid; grid-template-columns: repeat(4, 1fr); }
```

**Responsif (2 breakpoint)**

| Lebar layar | Perubahan |
|---|---|
| 900px ke bawah (tablet) | Layout jadi 1 kolom, sidebar berubah menjadi deretan tombol, card dan layanan jadi 2 kolom |
| 600px ke bawah (ponsel) | Header, navbar, dan footer ditumpuk vertikal, hero/card/layanan jadi 1 kolom |

| Desktop |
|---|
| <img src="contoh%202/dokumentasi/web.png" width="560" alt="Contoh 2 desktop"> |

| Mobile 1 | Mobile 2 | Mobile 3 | Mobile 4 | Mobile 5 |
|---|---|---|---|---|
| <img src="contoh%202/dokumentasi/mobile1.png" width="130" alt="Contoh 2 mobile 1"> | <img src="contoh%202/dokumentasi/mobile2.png" width="130" alt="Contoh 2 mobile 2"> | <img src="contoh%202/dokumentasi/mobile3.png" width="130" alt="Contoh 2 mobile 3"> | <img src="contoh%202/dokumentasi/mobile4.png" width="130" alt="Contoh 2 mobile 4"> | <img src="contoh%202/dokumentasi/mobile5.png" width="130" alt="Contoh 2 mobile 5"> |

---

## Tugas: Wireframe Toko Online Rimbun

Untuk tugas, saya merancang wireframe toko tanaman hias online. Berbeda dari dua contoh sebelumnya, setiap area diberi **kotak garis putus-putus dan label bernomor** (1 sampai 6), lalu di bagian bawah halaman ada bagian **Anotasi fungsi area** yang menjelaskan alasan tiap area ada di sana. Jadi wireframe ini bisa dibaca sebagai dokumen rancangan, bukan hanya tampilan.

**Susunan halaman**

| No | Area | Isi |
|---|---|---|
| 1 | Header | Logo "Rimbun", kolom pencarian, tautan Keranjang (0) |
| 2 | Navbar | Beranda, Tentang, Produk, Kontak (menu aktif ditandai) |
| 3 | Sidebar | Kategori (Indoor, Outdoor, Kaktus & Sukulen, Pot) dan filter rentang harga (checkbox) |
| 4 | Hero | Judul "Hijaukan rumahmu mulai hari ini", deskripsi singkat, tombol *Belanja sekarang*, foto |
| 5 | Card produk | Monstera Deliciosa, Lidah Mertua, Kaktus Mini, Pot Keramik Putih (foto, nama, harga, tombol) |
| 6 | Footer | Alamat, bantuan (cara pesan, pengiriman, pengembalian), media sosial, hak cipta |

Di antara area 5 dan 6 ada bagian **Tentang Rimbun** dan **Anotasi fungsi area**.

**Cara kerjanya**

Palet warna hijau-krem dengan aksen koral disimpan sebagai variabel CSS:

```css
:root {
  --forest: #1f4d3a;
  --leaf:   #3e8e63;
  --mint:   #dff0e6;
  --cream:  #fff9f2;
  --coral:  #e8604c;
  /* ... */
}
```

Label bernomor dibuat murni dengan CSS. Angkanya diambil dari atribut `data-n` pada HTML lewat `attr()`, jadi tidak perlu elemen tambahan:

```css
.note::before { content: attr(data-n); /* lingkaran bernomor */ }
```

Grid dipakai untuk kerangka halaman, sedangkan Flexbox untuk header dan navbar. Galeri produk memakai `auto-fill` sehingga jumlah kolom menyesuaikan lebar layar secara otomatis:

```css
.layout { display: grid; grid-template-columns: 240px 1fr; }
.grid   { display: grid;
          grid-template-columns: repeat(auto-fill, minmax(190px, 1fr)); }
```

Placeholder foto digambar dengan dua `linear-gradient` bersilang di atas latar mint, sehingga kotak gambar kosong tetap terbaca sebagai wireframe.

**Aksesibilitas kecil yang saya tambahkan**: `aria-label` pada pencarian, keranjang, dan navbar; `alt` yang deskriptif pada semua gambar; serta `:focus-visible` agar navigasi keyboard terlihat jelas.

**Responsif (3 breakpoint)**

| Lebar layar | Perubahan |
|---|---|
| 900px ke bawah | Sidebar mengecil jadi 200px, hero menjadi 1 kolom |
| 640px ke bawah | Layout jadi 1 kolom (sidebar pindah ke atas konten), header ditumpuk, produk 2 kolom, footer 1 kolom |
| 380px ke bawah | Produk menjadi 1 kolom |

| Desktop |
|---|
| <img src="Tugas/dokumentasi/versi%20WEB.png" width="560" alt="Tugas desktop"> |

| Mobile 1 | Mobile 2 | Mobile 3 | Mobile 4 |
|---|---|---|---|
| <img src="Tugas/dokumentasi/mobile1.png" width="150" alt="Tugas mobile 1"> | <img src="Tugas/dokumentasi/mobile2.png" width="150" alt="Tugas mobile 2"> | <img src="Tugas/dokumentasi/mobile3.png" width="150" alt="Tugas mobile 3"> | <img src="Tugas/dokumentasi/mobile4.png" width="150" alt="Tugas mobile 4"> |

> Karena masih berupa wireframe, kolom pencarian, tombol keranjang, dan filter harga belum berfungsi (tanpa JavaScript). Semuanya hanya menandai posisi elemen.

---

## Perbandingan Ketiga Halaman

| | Contoh 1 | Contoh 2 | Tugas |
|---|---|---|---|
| Tema | SMAN 5 Bekasi | Portal sekolah | Toko tanaman hias |
| Layout utama | Flexbox | Grid | Grid (kerangka), Flexbox (header, navbar) |
| Menu navbar | 4 | 5 (anchor link) | 4 |
| Sidebar | 4 menu teks | 6 tautan menu siswa | Kategori dan filter harga |
| Card | 3, tanpa tautan | 3, dengan tautan | 4, dengan harga dan tombol |
| Bagian tambahan | Tidak ada | Layanan Siswa (4 kotak) | Tentang, Anotasi, footer 3 kolom |
| Warna | Variabel CSS | Kode warna langsung | Variabel CSS |
| Breakpoint | 1 (768px) | 2 (900px, 600px) | 3 (900px, 640px, 380px) |
| Tingkat detail | Paling sederhana | Menengah | Paling lengkap, ada anotasi |

---

## Yang Saya Pelajari

1. Menentukan posisi elemen lebih dulu membuat proses styling jauh lebih terarah. Saya tahu area mana yang perlu dibuat kolom, mana yang cukup satu baris.
2. Flexbox dan Grid tidak saling menggantikan. Pada tugas saya memakai keduanya di satu halaman sesuai kebutuhan masing-masing area.
3. `repeat(auto-fill, minmax(...))` sangat membantu: jumlah kolom card menyesuaikan layar tanpa harus menulis banyak media query.
4. Variabel CSS (`:root` dan `var()`) membuat palet warna mudah diubah dari satu tempat, berbeda dengan Contoh 2 yang kodenya tersebar.
5. Urutan elemen di HTML menentukan tampilan di ponsel. Sidebar saya letakkan sebelum konten agar otomatis naik ke atas saat layout menjadi satu kolom.
6. Memisahkan HTML dan CSS membuat perubahan tampilan tidak mengganggu struktur.

---

## Menjalankan Proyek

```bash
git clone [isi URL repositori]
cd [nama folder repositori]
```

Lalu buka salah satu file berikut di browser (klik dua kali, atau pakai ekstensi *Live Server* di VS Code):

- `contoh 1/index.html`
- `contoh 2/index.html`
- `Tugas/index.html`

Untuk mencoba tampilan ponsel, tekan `F12` lalu aktifkan *Toggle Device Toolbar* (`Ctrl + Shift + M`).

> Folder `css/` dan gambar harus satu level dengan `index.html`. Perhatikan juga huruf besar/kecil pada nama file, karena beberapa server membedakannya.

---

<sub>Praktikum Desain Web A · Pertemuan 5 · [Isi nama] ([Isi NIM])</sub>