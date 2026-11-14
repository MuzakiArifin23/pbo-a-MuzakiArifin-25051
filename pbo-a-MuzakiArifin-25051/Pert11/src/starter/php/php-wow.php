<?php

$pengeluaran = [15000, 10000, 5000, 20000];

$hasilFilter = array_filter($pengeluaran, function($uang) {
    return $uang > 10000;
});

$hasilMap = array_map(function($uang) {
    return "Rp" . $uang;
}, $pengeluaran);

$total = array_reduce($pengeluaran, function($jumlah, $uang) {
    return $jumlah + $uang;
}, 0);

echo "Semua pengeluaran:<br>";
print_r($pengeluaran);

echo "<br><br>Pengeluaran di atas Rp10.000:<br>";
print_r($hasilFilter);

echo "<br><br>Pengeluaran dalam bentuk Rupiah:<br>";
print_r($hasilMap);

echo "<br><br>Total pengeluaran: Rp" . $total;

?>
