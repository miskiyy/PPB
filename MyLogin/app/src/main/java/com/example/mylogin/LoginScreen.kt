package com.example.mylogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(id=R.drawable.gambar), contentDescription = "Login Image",
            modifier = Modifier.size(350.dp))
        Text(text="Welcome Back", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Login to your account")

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(value = email, onValueChange = {
            email = it }, label = {
            Text(text = "Email address")
        })

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(value = password, onValueChange = {
            password = it
        }, label = {
            Text(text = "Password")
        })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {}) {
            Text(text = "Login")
        }
        Text(text = "Forgot Password?",
            modifier = Modifier.clickable{
            })

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Or sign in with")

        Row(
            modifier = Modifier.fillMaxWidth().padding(60.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(painter = painterResource(id = R.drawable.google),
                contentDescription = "Google",
                modifier = Modifier.size(60.dp).clickable{
                    // Google clicked
                }
            )

            Image(painter = painterResource(id = R.drawable.x),
                contentDescription = "Google",
                modifier = Modifier.size(60.dp).clickable{
                    // X clicked
                }
            )

            Image(painter = painterResource(id = R.drawable.fb),
                contentDescription = "Google",
                modifier = Modifier.size(60.dp).clickable{
                    // fb clicked
                }
            )
        }

    }
}