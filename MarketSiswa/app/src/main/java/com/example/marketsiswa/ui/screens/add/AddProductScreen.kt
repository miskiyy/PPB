package com.example.marketsiswa.ui.screens.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Publish
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketsiswa.model.Product
import com.example.marketsiswa.ui.theme.BorderSoftPink
import com.example.marketsiswa.ui.theme.PinkMain
import com.example.marketsiswa.ui.theme.SecondaryContainerLight
import com.example.marketsiswa.ui.theme.SurfaceContainerHigh
import com.example.marketsiswa.ui.theme.SurfaceContainerLowest
import com.example.marketsiswa.ui.typography.BodyFontFamily
import com.example.marketsiswa.ui.typography.HeadlineFontFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AddProductScreen(onProductAdded: (Product) -> Unit) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Buku") }
    var isLoading by remember { mutableStateOf(false) }
    var showSuccess by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.TopEnd)
                .offset(x = 100.dp, y = 80.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.05f))
                .blur(60.dp),
        )
        Box(
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.BottomStart)
                .offset(x = (-100).dp, y = (-80).dp)
                .clip(CircleShape)
                .background(SecondaryContainerLight.copy(alpha = 0.10f))
                .blur(60.dp),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(top = 32.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(bottom = 20.dp)) {
                Text(
                    text = "Tambah Produk Baru",
                    style = TextStyle(
                        fontSize = 32.sp,
                        lineHeight = 42.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = HeadlineFontFamily,
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "Bagikan barang yang tidak terpakai kepada teman kampusmu.",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = BodyFontFamily,
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.widthIn(max = 520.dp),
                )
            }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = SurfaceContainerLowest,
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSoftPink),
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 720.dp)
                    .shadow(6.dp, RoundedCornerShape(12.dp)),
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    LabeledField(
                        label = "Nama Produk",
                        content = {
                            OutlinedTextField(
                                value = name,
                                onValueChange = { name = it },
                                placeholder = { Text("Contoh: Buku Kalkulus Semester 1", fontFamily = BodyFontFamily) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                shape = RoundedCornerShape(12.dp),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant,
                                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                ),
                            )
                        },
                    )

                    LabeledField(
                        label = "Harga",
                        content = {
                            OutlinedTextField(
                                value = price,
                                onValueChange = { price = it },
                                placeholder = { Text("0", fontFamily = BodyFontFamily) },
                                prefix = {
                                    Text(
                                        "Rp",
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = HeadlineFontFamily,
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                shape = RoundedCornerShape(12.dp),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant,
                                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                ),
                            )
                        },
                    )

                    LabeledField(
                        label = "Deskripsi",
                        content = {
                            OutlinedTextField(
                                value = desc,
                                onValueChange = { desc = it },
                                placeholder = { Text("Ceritakan kondisi barang dan alasan dijual...", fontFamily = BodyFontFamily) },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                minLines = 4,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant,
                                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                ),
                            )
                        },
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(
                            text = "Kategori",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontFamily = HeadlineFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            CategoryChoiceChip("Buku", selectedCategory == "Buku") { selectedCategory = "Buku" }
                            CategoryChoiceChip("Elektronik", selectedCategory == "Elektronik") { selectedCategory = "Elektronik" }
                            CategoryChoiceChip("Fashion", selectedCategory == "Fashion") { selectedCategory = "Fashion" }
                            CategoryChoiceChip("Lainnya", selectedCategory == "Lainnya") { selectedCategory = "Lainnya" }
                        }
                    }

                    Button(
                        onClick = {
                            isLoading = true
                            showSuccess = false
                            scope.launch {
                                delay(800)
                                onProductAdded(
                                    Product(
                                        name = name,
                                        price = "Rp ${price}",
                                        description = desc,
                                    ),
                                )
                                isLoading = false
                                showSuccess = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        enabled = name.isNotBlank() && price.isNotBlank() && !isLoading,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(22.dp),
                                color = MaterialTheme.colorScheme.onPrimary,
                                strokeWidth = 2.dp,
                            )
                        } else {
                            Icon(Icons.Default.Publish, contentDescription = null)
                            Spacer(Modifier.width(10.dp))
                            Text(
                                "Simpan Produk",
                                fontFamily = HeadlineFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
                            )
                        }
                    }
                }
            }

            if (showSuccess) {
                Spacer(Modifier.height(18.dp))
                Surface(
                    color = SurfaceContainerHigh,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderSoftPink),
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 720.dp)
                        .drawWithContent {
                            drawContent()
                            drawRect(
                                color = PinkMain,
                                size = Size(4.dp.toPx(), size.height),
                                topLeft = Offset.Zero,
                            )
                        },
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Surface(
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape,
                            modifier = Modifier.size(40.dp),
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
                            }
                        }
                        Column {
                            Text(
                                text = "Berhasil Terpasang!",
                                color = MaterialTheme.colorScheme.primary,
                                fontFamily = HeadlineFontFamily,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Text(
                                text = "Produk Anda kini dapat dilihat oleh mahasiswa lainnya.",
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontFamily = BodyFontFamily,
                                fontSize = 14.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LabeledField(label: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontFamily = HeadlineFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 2.dp),
        )
        content()
    }
}

@Composable
private fun CategoryChoiceChip(label: String, selected: Boolean, onClick: () -> Unit) {
    val bg = if (selected) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surfaceVariant
    val fg = if (selected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
    Surface(
        onClick = onClick,
        color = bg,
        contentColor = fg,
        shape = RoundedCornerShape(999.dp),
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            fontFamily = HeadlineFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
        )
    }
}
