package com.example.marketsiswa.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marketsiswa.model.Product
import com.example.marketsiswa.navigation.Screen
import com.example.marketsiswa.ui.components.MarketSiswaBottomBar
import com.example.marketsiswa.ui.components.MarketSiswaFab
import com.example.marketsiswa.ui.components.MarketSiswaTopBar
import com.example.marketsiswa.ui.screens.add.AddProductScreen
import com.example.marketsiswa.ui.screens.home.HomeScreen
import com.example.marketsiswa.ui.screens.profile.ProfileScreen
import com.example.marketsiswa.ui.theme.BackgroundLight
import com.example.marketsiswa.ui.theme.MarketSiswaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketSiswaApp() {
    var currentScreen by remember { mutableStateOf(Screen.Home) }
    val productList = remember { mutableStateListOf<Product>() }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        if (productList.isEmpty()) {
            productList.add(
                Product(
                    id = 1L,
                    name = "Double Choco Brownies",
                    price = "Rp 25.000",
                    description = "Homemade brownies lumer, 1 box isi 6 potong besar.",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuC9j8ShH40z9TlGG7KsJwdL8ECdZrT3ViH92TuQdsXcS3MdjpnR_Lxn_cwYYUvuzyqdSgkP9MX6mETctqPJe6gGQrqzuWolCXUHbP5pqAgMqIM6lKX_J_FkPAP46XB0F3JQWjiMb-_bw-knXWF1bmnXG9WnWAvq8U1o8YbeTpeHqU1ow_kL1LnwbuPN-uRLM5_Se_M4aN_J7bM4l2ZgbYOfoa0L0sWyA51ZW5a5OZ-ve5M8txJdnM55T3pPDpijg4gPSTBTvfEzRmA",
                ),
            )
            productList.add(
                Product(
                    id = 2L,
                    name = "Oversized T-Shirt",
                    price = "Rp 75.000",
                    description = "Cotton combed 24s, tersedia various warna pastel.",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCCY6Pzeniw_RT0rOEJrxlenlgyLrviTm3lOxrtdJ9XTnwZRIP20TK7xUnrfbesQS6lbCNYOljt8VGkcHNO3u-HkFR6lhnqyzO2Xtzi0WhT1gRORjWh9nseaM8YCMIlAW3j4sGVf-4E6I5C0j0JbOvGgHF7ZMC6LLu_7XlJYysz4pP8-mjFMUa-YcoeZ3JXTlkNXHRuP4nvSjmAju6blBBv9dyfRNCIH8gih5UXvAbEoDUpUM110ijcBAxq71opzTLUAJF-jgNwcnU",
                ),
            )

            productList.add(
                Product(
                    id = 3L,
                    name = "Beaded Bracelet Set",
                    price = "Rp 12.000",
                    description = "Handmade dengan cinta, bisa request inisial nama.",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDDOQEKHhMQk12ag-TFZbbc67lPErZzdNd5AIkPrc_csCeOpUK6qMTs008OfEhE7Vh3rqCx0u0JDTlRgkPtjwJA0_uTXL9KLFZ4Gx7sZEUr62CrV_8gR8Zqkic5O-7r_u-nY_rPp9E8Lb3D0KlAa3Tpha4vH6AkRL6w-AmBD9r_oOTvdkHiFrvFa0UZkVECL_w97bQadv_2qf79K4OzWJfJQu2lOVr0ojJOPDHcFRCJMPQNU7s8mjRUGVyWJ8TwrXerITCOWIs8gY8",
                ),
            )

            productList.add(
                Product(
                    id = 4L,
                    name = "Textbook Calculus Ed 9",
                    price = "Rp 120.000",
                    description = "Bekas kondisi 95% mulus, tidak ada coretan.",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuA2MHSTy6enss_R6BnZTgOlQQPYgvCh7mUJ9XdYD7_-V1muuv5TFQ8JUhOddTiPm5sDnPSxVHpYaSU33F5m4NNdh89cR2D8pnhjCK1j2ySV0eUhIe5gaIT6EzSqbD-Iw6eR16zIs2Noxq9Tq61zViBPCsTJqBNnSw307YMdi5Q6KTC93NtblTdhx4jBxdlqAEMiyP-aYsO_JD4yM_nla2flkhKFTtGo2WnWG2Ut31I0ZQsdSxTtxrjbXMV0nflOd38fYuT2w5WOHgs",
                ),
            )

            productList.add(
                Product(
                    id = 5L,
                    name = "Scientific Calculator",
                    price = "Rp 150.000",
                    description = "Casio fx-991EX, fungsi normal, baterai baru.",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCHG6-s0_L8X8Q-r-o8Xqf9M0X7R-R6Q7P-Q9T8U-V5W6X7Y8Z9",
                ),
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            MarketSiswaTopBar(
                selected = currentScreen,
                onNavigate = { currentScreen = it },
            )
        },
        bottomBar = {
            MarketSiswaBottomBar(
                selected = currentScreen,
                onNavigate = { currentScreen = it },
            )
        },
        floatingActionButton = {
            if (currentScreen == Screen.Home) {
                MarketSiswaFab(onClick = { currentScreen = Screen.Add })
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundLight)
                .padding(innerPadding),
        ) {
            ResponsiveScreen {
                when (currentScreen) {
                    Screen.Home -> HomeScreen(
                        products = productList,
                        onToggleFavorite = { productId ->
                            val idx = productList.indexOfFirst { it.id == productId }
                            if (idx >= 0) {
                                val old = productList[idx]
                                productList[idx] = old.copy(isFavorite = !old.isFavorite)
                            }
                        },
                    )

                    Screen.Add -> AddProductScreen(
                        onProductAdded = { newProduct ->
                            productList.add(0, newProduct)
                        },
                    )

                    Screen.Profile -> ProfileScreen(productCount = productList.size)
                }
            }
        }
    }
}

@Composable
private fun ResponsiveScreen(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 0.dp),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .widthIn(max = 1280.dp),
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMain() {
    MarketSiswaTheme {
        MarketSiswaApp()
    }
}
