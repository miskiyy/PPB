
# COFFEE BLISS

## Membership Card Application

**Platform:** Android

---

# 1. Gambaran Produk

## Nama Produk

Coffee Bliss Membership Card App

## Deskripsi

Aplikasi membership digital untuk coffee shop yang memungkinkan pelanggan:

* Mendaftar sebagai member
* Menyimpan kartu member digital
* Mengumpulkan poin dari transaksi
* Melihat riwayat transaksi
* Menukarkan poin dengan reward

---

# 2. Tujuan Produk

Menggantikan kartu member fisik yang memiliki kekurangan:

* Mudah hilang
* Tidak praktis
* Sulit dikelola
* Sulit melakukan tracking transaksi

Dengan solusi:

* Membership digital
* Penyimpanan data terintegrasi
* Pengumpulan poin otomatis
* Reward management

---

# 3. Target Pengguna

## Pelanggan Coffee Shop

Kebutuhan:

* Mengumpulkan poin
* Melihat reward
* Tidak perlu membawa kartu fisik

---

## Kasir Coffee Shop

Kebutuhan:

* Menambahkan transaksi pelanggan
* Melihat status member

---

## Pemilik Coffee Shop

Kebutuhan:

* Mengelola program membership
* Melihat data member

---

# 4. Tech Stack

## Bahasa

Kotlin

## UI Framework

Jetpack Compose

## Database

Room Database

## Architecture

MVVM

---

# 5. Fitur Utama

## 1. Registrasi Member

Fungsi:

* Mendaftarkan member baru

Input:

* Nama
* Email
* Nomor HP

Output:

* Data member tersimpan

---

## 2. Digital Membership Card

Fungsi:

Menampilkan kartu member digital berisi:

* Nama member
* ID member
* QR Code
* Total poin

---

## 3. Tambah Transaksi

Fungsi:

Mencatat transaksi pembelian

Input:

* Nominal pembelian

Output:

* Poin otomatis bertambah

Formula:

```
Rp10.000 = 1 poin
```

Contoh:

```
Rp150.000
= 15 poin
```

---

## 4. Point System

Fungsi:

Menghitung poin otomatis berdasarkan transaksi.

Formula:

```
points = amount / 10000
```

Integer division.

Contoh:

```
25.000 → 2 poin
49.000 → 4 poin
150.000 → 15 poin
```

---

## 5. Riwayat Transaksi

Fungsi:

Menampilkan daftar transaksi member.

Data yang ditampilkan:

* Nominal transaksi
* Poin diperoleh
* Tanggal transaksi

---

## 6. Redeem Reward

Fungsi:

Menukarkan poin dengan hadiah.

Setelah redeem:

* Poin member berkurang

---

# 6. User Flow

## Alur Sistem

### 1

User membuka aplikasi

↓

### 2

User mendaftarkan data member

↓

### 3

Data disimpan ke Room Database

↓

### 4

User melihat kartu member digital

↓

### 5

User melakukan transaksi

↓

### 6

Sistem menghitung poin

↓

### 7

Poin bertambah

↓

### 8

User menukar reward

---

# 7. Functional Requirements

## FR-01 Registrasi Member

Deskripsi:

Pengguna dapat membuat akun member baru.

Input:

* Nama
* Email
* Nomor HP

Output:

* Data tersimpan

---

## FR-02 Daftar Member

Deskripsi:

Menampilkan seluruh member dari database.

Output:

* List member

---

## FR-03 Membership Card

Deskripsi:

Menampilkan kartu member digital.

Informasi:

* Nama
* ID Member
* QR Code
* Total poin

---

## FR-04 Tambah Transaksi

Deskripsi:

Mencatat transaksi pembelian.

Output:

* Poin otomatis dihitung

Aturan:

```
1 poin = Rp10.000
```

---

## FR-05 Riwayat Transaksi

Deskripsi:

Menampilkan daftar transaksi member.

---

## FR-06 Redeem Reward

Deskripsi:

Menukar poin dengan hadiah.

Output:

* Poin berkurang sesuai reward

---

# 8. Database Design

## Entity: Member

### Tabel Members

| Field  | Type    | Keterangan         |
| ------ | ------- | ------------------ |
| id     | INTEGER | PK, Auto Increment |
| name   | TEXT    | Nama Member        |
| email  | TEXT    | Email Member       |
| phone  | TEXT    | Nomor HP           |
| points | INTEGER | Total Poin         |

---

### Kotlin Entity

