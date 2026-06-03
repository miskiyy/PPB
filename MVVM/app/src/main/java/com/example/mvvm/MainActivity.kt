package com.example.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvvm.data.local.database.AppDatabase
import com.example.mvvm.data.repository.UserRepository
import com.example.mvvm.ui.screen.LoginScreen
import com.example.mvvm.ui.theme.MVVMTheme
import com.example.mvvm.viewmodel.LoginViewModel
import com.example.mvvm.viewmodel.LoginViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val repository = UserRepository(database.userDao())
        val factory = LoginViewModelFactory(repository)

        setContent {
            MVVMTheme {
                // Corrected: factory = factory (bukan factory factory)
                val viewModel: LoginViewModel = viewModel(factory = factory)

                LaunchedEffect(Unit) {
                    viewModel.insertDummyUser()
                }

                LoginScreen(viewModel)
            }
        }
    }
}