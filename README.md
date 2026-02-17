# Refleksi Tutorial & Latihan Module 2 CI/CD

## 1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

Selama pengerjaan tutorial dan latihan ini,saya memperbaiki beberapa error kualitas kode, yaitu:

* **Konsistensi Penamaan File (Case Sensitivity):**
  Saya mengalami kegagalan pada *pipeline* CI karena perbedaan sistem operasi. Di Windows (lokal), nama file `CreateProduct.html` dianggap sama dengan `createProduct`, tapi di lingkungan Linux (GitHub Actions),hal ini berbeda dan menyebabkan error `TemplateInputException`.
  **Strategi Perbaikan:** Saya menyamakan penamaan file HTML menjadi huruf kecil (camelCase) agar persis dengan *return string* pada Controller (misalnya mengubah `CreateProduct.html` menjadi `createProduct.html`), serta memperbarui *test assertion* agar ekspektasinya sesuai.

* **Kode Tidak Terpakai (Unused Code):**
  Saat menerapkan alat analisis kualitas kode (PMD), saya menemukan adanya variabel yang dideklarasikan tetapi tidak pernah digunakan (*unused private fields*) dan blok `catch` yang kosong. Hal ini dianggap sebagai *bad practice* karena mengotori kode dan berpotensi menyembunyikan error.
  **Strategi Perbaikan:** Saya menghapus variabel sampah tersebut dan membersihkan blok *try-catch* yang tidak diperlukan untuk memastikan kode bersih dan lolos scan PMD. Setelah itu saya lakukan push kembali.

## 2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Ya, implementasi saat ini sudah memenuhi definisi CI/CD.

1.  **Continuous Integration (CI):** Terpenuhi karena telah membuat workflow GitHub Actions (`ci.yml`, `scorecard.yml`, dan `pmd.yml`) yang secara otomatis menjalankan pengujian (*unit tests*) dan analisis keamanan/kualitas kode setiap kali ada push atau pull request. Ini memastikan kode yang digabungkan selalu teruji dan valid.
2.  **Continuous Deployment (CD):** Terpenuhi melalui integrasi dengan **Koyeb**. Saya telah mengatur agar setiap kali kalau ada perubahan yang berhasil di-merge ke branch `main`, Koyeb akan otomatis menarik kode terbaru, rebuild *Docker image*, dan melakukan deployment aplikasi secara langsung tanpa intervensi manual.  

  
# URL APP: vitreous-charline-a-ryan-gibran-p-sihaloho-2406419833-3e78dbd2.koyeb.app/