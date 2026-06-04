package com.example.newsapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.data.model.Article
import com.example.newsapp.ui.components.HeroCard
import com.example.newsapp.ui.components.NewsCard
import com.example.newsapp.viewmodel.NewsUiState
import com.example.newsapp.viewmodel.NewsViewModel

@Composable
fun HomeScreen(
    viewModel: NewsViewModel,
    onDetailClick: (Article) -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isDemoMode by viewModel.isDemoMode.collectAsState()
    val savedArticles by viewModel.savedArticles.collectAsState()

    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Home", "Search", "Saved")

    Scaffold(
        topBar = {
            // Screen 3 in mockup doesn't show the blue header bar.
            // If Selected Tab is Search, we don't display the top header block. We display it only for Home & Saved.
            if (selectedTabIndex != 1) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Spacer(modifier = Modifier.height(28.dp)) // Padding for status bar

                    // News App Title
                    Text(
                        text = "News App",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            fontSize = 20.sp
                        ),
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
                    )

                    // Tabs (Home, Search, Saved)
                    TabRow(
                        selectedTabIndex = selectedTabIndex,
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White,
                        indicator = { tabPositions ->
                            TabRowDefaults.SecondaryIndicator(
                                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                                color = Color.White,
                                height = 3.dp
                            )
                        },
                        divider = {}
                    ) {
                        tabTitles.forEachIndexed { index, title ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index },
                                text = {
                                    Text(
                                        text = title,
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                                            fontSize = 14.sp
                                        ),
                                        color = if (selectedTabIndex == index) Color.White else Color.White.copy(alpha = 0.7f)
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        val contentPadding = if (selectedTabIndex == 1) PaddingValues(0.dp) else innerPadding

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            when (state) {
                is NewsUiState.Loading -> {
                    // Custom Loading Screen: matches Mockup 4
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 4.dp,
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Loading...",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }

                is NewsUiState.Error -> {
                    // Custom Error Screen: matches Mockup 4
                    val error = state as NewsUiState.Error
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Red Exclamation Warning Icon
                        Surface(
                            modifier = Modifier.size(64.dp),
                            shape = CircleShape,
                            color = Color(0xFFEF5350)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "!",
                                    style = MaterialTheme.typography.headlineLarge.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.Black
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Failed to load news",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            ),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Please check your internet connection and try again.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        Button(
                            onClick = { viewModel.loadNews() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(horizontal = 8.dp),
                            shape = RoundedCornerShape(25.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                text = "Retry",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            )
                        }
                    }
                }

                is NewsUiState.Success -> {
                    val articles = (state as NewsUiState.Success).articles

                    // Demo banner indicator on Home Tab if active
                    val showBanner = isDemoMode && selectedTabIndex == 0

                    Column(modifier = Modifier.fillMaxSize()) {
                        AnimatedVisibility(
                            visible = showBanner,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f),
                                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = "Running in Demo Mode (Mock Data). Set your NewsAPI key in NewsRepository to see live news.",
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                                    modifier = Modifier.padding(12.dp)
                                )
                            }
                        }

                        when (selectedTabIndex) {
                            0 -> {
                                // Home Tab: matches Mockup 1
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize(),
                                    contentPadding = PaddingValues(bottom = 24.dp)
                                ) {
                                    // Featured Article Hero Card (Top Article)
                                    if (articles.isNotEmpty()) {
                                        item {
                                            HeroCard(
                                                article = articles[0],
                                                onClick = { onDetailClick(articles[0]) }
                                            )
                                        }
                                    }

                                    // "Breaking News" Section Title
                                    item {
                                        Text(
                                            text = "Breaking News",
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontSize = 16.sp
                                            ),
                                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 12.dp)
                                        )
                                    }

                                    // Sub-feed (Remaining articles)
                                    if (articles.size > 1) {
                                        items(articles.subList(1, articles.size)) { article ->
                                            NewsCard(
                                                article = article,
                                                onClick = { onDetailClick(article) }
                                            )
                                            HorizontalDivider(
                                                modifier = Modifier.padding(horizontal = 16.dp),
                                                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                                            )
                                        }
                                    }
                                }
                            }

                            1 -> {
                                // Search Tab: matches Mockup 3
                                Column(modifier = Modifier.fillMaxSize()) {
                                    Spacer(modifier = Modifier.height(28.dp)) // padding for status bar

                                    // Search Bar Text Field (Grey, Rounded)
                                    OutlinedTextField(
                                        value = searchQuery,
                                        onValueChange = { viewModel.setSearchQuery(it) },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp, vertical = 8.dp),
                                        placeholder = { Text("Search news...") },
                                        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search") },
                                        trailingIcon = {
                                            if (searchQuery.isNotEmpty()) {
                                                IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                                    Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                                                }
                                            }
                                        },
                                        shape = RoundedCornerShape(24.dp),
                                        singleLine = true,
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                            focusedBorderColor = Color.Transparent,
                                            unfocusedBorderColor = Color.Transparent
                                        )
                                    )

                                    // Tab Navigation inside Search (Go Back to Home / Saved)
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 20.dp, vertical = 8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Search Result",
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontSize = 16.sp
                                            )
                                        )
                                        
                                        // A quick button to go back to Home
                                        Text(
                                            text = "Back to Home",
                                            style = MaterialTheme.typography.labelMedium.copy(
                                                color = MaterialTheme.colorScheme.primary,
                                                fontWeight = FontWeight.Bold
                                            ),
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(8.dp))
                                                .clickable { selectedTabIndex = 0 }
                                                .padding(8.dp)
                                        )
                                    }

                                    // Filter list
                                    val filteredArticles = remember(articles, searchQuery) {
                                        if (searchQuery.isBlank()) {
                                            articles
                                        } else {
                                            articles.filter { article ->
                                                article.title.contains(searchQuery, ignoreCase = true) ||
                                                        (article.description?.contains(searchQuery, ignoreCase = true) == true) ||
                                                        (article.author?.contains(searchQuery, ignoreCase = true) == true)
                                            }
                                        }
                                    }

                                    if (filteredArticles.isEmpty()) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .weight(1f),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "No results found for \"$searchQuery\"",
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    } else {
                                        LazyColumn(
                                            modifier = Modifier.fillMaxSize(),
                                            contentPadding = PaddingValues(bottom = 24.dp)
                                        ) {
                                            items(filteredArticles) { article ->
                                                NewsCard(
                                                    article = article,
                                                    onClick = { onDetailClick(article) }
                                                )
                                                HorizontalDivider(
                                                    modifier = Modifier.padding(horizontal = 16.dp),
                                                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                            2 -> {
                                // Saved Articles Tab
                                Column(modifier = Modifier.fillMaxSize()) {
                                    Text(
                                        text = "Saved Articles",
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontSize = 16.sp
                                        ),
                                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp)
                                    )

                                    if (savedArticles.isEmpty()) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .weight(1f)
                                                .padding(32.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.FavoriteBorder,
                                                contentDescription = "No Saved Articles",
                                                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                                                modifier = Modifier.size(64.dp)
                                            )
                                            Spacer(modifier = Modifier.height(16.dp))
                                            Text(
                                                text = "No Saved Articles Yet",
                                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                                textAlign = TextAlign.Center
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text(
                                                text = "Open article detail and click the bookmark icon to save it for offline reading.",
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    } else {
                                        LazyColumn(
                                            modifier = Modifier.fillMaxSize(),
                                            contentPadding = PaddingValues(bottom = 24.dp)
                                        ) {
                                            items(savedArticles.toList()) { article ->
                                                NewsCard(
                                                    article = article,
                                                    onClick = { onDetailClick(article) }
                                                )
                                                HorizontalDivider(
                                                    modifier = Modifier.padding(horizontal = 16.dp),
                                                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
