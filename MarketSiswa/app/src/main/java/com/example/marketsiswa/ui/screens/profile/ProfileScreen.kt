package com.example.marketsiswa.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.marketsiswa.ui.theme.BorderSoftPink
import com.example.marketsiswa.ui.theme.MarketSiswaTheme
import com.example.marketsiswa.ui.theme.SurfaceContainerHigh
import com.example.marketsiswa.ui.theme.SurfaceContainerHighest
import com.example.marketsiswa.ui.theme.SurfaceContainerLow
import com.example.marketsiswa.ui.theme.SurfaceContainerLowest
import com.example.marketsiswa.ui.typography.BodyFontFamily
import com.example.marketsiswa.ui.typography.HeadlineFontFamily

@Composable
fun ProfileScreen(productCount: Int) {
    val avatarUrl =
        "https://lh3.googleusercontent.com/aida-public/AB6AXuBPIwhncNaZgSbOsDs6pUhm30Niq5wKBvCSORz5szWZGVUtf4LqspnynfasS5Q0QmCvl0RFoyVLQQ53RP6CvQeiRMYFRVMJZlx1cSbiG6YoFWTiAzUy_8qUstTwPu6WJBQeoqJplvTcTBAO1sCC-GQ_H6uuw_SsJZFj7m5dPnPKDcaZssxviJM08-3jF0gdCbYpMcy5SNzSaVI2ykwa7D-aDUTQml80GICy3Cg-3AlJfbTQGUsGW4pnibfWdcwRdtLnvIlqW_E2SAM"
    val activityImage1 =
        "https://lh3.googleusercontent.com/aida-public/AB6AXuCvcVqIZxo66V2RiI-5HZ_xEKb0AuRB-XRIwGotmtC1EtvHcUtm7MqbkWxm9V4Vhw08mtI1hGdh4q9hZFBVTDldCS-482vQwo1QkZ_OA_Fc6niimKUU5vgA8UYfkdakVbse_e2VOlWkzwqOWEFf5LsmOrl_hJTup_G_6nKEKFq0z3pYXx4WgTrJxUolQQjqzLxAKhU8kWExvFyz0WuUPgAn6R7civJQSTlL2hx9IWKm-x7cwch9UNEleyHVR_oHBa8oEJOphaKbhj0"
    val activityImage2 =
        "https://lh3.googleusercontent.com/aida-public/AB6AXuCyacd-Wj9nL21_mmCV74TiPgIKY2kpFkIbc84vCwtBKS_xAlNXq_QWLnf6PUoGrL6qyK3CVpHt9Oo-d-_bU7oEh-5F11LaCXUEg-PJvVPX-zMHq99RjBwB7mWjHB_32PidX1mvb2BvqI2MWJCpxWErIoFtwKN_kQq9rB6NuXWd07oVQxkIR8CstJfTKKDAkDZgRYDwfAaBY5KqYOd1ZgNKMIFDVtD1zTpJjYboMiOakfCot5qEtyMY85ihIGniXxf_sLF1J-HhyoI"

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isWide = maxWidth >= 900.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp, bottom = 120.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Box {
                    Surface(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = CircleShape,
                        modifier = Modifier.size(if (isWide) 160.dp else 128.dp),
                        tonalElevation = 0.dp,
                    ) {
                        Box(modifier = Modifier.padding(6.dp)) {
                            Surface(
                                color = SurfaceContainerLowest,
                                shape = CircleShape,
                                modifier = Modifier.fillMaxSize(),
                            ) {
                                AsyncImage(
                                    model = avatarUrl,
                                    contentDescription = "Profile Picture",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape),
                                )
                            }
                        }
                    }

                    Surface(
                        onClick = { /* edit */ },
                        color = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        shape = CircleShape,
                        shadowElevation = 6.dp,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .offset(x = (-4).dp, y = (-4).dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit",
                            modifier = Modifier.padding(10.dp),
                        )
                    }
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Siti Aminah",
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 31.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = HeadlineFontFamily,
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp),
                        )
                        Text(
                            text = "Kelas 10-A",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 26.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = BodyFontFamily,
                            ),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }

            BoxWithConstraints(modifier = Modifier.fillMaxWidth().widthIn(max = 1280.dp)) {
                val showExtra = maxWidth >= 600.dp
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        StatCard(
                            headline = "12",
                            label = "Produk Terjual",
                        )
                    }

                    Box(modifier = Modifier.weight(1f)) {
                        StatusCardVerified()
                    }

                    if (showExtra) {
                        Box(modifier = Modifier.weight(1f)) {
                            StatCard(headline = "4.8", label = "Rating Penjual")
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            StatCard(headline = productCount.toString(), label = "Produk Aktif")
                        }
                    }
                }
            }

            if (isWide) {
                Row(
                    modifier = Modifier.fillMaxWidth().widthIn(max = 1280.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    SettingsSection(modifier = Modifier.weight(1f))
                    ActivitySection(activityImage1 = activityImage1, activityImage2 = activityImage2, modifier = Modifier.weight(1f))
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth().widthIn(max = 1280.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    SettingsSection(modifier = Modifier.fillMaxWidth())
                    ActivitySection(activityImage1 = activityImage1, activityImage2 = activityImage2, modifier = Modifier.fillMaxWidth())
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 8.dp, bottom = 20.dp)) {
                OutlinedButton(
                    onClick = { /* logout */ },
                    shape = RoundedCornerShape(999.dp),
                    border = androidx.compose.foundation.BorderStroke(2.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.20f)),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 22.dp, vertical = 10.dp),
                ) {
                    Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null)
                    Spacer(Modifier.width(10.dp))
                    Text("Keluar dari Akun", fontFamily = HeadlineFontFamily, fontWeight = FontWeight.SemiBold)
                }

                Spacer(Modifier.height(10.dp))
                Text(
                    text = "MarketSiswa v2.4.0 • Made for Students",
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                    fontFamily = BodyFontFamily,
                    fontSize = 12.sp,
                )
            }
        }
    }
}

