# ResepKita - The Digital Kitchen Assistant 🍳

ResepKita adalah aplikasi resep masakan berbasis Android asli (Native) yang dirancang untuk menjadi asisten dapur digital Anda. Dibangun menggunakan teknologi modern Kotlin dan Jetpack Compose, aplikasi ini mengedepankan pendekatan Visual-First dengan desain minimalis, modern, dan sangat responsif.

## 🌟 Fitur Utama

Aplikasi ini dibagi menjadi 4 halaman utama (Tab Navigation) yang interaktif:

1. Beranda (Dashboard)
   - Tampilan utama yang menyapa pengguna dan menampilkan saran resep.
   - Dilengkapi dengan Kategori, daftar Trending Hari Ini (Card yang dapat digeser menyamping), dan Rekomendasi Untukmu (Grid layout).
2. Cari (Search)
   - Mesin pencari real-time. Anda dapat mengetikkan nama resep (misal: "Ayam") dan daftar resep akan langsung difilter tanpa perlu menekan tombol submit.
   - Menyediakan tata letak kartu yang horizontal dengan informasi detail seperti tingkat kesulitan dan perkiraan waktu masak.
3. Simpan (Favorite)
   - Halaman khusus untuk menampung resep yang Anda tandai.
   - Memiliki fitur pencariannya sendiri, jadi Anda bisa mencari resep spesifik hanya dari daftar resep yang Anda sukai.
   - Fitur Toggle Favorite (Ikon Hati): Klik ikon hati di mana saja (Beranda, Detail, atau Cari) untuk menyimpan atau menghapus resep dari halaman ini.
4. Profil (Profile)
   - Halaman statis yang memuat preferensi pengguna seperti Dark Mode, Dietary Filters, dan Account Settings.

### Fitur Tambahan
- Recipe Detail Screen: Halaman dinamis yang menampilkan detail spesifik resep yang diklik. Memuat Hero Image besar, Checkbox bahan-bahan interaktif (bisa dicentang jika bahan sudah siap), dan langkah-langkah memasak yang runtut.

---

## 🛠️ Cara Mengerjakan & Menjalankan (How to Run)

Aplikasi ini dikembangkan sepenuhnya menggunakan ekosistem Android Studio terbaru.

### Kebutuhan Sistem (Prerequisites)
- Android Studio (Disarankan versi Iguana atau Jellyfish terbaru).
- JDK 17 atau yang lebih baru.
- Emulator Android atau perangkat fisik Android.

### Cara Menjalankan
1. Buka Android Studio.
2. Pilih `File > Open...` dan arahkan ke folder proyek `ResepKita`.
3. Tunggu hingga proses Gradle Sync selesai (perhatikan bar progres di bawah). Proses ini mengunduh dependensi (seperti Navigation Compose dan Coil).
4. Setelah indeks dan sync selesai, pilih emulator atau HP Android Anda di menu Device Manager bagian atas.
5. Klik tombol Run (Segitiga Hijau) atau tekan `Shift + F10`.

---

## 🏗️ Penjelasan Kode (Architecture & State Management)

Aplikasi ini menerapkan pemisahan tugas (Separation of Concerns) yang baik antara antarmuka pengguna (UI) dan logika data.

### Struktur Folder
- `ui/screens/`: Berisi semua halaman UI (`DashboardScreen`, `SearchScreen`, `FavoriteScreen`, `ProfileScreen`, `RecipeDetailScreen`).
- `ui/navigation/`: Berisi pengaturan rute perpindahan antar layar (`ResepKitaNavigation`).
- `model/`: Berisi struktur data `Recipe.kt` dan dummy data global.
- `viewmodel/`: Berisi `RecipeViewModel.kt` untuk State Management.
- `ui/theme/`: Konfigurasi warna, tipografi (menggunakan font kustom Poppins), dan tema Jetpack Compose.

### Penjelasan Fungsionalitas (State Management)
Alih-alih menyebar data statis di setiap file, ResepKita menggunakan pendekatan Single Source of Truth:

1. `RecipeViewModel.kt`: 
   Menyimpan seluruh data resep di dalam `StateFlow`. Model view ini mengatur perubahan status saat pengguna menekan tombol Favorit (Love). Hal ini memastikan bahwa jika status favorit diubah di "Beranda", "Halaman Favorit" akan langsung tahu dan ter-update.
2. `ResepKitaApp.kt`:
   Sebagai hub pusat (Kerangka Navigasi Dasar). File ini melakukan instansiasi `RecipeViewModel` dan menyebarkan (pass down) daftar resep (`recipes`) ke layar yang membutuhkan (seperti Dashboard, Search, dan Favorite).
3. Filter Pencarian:
   Fungsi pencarian tidak memanggil server/database secara nyata, melainkan memfilter secara lokal (in-memory filtering) dari `StateFlow` resep dengan membandingkan `.contains(searchQuery)`.
4. Navigasi dengan Argumen (`navArgument`):
   Saat kartu resep diklik, aplikasi tidak sekadar pindah halaman. Aplikasi akan memanggil rute `recipe_detail/{recipeId}`. Navigasi ini melempar ID resep yang ditekan, yang kemudian dibaca oleh `RecipeDetailScreen` untuk menampilkan instruksi dan gambar yang tepat dari `ViewModel`.
5. Gambar Asinkron (Coil):
   Gambar makanan yang indah dimuat dari URL internet menggunakan pustaka `coil-compose`. Penggunaan `AsyncImage` memastikan UI tidak lag atau membeku saat mengunduh gambar. (Pastikan perangkat terhubung internet saat menjalankan aplikasi).
