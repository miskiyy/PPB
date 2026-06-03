# Dokumentasi Teknis MarketSiswa

MarketSiswa adalah aplikasi marketplace berbasis Android yang dibangun dengan **Jetpack Compose** dan **Material Design 3**. Dokumentasi ini berfokus pada penjelasan kode dan struktur teknis aplikasi.

---

## 1. Struktur Proyek
```text
com.example.marketsiswa
├── model               # Definisi data utama (Product.kt)
├── navigation          # Sistem navigasi internal (Screen.kt)
└── ui                  # Semua komponen antarmuka
    ├── components      # Global UI: TopBar, BottomBar, Fab kustom
    ├── screens         # Implementasi setiap layar (add, home, profile)
    ├── theme           # Design System: Color, Type, Theme (M3)
    └── MarketSiswaApp  # Orchestrator: State Hoisting & Navigation Logic
```

---

## 2. Penjelasan Kode & Snippets

### A. Data Model (`Product.kt`)
Model data utama menggunakan `data class` untuk merepresentasikan barang dagangan mahasiswa.
```kotlin
data class Product(
    val id: Long = (0..Long.MAX_VALUE).random(),
    val name: String,
    val price: String,
    val description: String,
    val imageUrl: String? = null,
    val isFavorite: Boolean = false,
    @DrawableRes val imageRes: Int = R.drawable.product_placeholder,
)
```

### B. State Management (`MarketSiswaApp.kt`)
Aplikasi menggunakan pola **State Hoisting** di level root untuk mengelola data secara terpusat.
```kotlin
@Composable
fun MarketSiswaApp() {
    // State untuk navigasi
    var currentScreen by remember { mutableStateOf(Screen.Home) }
    
    // State untuk daftar produk (Reaktif)
    val productList = remember { mutableStateListOf<Product>() }
    
    Scaffold(
        topBar = { MarketSiswaTopBar(selected = currentScreen, onNavigate = { currentScreen = it }) },
        bottomBar = { MarketSiswaBottomBar(selected = currentScreen, onNavigate = { currentScreen = it }) },
        floatingActionButton = {
            if (currentScreen == Screen.Home) {
                MarketSiswaFab(onClick = { currentScreen = Screen.Add })
            }
        }
    ) { innerPadding ->
        // Navigasi manual berdasarkan state currentScreen
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentScreen) {
                Screen.Home -> HomeScreen(products = productList, onToggleFavorite = { /* logic */ })
                Screen.Add -> AddProductScreen(onProductAdded = { productList.add(0, it) })
                Screen.Profile -> ProfileScreen(productCount = productList.size)
            }
        }
    }
}
```

### C. Responsive Grid (`HomeScreen.kt`)
Menggunakan `BoxWithConstraints` untuk membuat layout yang adaptif terhadap ukuran layar.
```kotlin
@Composable
fun HomeScreen(products: List<Product>, onToggleFavorite: (Long) -> Unit) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        // Tentukan jumlah kolom berdasarkan lebar layar
        val columnCount = when {
            maxWidth < 600.dp -> 2 // Smartphone
            maxWidth < 900.dp -> 3 // Tablet Portrait
            else -> 4              // Desktop / Tablet Landscape
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(columnCount),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { product ->
                ProductCard(product, onToggleFavorite = { onToggleFavorite(product.id) })
            }
        }
    }
}
```

### D. Design System & Theme (`Theme.kt`)
Implementasi Material 3 dengan dukungan **Dynamic Color** (Android 12+).
```kotlin
private val LightColorScheme = lightColorScheme(
    primary = PinkMain,
    onPrimary = Color.White,
    primaryContainer = PinkContainer,
    background = BackgroundLight,
    surface = BackgroundLight,
    // ... detail warna lainnya
)

@Composable
fun MarketSiswaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Bisa diaktifkan untuk Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
```

### E. Custom Colors (`Color.kt`)
Warna didefinisikan secara eksplisit untuk menjaga konsistensi dengan desain *branded*.
```kotlin
val PinkMain = Color(0xFFAC2471)
val PinkContainer = Color(0xFFFF69B4)
val BackgroundLight = Color(0xFFFFF8F8)
val SurfaceContainerLow = Color(0xFFFFF0F3)
val BorderSoftPink = Color(0xFFFFF0F6)
```

---

## 3. Fitur Material Design 3 yang Digunakan
1. **Scaffold**: Integrasi TopBar, BottomBar, dan FAB secara seamless.
2. **Surface Container**: Penggunaan kontainer dengan tingkat *elevation* yang berbeda (Low, High, Lowest) untuk kedalaman visual.
3. **Card M3**: Digunakan pada daftar produk dengan sudut membulat (*rounded corners*) 16dp.
4. **Navigation Bar**: Pengganti Bottom Navigation lama dengan indikator seleksi yang lebih modern.

---

## 4. Cara Menambah Fitur Baru
Untuk menambahkan layar baru:
1. Tambahkan enum baru di `Screen.kt`.
2. Buat file UI baru di folder `ui/screens`.
3. Daftarkan layar tersebut di dalam blok `when(currentScreen)` pada `MarketSiswaApp.kt`.