```kotlin
@Entity(tableName = "members")
data class Member(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val email: String,
    val phone: String,
    val points: Int = 0
)
```

---

## Entity: Transaction

### Tabel Transactions

| Field       | Type    | Keterangan        |
| ----------- | ------- | ----------------- |
| id          | INTEGER | PK                |
| memberId    | INTEGER | FK                |
| amount      | REAL    | Nominal Pembelian |
| pointEarned | INTEGER | Poin Didapat      |
| date        | TEXT    | Tanggal Transaksi |

---

### Kotlin Entity

```kotlin
@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = Member::class,
            parentColumns = ["id"],
            childColumns = ["memberId"]
        )
    ]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val memberId: Int,
    val amount: Double,
    val pointEarned: Int,
    val date: String
)
```

---

# 9. Reward System

| Point | Reward       |
| ----- | ------------ |
| 50    | Espresso     |
| 100   | Cappuccino   |
| 150   | Latte Gratis |

---

## Model Reward

```kotlin
data class Reward(
    val pointCost: Int,
    val name: String
)
```

```kotlin
val rewards = listOf(
    Reward(50, "Espresso"),
    Reward(100, "Cappuccino"),
    Reward(150, "Latte Gratis")
)
```

---

# 10. Screen List

## Splash Screen

Fungsi:

* Menampilkan logo Coffee Bliss

---

## Home Screen

Fungsi:

* Total member
* Daftar member
* Tombol tambah member

---

## Add Member Screen

Field:

* Nama
* Email
* Nomor HP

Button:

* Simpan

---

## Member Card Screen

Menampilkan:

* Nama
* Member ID
* QR Code
* Total poin

---

## Transaction Screen

Field:

* Nominal transaksi

Button:

* Simpan

Output:

* Poin otomatis

---

## Reward Screen

Menampilkan:

* Daftar reward
* Tombol redeem

---

# 11. Arsitektur Aplikasi

```text
UI Layer
    ↓
ViewModel Layer
    ↓
Repository Layer
    ↓
Room Database
```

---

## UI Layer

Jetpack Compose

Contoh Screen:

* SplashScreen
* HomeScreen
* AddMemberScreen
* MemberCardScreen
* TransactionScreen
* RewardScreen

---

## ViewModel Layer

Tugas:

* Menyimpan UI State
* Business Logic
* Menghubungkan Repository dan UI

---

## Repository Layer

Tugas:

* Mengambil data Room
* Menyimpan data Room

---

## Data Layer

Room Database

Komponen:

* Entity
* DAO
* Database

---

# 12. DAO yang Dibutuhkan

## MemberDao

```kotlin
@Dao
interface MemberDao {

    @Insert
    suspend fun insert(member: Member)

    @Query("SELECT * FROM members")
    fun getAllMembers(): Flow<List<Member>>

    @Update
    suspend fun update(member: Member)

    @Delete
    suspend fun delete(member: Member)
}
```

---

## TransactionDao

```kotlin
@Dao
interface TransactionDao {

    @Insert
    suspend fun insert(transaction: Transaction)

    @Query("""
        SELECT * FROM transactions
        WHERE memberId = :memberId
    """)
    fun getTransactions(
        memberId: Int
    ): Flow<List<Transaction>>
}
```

---

# 13. Success Metrics

Aplikasi dianggap berhasil jika:

* Member dapat didaftarkan dan tersimpan
* Poin dihitung otomatis setelah transaksi
* Riwayat transaksi dapat dilihat
* Reward dapat ditukar
* Crash rate < 2%
* Waktu loading < 2 detik

---

# 14. Non Functional Requirements

## Performance

* Startup < 3 detik
* Query database < 500 ms

---

## Reliability

* Data tetap tersedia setelah aplikasi ditutup

---

## Usability

* UI sederhana
* Intuitif
* Material Design 3

---

## Maintainability

* Menggunakan MVVM
* Repository Pattern

---

# 15. Future Enhancement (Opsional)

Jika ingin proyek terlihat lebih "mahasiswa TI semester 3-4 yang jago Android", aku sarankan tambahkan:

### QR Membership

* Generate QR dari memberId

### Membership Tier

* Silver
* Gold
* Platinum

### Firebase Authentication

* Login Google
* Login Email

### Push Notification

* Reward tersedia
* Promo coffee shop

### Export Member Card

* PDF
* PNG

### Analytics Dashboard

* Total member
* Total transaksi
* Reward paling sering diredeem

### Dark Mode

* Material 3 Dynamic Color

\