package com.example.resepkita

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.resepkita.viewmodel.RecipeViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.resepkita.ui.navigation.Screen
import com.example.resepkita.ui.screens.DashboardScreen
import com.example.resepkita.ui.screens.FavoriteScreen
import com.example.resepkita.ui.screens.ProfileScreen
import com.example.resepkita.ui.screens.RecipeDetailScreen
import com.example.resepkita.ui.screens.SearchScreen

@Composable
fun ResepKitaApp(recipeViewModel: RecipeViewModel = viewModel()) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val recipes by recipeViewModel.recipes.collectAsState()

    Scaffold(
        bottomBar = {
            if (currentRoute == Screen.Dashboard.route || currentRoute == Screen.Search.route || currentRoute == Screen.Favorite.route || currentRoute == Screen.Profile.route) {
                NavigationBar(
                    containerColor = Color.White
                ) {
                    val primaryColor = Color(0xFFA93709)
                    
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Home, contentDescription = "Beranda") },
                        label = { Text("Beranda", fontWeight = FontWeight.SemiBold) },
                        selected = currentRoute == Screen.Dashboard.route,
                        onClick = {
                            navController.navigate(Screen.Dashboard.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = primaryColor,
                            selectedTextColor = primaryColor,
                            indicatorColor = Color(0xFFFFE0D2)
                        )
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Search, contentDescription = "Cari") },
                        label = { Text("Cari", fontWeight = if (currentRoute == Screen.Search.route) FontWeight.SemiBold else FontWeight.Normal) },
                        selected = currentRoute == Screen.Search.route,
                        onClick = {
                            navController.navigate(Screen.Search.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = primaryColor,
                            selectedTextColor = primaryColor,
                            indicatorColor = Color(0xFFFFE0D2),
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray
                        )
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = "Simpan") },
                        label = { Text("Simpan", fontWeight = if (currentRoute == Screen.Favorite.route) FontWeight.SemiBold else FontWeight.Normal) },
                        selected = currentRoute == Screen.Favorite.route,
                        onClick = {
                            navController.navigate(Screen.Favorite.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = primaryColor,
                            selectedTextColor = primaryColor,
                            indicatorColor = Color(0xFFFFE0D2),
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray
                        )
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Person, contentDescription = "Profil") },
                        label = { Text("Profil", fontWeight = if (currentRoute == Screen.Profile.route) FontWeight.SemiBold else FontWeight.Normal) },
                        selected = currentRoute == Screen.Profile.route,
                        onClick = {
                            navController.navigate(Screen.Profile.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = primaryColor,
                            selectedTextColor = primaryColor,
                            indicatorColor = Color(0xFFFFE0D2),
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dashboard.route) {
                DashboardScreen(
                    recipes = recipes,
                    onNavigateToDetail = { recipeId ->
                        navController.navigate(Screen.RecipeDetail.createRoute(recipeId))
                    },
                    onToggleFavorite = { recipeViewModel.toggleFavorite(it) }
                )
            }
            composable(Screen.Search.route) {
                SearchScreen(
                    recipes = recipes,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onNavigateToDetail = { recipeId ->
                        navController.navigate(Screen.RecipeDetail.createRoute(recipeId))
                    },
                    onToggleFavorite = { recipeViewModel.toggleFavorite(it) }
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    recipes = recipes.filter { it.isFavorite },
                    onNavigateToDetail = { recipeId ->
                        navController.navigate(Screen.RecipeDetail.createRoute(recipeId))
                    },
                    onToggleFavorite = { recipeViewModel.toggleFavorite(it) }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.RecipeDetail.route,
                arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
            ) { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: -1
                val recipe = recipeViewModel.getRecipeById(recipeId)

                if (recipe != null) {
                    RecipeDetailScreen(
                        recipe = recipe,
                        onNavigateBack = {
                            navController.popBackStack()
                        },
                        onToggleFavorite = { recipeViewModel.toggleFavorite(recipe.id) }
                    )
                } else {
                    // Fallback UI or pop back
                }
            }
        }
    }
}
