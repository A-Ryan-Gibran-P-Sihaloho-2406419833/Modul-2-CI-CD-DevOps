# Refleksi 1

## Prinsip Clean Code dan Secure Coding yang Diterapkan

### 1. Penamaan yang bermakna (Meaningful Names)
Saya berusaha menerapkan prinsip **Meaningful Names** dari *Clean Code*. Saya memsastikan bahwa nama variabel yang saya gunakan deskriptif dan mengungkapkan maksud pengguna secara jelas. Saya berusaha ikuti standar penamaan Java (CamelCase) 
- **Contoh:** saya memakai nama yang spesifik seperti `productName` dan `productQuantity` daripada `pname` atau `pquant`. kode menjadi lebih mudah dibaca tanpa perlu komentar .

### 2. Secure Coding & Identitas Objek 
Dalam mengimplementasikan fitur **Edit**, saya rasa identifikasi unik untuk setiap produk di dalam repository `ArrayList` penting sekali. Mengandalkan input manual dari user untuk ID berisiko dan rentan error.
- **Perbaikan:** Saya mengubah strategi penanganan `productId`. Saya tidak menggunakan String sederhana yang diinput user, tapi mengimplementasikan pembuatan **UUID** secara otomatis.
- **Manfaat:** Langkah ini menjamin bahwa setiap produk yang dibuat memiliki pengenal yang unik sebagai primary key dan aman dari duplikasi (collision). 

## Akan di improve nanti

### 1. Validasi Input & Penanganan Error
Saya menemukan potensi masalah terkait **Validasi Data Input**.
- **Masalah:** Kalau membiarkan kolom "Quantity" kosong saat pembuatan atau pengeditan produk, web akan *error* dan return halaman kesalahan default ("Whitelabel Error Page"). Hal ini terjadi karena aplikasi mencoba mengonversi string kosong menjadi integer.
- **Rencana:** Saat ini dibiarkan dulu seperti ini untuk tutorial sekarang. Akan dieprbaiki di modul-modul mendatang dengan menerapkan validasi yang tepat di sisi klien (*client-side*) maupun sisi server (*server-side*), serta membuat halaman error message.

# Refleksi 2

### 1. Unit Testing & Code Coverage
Setelah menulis unit test, perasaan saya mual dan menganngap matkul ini sepertinya akan paling berat dibanding matkul yang saya ambil di semester ini .

Menurut saya, tidak ada angka pasti berapa banyak unit test yang harus dibuat dalam satu class. Yang penting adalah unit test tersebut cukup untuk bisa memverifikasi semua logika krusial, termasuk positive case, negative case, dan edge cases.

Terkait Code Coverage, yang saya tahu adalah metrik ini berguna untuk mengetahui seberapa banyak baris kode yang dieksekusi oleh test. Tapi, 100% Code Coverage tidak menjamin kode bebas dari bug.
- coverage 100% cuma berarti semua baris kode pernah dijalankan saat test.
- Itu tidak menjamin logikanya benar, atau apakah test sudah menangani input-input aneh yang mungkin bikin program crash. Jadi, kualitas test case seharusnya lebih penting daripada sekedar mengejar angka coverage.

### 2. Functional Test Cleanliness
jika saya membuat functional test suite baru untuk memverifikasi jumlah item dengan cara me-copy paste prosedur setup dan variabel instance dari class sebelumnya, spertinya itu akan menurunkan kualitas kode.

Masalah *Clean Code* yang terjadi adalah:
- duplikasi Kode (Code Duplication): Melanggar prinsip DRY (Don't Repeat Yourself). Mengulang-ulang kode setup (`baseUrl`, port configuration, dll) di banyak file itu tidak efisien.
- maaintainability Rendah: Kalo nanti ada perubahan konfigurasi (misal cara setup port berubah), saya harus ganti manual di semua file test satu per satu. capek.

**Saran Perbaikan:**
Sebaiknya buat satu Base Test Class yang berisi semua konfigurasi setup umum dan variabel instance yang sering dipakai. Nanti, class test baru (`CreateProductFunctionalTest`, `CountProductFunctionalTest`, dll) tinggal melakukan inheritance (extends) dari Base Class tersebut. Jadinya kode lebih bersih, rapi, dan gampang diurus.