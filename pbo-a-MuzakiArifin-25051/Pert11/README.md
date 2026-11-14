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

**Repository.java**

![Kode Repository.java](<img width="1920" height="1080" alt="01-repository-java" src="https://github.com/user-attachments/assets/9ad800ae-4008-4442-9202-1dd9ab56718c" />
)

**RepositoryTest.java**

![Kode RepositoryTest.java](<img width="1920" height="1080" alt="02-repositorytest-java" src="https://github.com/user-attachments/assets/9291c32e-d0b2-4141-b401-470b8c83fa97" />
)

**KoleksiBuku.php**

![Kode KoleksiBuku.php](<img width="1920" height="1080" alt="03-koleksibuku-php" src="https://github.com/user-attachments/assets/785fb175-976c-4122-bc28-6151a86a2c07" />
)

**KoleksiBukuTest.php**

![Kode KoleksiBukuTest.php](<img width="1920" height="1080" alt="04-koleksibukutest-php" src="https://github.com/user-attachments/assets/e636b1df-05a5-49ab-88a2-8701667b3f1a" />)

<!--
Tambahkan screenshot hasil uji dari komputer Anda sendiri (Win + Shift + S pada terminal VS Code), lalu hapus tanda komentar ini:

![Hasil mvn test](screenshots/05-hasil-mvn-test.png)
![Hasil PHPUnit](screenshots/06-hasil-phpunit.png)
-->
