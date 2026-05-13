package com.example.marketsiswa.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.marketsiswa.model.Product
import com.example.marketsiswa.ui.theme.BackgroundLight
import com.example.marketsiswa.ui.theme.BorderSoftPink
import com.example.marketsiswa.ui.theme.OnPrimaryFixedLight
import com.example.marketsiswa.ui.theme.OnSecondaryFixedLight
import com.example.marketsiswa.ui.theme.PrimaryFixedLight
import com.example.marketsiswa.ui.theme.SecondaryFixedLight
import com.example.marketsiswa.ui.theme.SurfaceContainer
import com.example.marketsiswa.ui.theme.SurfaceContainerHigh
import com.example.marketsiswa.ui.theme.SurfaceContainerLow
import com.example.marketsiswa.ui.theme.SurfaceContainerLowest
import com.example.marketsiswa.ui.typography.BodyFontFamily
import com.example.marketsiswa.ui.typography.HeadlineFontFamily

@Composable
fun HomeScreen(
    products: List<Product>,
    onToggleFavorite: (Long) -> Unit,
) {
    val gridState = rememberLazyGridState()

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val columnCount = when {
            maxWidth < 600.dp -> 2
            maxWidth < 900.dp -> 3
            else -> 4
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(columnCount),
            state = gridState,
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 8.dp,
                bottom = 120.dp,
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize(),
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                HeroSection()
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(top = 8.dp)
                        .zIndex(1f),
                ) {
                    SearchAndFilterBar()
                }
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Produk Populer",
                        style = TextStyle(fontSize = 24.sp, lineHeight = 31.sp, fontWeight = FontWeight.Bold),
                        fontFamily = HeadlineFontFamily,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    TextButton(onClick = { /* no-op */ }) {
                        Text(
                            text = "Lihat Semua",
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = HeadlineFontFamily,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }

            items(products, key = { it.id }) { product ->
                ProductCard(product = product, onToggleFavorite = { onToggleFavorite(product.id) })
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                BenefitsBentoSection()
            }
        }
    }
}

@Composable
fun HeroSection() {
    val heroImageUrl =
        "https://lh3.googleusercontent.com/aida-public/AB6AXuCewVDLL_1SY9g1e4jYLV5HWqHjiUppSKpvBWVtlW3SeDPUd8xn_QMsrqDAoXODyGiuLB5-qLILG79f_3aKGq__SrQV1kv0ypDh5VgdAzs-VnvxFO-Cy5soi784QRw-GjLqaT4iR8WN4MCRtSUUPNImB8UvHW6Qm_iJ6sK5NJhdNJnxkSmYAUudzI3upIR3ljtaBzBJKD2gHTajmCG7JG7l_1CFehmhgmrPhWvCtn_MKYDfMvqjY7X3U2r_l4QusK4ac0rWjbLX3F0"

    Surface(
        shape = RoundedCornerShape(24.dp),
        color = Color.Transparent,
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSoftPink),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(24.dp)),
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        colors = listOf(BackgroundLight, SurfaceContainerLow),
                    ),
                )
                .padding(24.dp),
        ) {
            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val isWide = maxWidth >= 700.dp

                if (isWide) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        HeroText(modifier = Modifier.weight(1f))
                        HeroImage(heroImageUrl = heroImageUrl, showVerified = true, modifier = Modifier.weight(1f))
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        HeroText(modifier = Modifier.fillMaxWidth())
                        HeroImage(heroImageUrl = heroImageUrl, showVerified = false, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}

@Composable
private fun HeroText(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Surface(
            color = MaterialTheme.colorScheme.secondaryContainer,
            shape = RoundedCornerShape(999.dp),
        ) {
            Text(
                text = "Edisi Kampus",
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontFamily = HeadlineFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }

        Text(
            text = "Halo, Siswa!",
            color = MaterialTheme.colorScheme.onSurface,
            style = TextStyle(
                fontSize = 40.sp,
                lineHeight = 48.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = HeadlineFontFamily,
                letterSpacing = (-0.8).sp,
            ),
        )

        Text(
            text = "Temukan kebutuhan kuliahmu dari sesama mahasiswa. Mulai dari buku bekas hingga camilan malam.",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = BodyFontFamily,
            ),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.widthIn(max = 420.dp),
        )

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(top = 6.dp)) {
            Button(
                onClick = { /* no-op */ },
                shape = RoundedCornerShape(999.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                contentPadding = PaddingValues(horizontal = 22.dp, vertical = 12.dp),
            ) {
                Text("Mulai Belanja", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, fontFamily = HeadlineFontFamily)
            }
            Button(
                onClick = { /* no-op */ },
                shape = RoundedCornerShape(999.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SecondaryFixedLight,
                    contentColor = OnSecondaryFixedLight,
                ),
                contentPadding = PaddingValues(horizontal = 22.dp, vertical = 12.dp),
            ) {
                Text("Lihat Promo", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, fontFamily = HeadlineFontFamily)
            }
        }
    }
}

