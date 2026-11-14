<?php
declare(strict_types=1);

namespace Perpustakaan\Model;

final class Buku
{
    public function __construct(
        public readonly string $isbn,
        public readonly string $judul,
        public readonly string $penulis,
        public readonly int $harga,
    ) {
    }
}
