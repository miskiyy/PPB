# Student Registration App - Jetpack Compose & Room Database

Aplikasi Android modern berbasis Kotlin dan Jetpack Compose yang menerapkan konsep **CRUD (Create, Read, Update, Delete)** menggunakan **Room Database** sebagai penyimpanan data lokal offline, didukung oleh **KSP (Kotlin Symbol Processing)** compiler.

Aplikasi ini menggunakan pola arsitektur **MVVM (Model-View-ViewModel)** untuk memisahkan UI reaktif dari logika bisnis dan database.

## 🎓 Informasi Mahasiswa
* **Nama**: Miskiyah
* **NRP**: 5025231119
* **Kelas**: PPB C
* **Repository**: [https://github.com/miskiyy/PPB](https://github.com/miskiyy/PPB)

---

## 🚀 Fitur Utama
1. **Tambah Siswa (Create)**: Memasukkan Nama Lengkap & Email melalui form input.
2. **Daftar Siswa Terdaftar (Read)**: Daftar reaktif menggunakan `LazyColumn` yang terupdate otomatis saat database berubah.
3. **Edit Siswa (Update)**: Menekan tombol edit pada list item memindahkan data siswa kembali ke form input untuk dilakukan pembaruan (dengan opsi tombol Batal & Update).
4. **Hapus Siswa (Delete)**: Menghapus data siswa secara permanen dari SQLite lokal via Room.
5. **Validasi Input**:
   * Nama tidak boleh kosong.
   * Email tidak boleh kosong.
   * Format email harus valid (mengandung karakter `@`).
6. **Desain UI Modern (Material 3)**:
   * Visual Card modern dengan rounded corner.
   * Badge inisial huruf nama otomatis.
   * Tampilan statis placeholder informatif ketika data kosong.

---

## 📐 Arsitektur MVVM & Alur Data Teknis

Aplikasi ini menerapkan pola arsitektur **Model-View-ViewModel (MVVM)** dengan implementasi **Unidirectional Data Flow (UDF)** yang memastikan pemisahan peran (*Separation of Concerns*) dan mempermudah pengujian komponen.

```
                  +----------------------------------------------+
                  |                  VIEW LAYER                  |
                  |  [MainScreen] -> [FormInput] / [StudentItem] |
                  +----------------------+-----------------------+
                                         |
                            User Events  |  Observe UI State
                          (Forms/Clicks) |  (collectAsState)
                                         v
                  +----------------------+-----------------------+
                  |               VIEWMODEL LAYER                |
                  |             [StudentViewModel]               |
                  +----------------------+-----------------------+
                                         |
                            Launch       |  Expose StateFlow
                          Coroutines     |  (stateIn operator)
                                         v
                  +----------------------+-----------------------+
                  |                  MODEL LAYER                 |
                  |       [SiswaDao]  <--->  [AppDatabase]       |
                  +----------------------------------------------+
```

### 1. Model (Data & Storage Layer)
Model mengelola persistensi data lokal menggunakan SQLite yang diabstraksikan oleh **Room Database**.
* **Entity (`Siswa.kt`)**: Kelas representasi data Kotlin yang dianotasikan dengan `@Entity(tableName = "siswa")`. Setiap properti dipetakan menjadi kolom tabel SQLite secara otomatis pada saat kompilasi.
* **DAO (`SiswaDao.kt`)**: Menyediakan antarmuka (interface) akses data.
  * Fungsi penulisan data (`insertSiswa`, `updateSiswa`, `deleteSiswa`) dianotasikan dengan `suspend` karena merupakan operasi pemblokir I/O yang harus dijalankan di dalam Coroutine.
  * Fungsi pembacaan data `getAllSiswa()` mengembalikan `Flow<List<Siswa>>`. Room secara otomatis menginisiasi query di background thread pool dan menghasilkan **Cold Flow** (hanya memicu pembacaan database ketika ada kolektor aktif).
* **Database Singleton (`AppDatabase.kt`)**: Menggunakan pola **Double-Checked Locking Singleton Pattern** dengan `@Volatile` dan block `synchronized(this)`. Hal ini menjamin bahwa instansiasi database Room bersifat *thread-safe* dan hanya ada satu instance database yang berjalan di memori perangkat, mencegah terjadinya kebocoran memori (*resource leak*) dan tabrakan data (*database locking*).

### 2. ViewModel (Business Logic & State Holder Layer)
`StudentViewModel` bertindak sebagai jembatan antara Model dan View. ViewModel ini bersifat *lifecycle-aware*, artinya ia tetap bertahan di memori saat terjadi perubahan konfigurasi Android (seperti rotasi layar).
* **Coroutine Scope (`viewModelScope`)**: Semua pemanggilan fungsi DAO berbasis `suspend` diluncurkan menggunakan `viewModelScope.launch`. Scope ini terikat langsung pada siklus hidup ViewModel. Jika ViewModel dihancurkan (`onCleared()`), semua coroutine yang sedang berjalan otomatis dibatalkan, mencegah kebocoran memori (*coroutines leak*).
* **Transformasi Data (`stateIn`)**:
  Untuk mengalirkan data reaktif ke UI secara aman, ViewModel mengonversi Cold Flow dari Room menjadi Hot Flow (`StateFlow`) menggunakan operator `.stateIn(...)`:
  ```kotlin
  val siswaList = dao.getAllSiswa()
      .stateIn(
          scope = viewModelScope,
          started = SharingStarted.WhileSubscribed(5000),
          initialValue = emptyList()
      )
  ```
  * `scope = viewModelScope`: Proses observasi database berjalan selama ViewModel aktif.
  * `started = SharingStarted.WhileSubscribed(5000)`: Mengamankan resource hardware. Upstream Flow dari database akan tetap berjalan selama ada UI yang mengamati. Jika aplikasi masuk ke background (UI tidak aktif), database listener akan mati setelah jeda toleransi 5 detik (berguna saat rotasi layar agar database tidak terus-menerus dibaca ulang).
  * `initialValue = emptyList()`: Menyediakan data awal instan bagi UI sebelum pembacaan awal dari Room selesai.

### 3. View (UI Presentation Layer)
View dibangun secara deklaratif menggunakan **Jetpack Compose** (`MainScreen`, `FormInput`, `StudentItem`).
* **UI Reaktif**: View mengamati `siswaList` dari ViewModel menggunakan `collectAsState()`. Fungsi ini mengonversi Kotlin `StateFlow` menjadi Compose `State<T>`. Setiap kali ada mutasi data di database SQLite, Room mendeteksi perubahan tersebut, mengirimkan emisi baru melalui Flow, memicu perubahan StateFlow, dan secara otomatis memicu proses **Recomposition** pada compiler Compose untuk menggambar ulang item-item list di layar secara instan.
* **State Hoisting & Stateless Components**:
  `FormInput` dirancang bersifat *stateless* (tidak menyimpan state internal) dengan memindahkan (hoisting) state formulir (`nama`, `email`, dan `editingSiswa`) ke `MainScreen`. `FormInput` hanya menerima data sebagai argumen parameter dan mengirimkan event interaksi ke atas melalui lambda expression (`onNamaChange`, `onEmailChange`, `onActionClick`, `onBatalClick`). Hal ini meningkatkan reusabilitas dan memudahkan pembuatan visual preview maupun unit testing.


---

## 📂 Struktur Project

```
com.example.studentapp/
├── data/
│   ├── Siswa.kt             # Entity (Skema Tabel Room)
│   ├── SiswaDao.kt          # Interface DAO (Query SQLite)
│   └── AppDatabase.kt       # Room Database Class (Singleton)
├── ui/
│   ├── theme/
│   │   ├── Color.kt         # Definisi Warna Pink & Dark Pink
│   │   ├── Theme.kt         # Konfigurasi Material Theme 3
│   │   └── Type.kt          # Font Tipografi
│   ├── FormInput.kt         # UI Formulir Input Dinamis
│   ├── StudentItem.kt       # Card Item List Siswa
│   └── MainScreen.kt        # Komponen Layar Utama Scaffold
├── viewmodel/
│   └── StudentViewModel.kt  # ViewModel (State & Logika Bisnis)
└── MainActivity.kt          # Entry Point Aplikasi
```

---

## 🛠️ Konfigurasi Dependensi (Gradle)

Proyek ini dibangun dengan menggunakan compiler **KSP** versi terbaru untuk performa kompilasi Room yang lebih cepat.

### `gradle/libs.versions.toml`
```toml
[versions]
agp = "9.1.1"
coreKtx = "1.18.0"
kotlin = "2.2.10"
room = "2.7.0-alpha11"
ksp = "2.2.10-2.0.2"

[libraries]
androidx-room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "room" }
androidx-room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "room" }
androidx-room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "room" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
```

### `app/build.gradle.kts`
```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.studentapp"
    compileSdk = 36
    
    defaultConfig {
        applicationId = "com.example.studentapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }
    // ...
}

dependencies {
    // ... Compose & Core ...
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    
    // Room + KSP Compiler
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}
```

---

## 🔄 Penjelasan State Management
1. **`mutableStateOf("")` & `remember`**: Digunakan di `MainScreen` untuk memantau perubahan teks nama dan email saat diketik oleh pengguna dan mempertahankannya agar tidak hilang saat recompilation.
2. **`stateIn`**: Mengubah cold `Flow` (aliran data pasif) dari Room Database query menjadi hot `StateFlow` (aliran data aktif/selalu update) di ViewModel agar tetap efisien terhadap siklus hidup activity.
3. **`collectAsState`**: Mengamati `StateFlow` tersebut di UI `MainScreen`. Setiap kali ada data ditambah/dihapus/diedit di Room, Compose otomatis mendeteksi emisi baru dan merender ulang daftar siswa secara instan.
4. **State Hoisting**: Memindahkan status data ke `MainScreen` dan menyalurkannya ke `FormInput` sebagai parameter callback statis. Cara ini menjaga composable `FormInput` tetap modular (*stateless*) dan terbebas dari direct database logic.

---

## 📝 Panduan Lisensi & Penggunaan
Proyek ini dibuat untuk pemenuhan tugas mata kuliah **Pemrograman Perangkat Bergerak (PPB)**. Kredensial default untuk uji coba aplikasi tidak dibatasi karena pendaftaran langsung dimasukkan ke database lokal Room pada perangkat pengguna.