@Composable
private fun HeroImage(
    heroImageUrl: String,
    showVerified: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = heroImageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainerHigh),
            contentScale = ContentScale.Crop,
        )

        if (showVerified) {
            Surface(
                color = SurfaceContainerLowest,
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceContainerHigh),
                shadowElevation = 6.dp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(x = (-10).dp, y = 10.dp),
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Surface(
                        color = Color(0xFFE7F6EA),
                        shape = RoundedCornerShape(999.dp),
                        modifier = Modifier.size(40.dp),
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = Color(0xFF2E7D32),
                            )
                        }
                    }
                    Column {
                        Text("Terverifikasi", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        Text(
                            "Khusus Mahasiswa",
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchAndFilterBar() {
    var query by remember { mutableStateOf("") }
    var selectedChip by remember { mutableStateOf("Semua") }

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = SurfaceContainerLowest.copy(alpha = 0.92f),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSoftPink),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(16.dp)),
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text("Cari buku, elektronik, atau jasa...", fontSize = 16.sp, fontFamily = BodyFontFamily) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                ),
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OutlinedButton(
                    onClick = { /* no-op */ },
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(2.dp, MaterialTheme.colorScheme.outlineVariant),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 10.dp),
                ) {
                    Icon(Icons.Default.Menu, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Filter", fontWeight = FontWeight.SemiBold, fontFamily = HeadlineFontFamily)
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CategoryChip(
                        label = "Semua",
                        selected = selectedChip == "Semua",
                        onClick = { selectedChip = "Semua" },
                    )
                    CategoryChip(
                        label = "Buku",
                        selected = selectedChip == "Buku",
                        onClick = { selectedChip = "Buku" },
                    )
                    CategoryChip(
                        label = "Makanan",
                        selected = selectedChip == "Makanan",
                        onClick = { selectedChip = "Makanan" },
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryChip(label: String, selected: Boolean, onClick: () -> Unit) {
    val bg = if (selected) MaterialTheme.colorScheme.primary else SurfaceContainer
    val fg = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary

    Surface(
        onClick = onClick,
        color = bg,
        contentColor = fg,
        shape = RoundedCornerShape(999.dp),
        border = if (selected) null else androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = HeadlineFontFamily,
        )
    }
}

@Composable
fun ProductCard(
    product: Product,
    onToggleFavorite: () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = SurfaceContainerLowest,
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSoftPink),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(16.dp)),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3f)
                    .background(SurfaceContainerHigh),
            ) {
                if (product.imageUrl != null) {
                    AsyncImage(
                        model = product.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )
                } else {
                    androidx.compose.foundation.Image(
                        painter = androidx.compose.ui.res.painterResource(id = product.imageRes),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                IconButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.90f)),
                ) {
                    Icon(
                        imageVector = if (product.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (product.isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(
                    text = product.name,
                    style = TextStyle(fontSize = 20.sp, lineHeight = 28.sp, fontWeight = FontWeight.SemiBold),
                    fontFamily = HeadlineFontFamily,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = product.description,
                    style = TextStyle(fontSize = 16.sp, lineHeight = 26.sp, fontWeight = FontWeight.Normal),
                    fontFamily = BodyFontFamily,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(Modifier.height(6.dp))

                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    shape = RoundedCornerShape(999.dp),
                ) {
                    Text(
                        text = product.price,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = HeadlineFontFamily,
                    )
                }
            }
        }
    }
}

@Composable
fun BenefitsBentoSection() {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val isWide = maxWidth >= 700.dp

        if (isWide) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                BenefitsCardPrimary(modifier = Modifier.weight(2f))
                BenefitsCardSecondary(modifier = Modifier.weight(1f))
            }
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                BenefitsCardPrimary(modifier = Modifier.fillMaxWidth())
                BenefitsCardSecondary(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
private fun BenefitsCardPrimary(modifier: Modifier = Modifier) {
    Surface(
        color = SecondaryFixedLight,
        contentColor = OnSecondaryFixedLight,
        shape = RoundedCornerShape(24.dp),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .heightIn(min = 200.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "Satu-satunya Marketplace Khusus Mahasiswa",
                    style = TextStyle(fontSize = 24.sp, lineHeight = 31.sp, fontWeight = FontWeight.Bold),
                    fontFamily = HeadlineFontFamily,
                )
                Text(
                    text = "Jaminan transaksi aman dengan sistem verifikasi KTM (Kartu Tanda Mahasiswa).",
                    style = TextStyle(fontSize = 16.sp, lineHeight = 26.sp, fontWeight = FontWeight.Normal),
                    fontFamily = BodyFontFamily,
                    color = OnSecondaryFixedLight.copy(alpha = 0.85f),
                )
            }

            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
            )
        }
    }
}

@Composable
private fun BenefitsCardSecondary(modifier: Modifier = Modifier) {
    Surface(
        color = PrimaryFixedLight,
        contentColor = OnPrimaryFixedLight,
        shape = RoundedCornerShape(24.dp),
        border = androidx.compose.foundation.BorderStroke(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .heightIn(min = 200.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "Siap Jualan?",
                    style = TextStyle(fontSize = 24.sp, lineHeight = 31.sp, fontWeight = FontWeight.Bold),
                    fontFamily = HeadlineFontFamily,
                )
                Text(
                    text = "Ubah hobimu jadi cuan tambahan di kampus.",
                    style = TextStyle(fontSize = 16.sp, lineHeight = 26.sp, fontWeight = FontWeight.Normal),
                    fontFamily = BodyFontFamily,
                    color = OnPrimaryFixedLight.copy(alpha = 0.85f),
                )
            }

            TextButton(
                onClick = { /* no-op */ },
                contentPadding = PaddingValues(0.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Pelajari Caranya", fontWeight = FontWeight.Bold, color = OnPrimaryFixedLight, fontFamily = HeadlineFontFamily)
                    Icon(Icons.Default.ArrowForward, contentDescription = null, tint = OnPrimaryFixedLight)
                }
            }
        }
    }
}
