**Nama:** Muzaki Arifin<br>
**NPM:** 4525210051<br>
**Kelas:** PBO A 2026/2027<br>
**Dosen Pengampu:** Adi Wahyu Pribadi, S.Si., M.Kom

Praktikum Pertemuan 11: Collections, Generics, dan unit test pertama. Yang dikerjakan hanya berkas di `src/starter/`.

## Berkas yang dikerjakan

| Berkas | Isi |
|---|---|
| `src/starter/java/Repository.java` | TODO 1-4: `simpan`, `cariById` (Optional), `cariSemua` (`List.copyOf`), `hapus` |
| `src/starter/java/RepositoryTest.java` | TODO 1-5 + 3 uji tambahan = 8 uji |
| `src/starter/java/Buku.java`, `BukuTidakDitemukanException.java`, `LibraryService.java` | Model, exception, dan service (`pinjam`, 3 laporan fungsional) |
| `src/starter/java/LibraryServiceTest.java` | TODO 6: uji exception `pinjam()` + laporan = 6 uji |
| `src/starter/php/KoleksiBuku.php` | TODO 1-6: `simpan`, `cariByIsbn`, `filter`, `map`, `jumlahkan`, `kelompokkan` |
| `src/starter/php/Buku.php` | Kelas `Buku` (isbn, judul, penulis, harga) |
| `src/starter/php/KoleksiBukuTest.php` | TODO 1-5 + 5 uji tambahan = 10 uji |
| `src/starter/php/BukuTidakDitemukanException.php`, `PerpustakaanService.php`, `PerpustakaanServiceTest.php` | Exception, service `pinjam()`, dan 3 uji (TODO 6) |

## Kode inti

```java
public void simpan(T item)            { data.put(pengambilKunci.apply(item), item); }
public Optional<T> cariById(String id){ return Optional.ofNullable(data.get(id)); }
public List<T> cariSemua()            { return List.copyOf(data.values()); }
public boolean hapus(String id)       { return data.remove(id) != null; }
```

```php
public function simpan(Buku $b): void { $this->items[$b->isbn] = $b; }
public function cariByIsbn(string $isbn): ?Buku { return $this->items[$isbn] ?? null; }
public function filter(callable $predikat): self
{
    return new self(array_values(array_filter($this->items, $predikat)));   // array DULU
}
public function map(callable $f): array
{
    return array_map($f, array_values($this->items));                       // callable DULU
}
```

## Cara menjalankan

Java: salin isi `src/starter/pom-tambahan.xml` ke `pom.xml`, letakkan kelas utama di `src/main/java/id/ac/univpancasila/perpustakaan/` dan berkas `*Test.java` di `src/test/java/` dengan package yang sama.

```powershell
mvn test
```

PHP: tambahkan `autoload` / `autoload-dev` di `composer.json` (`Perpustakaan\Model\` dan `Perpustakaan\Exception\` ke `src/`, `Perpustakaan\Tests\` ke `tests/`), lalu:

```powershell
composer require --dev phpunit/phpunit
composer dump-autoload
./vendor/bin/phpunit tests
```

Hasil yang diharapkan: Java `Tests run: 14, Failures: 0, Errors: 0`; PHP `OK (13 tests)`.

## Screenshot

Tampilan VS Code di Windows (tema Dark+).

**Repository.java**

![Kode Repository.java](screenshots/01-repository-java.png)

**RepositoryTest.java**

![Kode RepositoryTest.java](screenshots/02-repositorytest-java.png)

**KoleksiBuku.php**

![Kode KoleksiBuku.php](screenshots/03-koleksibuku-php.png)

**KoleksiBukuTest.php**

![Kode KoleksiBukuTest.php](screenshots/04-koleksibukutest-php.png)

<!--
Tambahkan screenshot hasil uji dari komputer Anda sendiri (Win + Shift + S pada terminal VS Code), lalu hapus tanda komentar ini:

![Hasil mvn test](screenshots/05-hasil-mvn-test.png)
![Hasil PHPUnit](screenshots/06-hasil-phpunit.png)
-->