@Composable
private fun StatCard(headline: String, label: String) {
    Surface(
        color = SurfaceContainerLow,
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSoftPink),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(12.dp)),
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = headline,
                color = MaterialTheme.colorScheme.primary,
                style = TextStyle(
                    fontSize = 40.sp,
                    lineHeight = 48.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = HeadlineFontFamily,
                ),
            )
            Text(
                text = label,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontFamily = HeadlineFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
            )
        }
    }
}

@Composable
private fun StatusCardVerified() {
    Surface(
        color = SurfaceContainerLow,
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSoftPink),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(12.dp)),
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Icon(
                imageVector = Icons.Default.VerifiedUser,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp),
            )

            Text(
                text = "Status Akun",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontFamily = HeadlineFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
            )

            Surface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                shape = RoundedCornerShape(999.dp),
            ) {
                Text(
                    text = "Verified",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    fontFamily = HeadlineFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                )
            }
        }
    }
}

@Composable
private fun SettingsSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "Pengaturan Akun",
            style = TextStyle(fontSize = 20.sp, lineHeight = 28.sp, fontWeight = FontWeight.SemiBold, fontFamily = HeadlineFontFamily),
            modifier = Modifier.padding(horizontal = 4.dp),
        )

        SettingButton(icon = Icons.Default.Person, label = "Informasi Pribadi")
        SettingButton(icon = Icons.Default.Notifications, label = "Notifikasi")
        SettingButton(icon = Icons.Default.Shield, label = "Keamanan")
    }
}

@Composable
private fun SettingButton(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Surface(
        onClick = { /* no-op */ },
        color = SurfaceContainerLowest,
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSoftPink),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(12.dp)),
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Surface(
                    color = SurfaceContainerHigh,
                    shape = CircleShape,
                    modifier = Modifier.size(40.dp),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    }
                }
                Text(
                    text = label,
                    fontFamily = HeadlineFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                )
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun ActivitySection(
    activityImage1: String,
    activityImage2: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "Riwayat Aktivitas",
            style = TextStyle(fontSize = 20.sp, lineHeight = 28.sp, fontWeight = FontWeight.SemiBold, fontFamily = HeadlineFontFamily),
            modifier = Modifier.padding(horizontal = 4.dp),
        )

        ActivityRow(
            imageUrl = activityImage1,
            title = "Buku Biologi XI Terjual",
            subtitle = "2 jam yang lalu",
            trailing = {
                Text(
                    text = "+Rp 45k",
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = HeadlineFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                )
            },
        )

        ActivityRow(
            imageUrl = activityImage2,
            title = "Botol Minum Pastel Baru",
            subtitle = "Kemarin",
            trailing = {
                Surface(color = MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(6.dp)) {
                    Text(
                        text = "DRAFT",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontFamily = HeadlineFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                    )
                }
            },
        )

        TextButton(onClick = { /* no-op */ }, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Lihat Semua Aktivitas",
                color = MaterialTheme.colorScheme.primary,
                fontFamily = HeadlineFontFamily,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Composable
private fun ActivityRow(
    imageUrl: String,
    title: String,
    subtitle: String,
    trailing: @Composable () -> Unit,
) {
    Surface(
        color = SurfaceContainerLowest,
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSoftPink),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(12.dp)),
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(SurfaceContainerHighest),
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontFamily = HeadlineFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = subtitle,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontFamily = HeadlineFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                )
            }

            trailing()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MarketSiswaTheme {
        ProfileScreen(productCount = 5)
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ProfileScreenWidePreview() {
    MarketSiswaTheme {
        ProfileScreen(productCount = 10)
    }
}
