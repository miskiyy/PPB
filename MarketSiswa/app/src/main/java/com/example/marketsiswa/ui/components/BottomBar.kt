package com.example.marketsiswa.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketsiswa.navigation.Screen
import com.example.marketsiswa.ui.theme.SecondaryContainerLight

@Composable
fun MarketSiswaBottomBar(
    selected: Screen,
    onNavigate: (Screen) -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BottomNavItem(
                label = "Home",
                icon = Icons.Default.Home,
                selected = selected == Screen.Home,
                onClick = { onNavigate(Screen.Home) },
            )
            BottomNavItem(
                label = "Add",
                icon = Icons.Default.AddCircle,
                selected = selected == Screen.Add,
                onClick = { onNavigate(Screen.Add) },
            )
            BottomNavItem(
                label = "Profile",
                icon = Icons.Default.Person,
                selected = selected == Screen.Profile,
                onClick = { onNavigate(Screen.Profile) },
            )
        }
    }
}

@Composable
private fun BottomNavItem(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val selectedBg = SecondaryContainerLight
    val selectedFg = MaterialTheme.colorScheme.onSecondaryContainer
    val unselectedFg = MaterialTheme.colorScheme.onSurfaceVariant

    Surface(
        onClick = onClick,
        color = if (selected) selectedBg else Color.Transparent,
        contentColor = if (selected) selectedFg else unselectedFg,
        shape = RoundedCornerShape(999.dp),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(icon, contentDescription = label)
            Text(text = label, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}
