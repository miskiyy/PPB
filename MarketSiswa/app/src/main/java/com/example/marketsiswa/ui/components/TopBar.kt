package com.example.marketsiswa.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.marketsiswa.navigation.Screen
import com.example.marketsiswa.ui.theme.BackgroundLight
import com.example.marketsiswa.ui.theme.PrimaryFixedLight
import com.example.marketsiswa.ui.typography.HeadlineFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketSiswaTopBar(
    selected: Screen,
    onNavigate: (Screen) -> Unit,
) {
    Surface(
        color = BackgroundLight.copy(alpha = 0.92f),
        shadowElevation = 0.dp,
    ) {
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "MarketSiswa",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = HeadlineFontFamily,
                        fontSize = 22.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        if (selected == Screen.Add) onNavigate(Screen.Home) else Unit
                    },
                ) {
                    Icon(
                        imageVector = if (selected == Screen.Add) Icons.Default.ArrowBack else Icons.Default.Menu,
                        contentDescription = if (selected == Screen.Add) "Back" else "Menu",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            },
            actions = {
                BoxWithConstraints {
                    if (maxWidth >= 600.dp) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(end = 8.dp),
                        ) {
                            TopBarLink(
                                label = "Home",
                                selected = selected == Screen.Home,
                                onClick = { onNavigate(Screen.Home) },
                            )
                            TopBarLink(
                                label = "Browse",
                                selected = false,
                                onClick = { /* no-op */ },
                            )
                            TopBarLink(
                                label = "Orders",
                                selected = false,
                                onClick = { /* no-op */ },
                            )
                        }
                    }
                }

                val avatarUrl =
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuCoR9BecTCckr0YWwggwki3UHivT7swMEKpoj-otlkSB6KrErGaIFSjlQOG1grGhbK5NjJf9lpKnUTEhrnvCKk6AprPBp45lZ7e06Ep5XRB0AAO9-IK-Y9ZQXtOS1GCVw7edybMT_AyOCjZCuoMDt_NWWMexDsqUH_wU4EWQUzypKohLRUJXcBLy6uzea7M1ePfOnCUv41Vj90Z_qIGZGHZmG2EgeseRebdat2JsrPyX3gfWDCfKhmFtm6wEn659kJYnvK9Km-Op6c"
                Box(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(PrimaryFixedLight)
                        .shadow(0.dp, CircleShape),
                ) {
                    AsyncImage(
                        model = avatarUrl,
                        contentDescription = "User Profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .background(PrimaryFixedLight)
                            .padding(0.dp),
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .background(Color.Transparent)
                            .shadow(0.dp, CircleShape),
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent,
                titleContentColor = MaterialTheme.colorScheme.onBackground,
            ),
        )
    }
}

@Composable
private fun TopBarLink(label: String, selected: Boolean, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
    ) {
        Text(
            text = label,
            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.SemiBold,
            fontFamily = HeadlineFontFamily,
            fontSize = 14.sp,
        )
    }
}
