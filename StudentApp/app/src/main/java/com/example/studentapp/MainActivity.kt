package com.example.studentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studentapp.data.AppDatabase
import com.example.studentapp.ui.MainScreen
import com.example.studentapp.ui.theme.StudentAppTheme
import com.example.studentapp.viewmodel.StudentViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val dao = AppDatabase
            .getDatabase(applicationContext)
            .siswaDao()
            
        val viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return StudentViewModel(dao) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        })[StudentViewModel::class.java]

        setContent {
            StudentAppTheme {
                MainScreen(viewModel)
            }
        }
    }
}