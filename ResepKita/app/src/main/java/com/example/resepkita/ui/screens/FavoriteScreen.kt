package com.example.resepkita.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.resepkita.model.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    recipes: List<Recipe>,
    onNavigateToDetail: (Int) -> Unit,
    onToggleFavorite: (Int) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    
    val filterChips = listOf(
        "Semua", "Main Course", "< 30 Mins", "Spicy", "Indonesian"
    )

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
            ) {
                // Top Search Row (No back button since it's a bottom nav root)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Search Input
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .clip(CircleShape)
                            .background(Color(0xFFFBF2ED))
                            .padding(horizontal = 16.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            Icons.Filled.Search, 
                            contentDescription = "Search", 
                            tint = Color(0xFFA93709),
                            modifier = Modifier.padding(start = 12.dp)
                        )
                        
                        TextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            placeholder = { Text("Cari di favorit...") },
                            modifier = Modifier.weight(1f),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF58413A)
                            ),
                            singleLine = true
                        )
                        
                        if (searchQuery.isNotEmpty()) {
                            Icon(
                                Icons.Filled.Cancel, 
                                contentDescription = "Clear", 
                                tint = Color.Gray,
                                modifier = Modifier
                                    .padding(end = 12.dp)
                                    .clickable { searchQuery = "" }
                            )
                        }
                    }

                    IconButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Outlined.Tune, contentDescription = "Filter", tint = Color.Gray)
                    }
                }

                // Filter Chips Row
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filterChips) { chip ->
                        val isSelected = chip == "Semua"
                        Surface(
                            shape = CircleShape,
                            color = if (isSelected) Color(0xFFF26B3C) else Color.White,
                            border = if (!isSelected) androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE0C0B6)) else null,
                            modifier = Modifier.clickable { /*TODO*/ }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = chip,
                                    color = if (isSelected) Color.White else Color(0xFF58413A),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp
                                )
                                if (isSelected && chip != "Semua") {
                                    Icon(
                                        Icons.Filled.Close,
                                        contentDescription = "Remove",
                                        tint = Color.White,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                
                HorizontalDivider(color = Color(0xFFF5ECE7))
            }
        },
        containerColor = Color(0xFFFFF8F5)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val filteredRecipes = if (searchQuery.isBlank()) {
                recipes
            } else {
                recipes.filter { it.title.contains(searchQuery, ignoreCase = true) }
            }

            item {
                Text(
                    text = "Resep Tersimpan (${filteredRecipes.size})",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E1B18),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            items(filteredRecipes) { recipe ->
                SearchResultCard(
                    recipe = recipe, 
                    onClick = { onNavigateToDetail(recipe.id) },
                    onToggleFavorite = { onToggleFavorite(recipe.id) }
                )
            }
            
            item {
                Spacer(modifier = Modifier.height(80.dp)) // padding for bottom nav
            }
        }
    }
}
