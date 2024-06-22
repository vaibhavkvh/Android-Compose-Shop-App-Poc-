package com.example.shoppingapppoc

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppingapppoc.ui.theme.ShoppingAppPOCTheme


class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingAppPOCTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()

                ) { _ ->
                    uiInCenter()

                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun uiInCenter() {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            createLoginScreen()
        }
    }

    //@Preview(showBackground = true)
    @Composable
    private fun createLoginScreen() {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(

                painter = painterResource(id = R.drawable.ic_icon),
                modifier = Modifier
                    .height(180.dp)
                    .width(180.dp),
                contentDescription = "Center image"
            )
            Spacer(modifier = Modifier.height(26.dp))
            EmailTextField(
                email,
                onEmailChange = { email = it },
                "Enter your email",
                KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(16.dp))
            EmailTextField(
                password,
                onEmailChange = { password = it },
                "Enter your password",
                KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedYellowButton("Log In", onClick = {
                // Handle button click here
                if (email.isBlank()) {
                    Toast.makeText(baseContext, "Please enter email", Toast.LENGTH_LONG).show()
                } else if (!isValidEmail(email)) {
                    Toast.makeText(baseContext, "Please enter valid email", Toast.LENGTH_LONG)
                        .show()
                } else if (password.isBlank()) {
                    Toast.makeText(baseContext, "Please enter password", Toast.LENGTH_LONG).show()
                }else{
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
            })
        }
    }

    @Composable
    private fun EmailTextField(
        email: String,
        onEmailChange: (String) -> Unit,
        hint: String,
        type: KeyboardType
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray.copy(alpha = 0.5f), // Remove underline
                unfocusedContainerColor = Color.LightGray.copy(alpha = 0.3f),
                cursorColor = Color.Red,
                focusedIndicatorColor = Color.Transparent, // Remove focused underline
                unfocusedIndicatorColor = Color.Transparent // Remove unfocused underline
            ),
            visualTransformation = if (type == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
            value = email,
            onValueChange = onEmailChange,
            placeholder = { Text(hint) },
            keyboardOptions = KeyboardOptions(keyboardType = type, imeAction = ImeAction.Next),
            singleLine = true // To make it a single-line input field
        )
    }

    @Composable
    fun OutlinedYellowButton(buttonText: String, onClick: () -> Unit) {
        /* Button(
             modifier = Modifier.width(IntrinsicSize.Max),
             onClick = onClick,
             colors = ButtonDefaults.buttonColors(
                 containerColor = Color.Transparent, // Transparent background
                 contentColor = Color.Red // Text color
             ),
             border = BorderStroke(1.dp, Color.Red), // Yellow outline
             contentPadding = PaddingValues(16.dp) // Add padding
         ) {
             Text(buttonText, modifier = Modifier.width(IntrinsicSize.Max))
         }*/

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                modifier = Modifier.weight(1f), // Take up all remaining space
                onClick = onClick,

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent, // Transparent background
                    contentColor = Color.Red // Text color
                ),
                border = BorderStroke(1.dp, Color.Red), // Yellow outline
                contentPadding = PaddingValues(8.dp) // Add padding
                // ... other properties ...
            ) {
                Text(buttonText)
            }
            // Other composables in the Row if needed
        }
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return if (TextUtils.isEmpty(target)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }

}