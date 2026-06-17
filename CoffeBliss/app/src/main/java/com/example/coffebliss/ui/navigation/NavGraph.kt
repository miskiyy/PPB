package com.example.coffebliss.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coffebliss.CoffeeBlissApp
import com.example.coffebliss.ui.screens.addmember.AddMemberScreen
import com.example.coffebliss.ui.screens.home.HomeScreen
import com.example.coffebliss.ui.screens.membercard.MemberCardScreen
import com.example.coffebliss.ui.screens.reward.RewardScreen
import com.example.coffebliss.ui.screens.splash.SplashScreen
import com.example.coffebliss.ui.screens.transaction.TransactionHistoryScreen
import com.example.coffebliss.ui.screens.transaction.TransactionScreen
import com.example.coffebliss.viewmodel.MemberDetailViewModel
import com.example.coffebliss.viewmodel.MemberViewModel

object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val ADD_MEMBER = "add_member"
    const val MEMBER_CARD = "member_card/{memberId}"
    const val TRANSACTION = "transaction/{memberId}"
    const val TRANSACTION_HISTORY = "transaction_history/{memberId}"
    const val REWARD = "reward/{memberId}"

    fun memberCard(memberId: Int) = "member_card/$memberId"
    fun transaction(memberId: Int) = "transaction/$memberId"
    fun transactionHistory(memberId: Int) = "transaction_history/$memberId"
    fun reward(memberId: Int) = "reward/$memberId"
}

@Composable
fun CoffeeBlissNavHost(
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val repository = (context.applicationContext as CoffeeBlissApp).repository

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            val viewModel: MemberViewModel = viewModel(
                factory = MemberViewModel.factory(repository)
            )
            HomeScreen(
                viewModel = viewModel,
                onNavigateToAddMember = { navController.navigate(Routes.ADD_MEMBER) },
                onNavigateToMemberCard = { memberId ->
                    navController.navigate(Routes.memberCard(memberId))
                }
            )
        }

        composable(Routes.ADD_MEMBER) {
            val viewModel: MemberViewModel = viewModel(
                factory = MemberViewModel.factory(repository)
            )
            AddMemberScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.MEMBER_CARD,
            arguments = listOf(navArgument("memberId") { type = NavType.IntType })
        ) { backStackEntry ->
            val memberId = backStackEntry.arguments?.getInt("memberId") ?: 0
            val viewModel: MemberDetailViewModel = viewModel(
                factory = MemberDetailViewModel.factory(repository, memberId)
            )
            MemberCardScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToTransaction = { navController.navigate(Routes.transaction(memberId)) },
                onNavigateToHistory = { navController.navigate(Routes.transactionHistory(memberId)) },
                onNavigateToReward = { navController.navigate(Routes.reward(memberId)) }
            )
        }

        composable(
            route = Routes.TRANSACTION,
            arguments = listOf(navArgument("memberId") { type = NavType.IntType })
        ) { backStackEntry ->
            val memberId = backStackEntry.arguments?.getInt("memberId") ?: 0
            val viewModel: MemberDetailViewModel = viewModel(
                factory = MemberDetailViewModel.factory(repository, memberId)
            )
            TransactionScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.TRANSACTION_HISTORY,
            arguments = listOf(navArgument("memberId") { type = NavType.IntType })
        ) { backStackEntry ->
            val memberId = backStackEntry.arguments?.getInt("memberId") ?: 0
            val viewModel: MemberDetailViewModel = viewModel(
                factory = MemberDetailViewModel.factory(repository, memberId)
            )
            TransactionHistoryScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.REWARD,
            arguments = listOf(navArgument("memberId") { type = NavType.IntType })
        ) { backStackEntry ->
            val memberId = backStackEntry.arguments?.getInt("memberId") ?: 0
            val viewModel: MemberDetailViewModel = viewModel(
                factory = MemberDetailViewModel.factory(repository, memberId)
            )
            RewardScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
