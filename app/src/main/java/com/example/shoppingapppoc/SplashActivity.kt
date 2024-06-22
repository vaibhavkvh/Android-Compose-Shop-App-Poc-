package com.example.shoppingapppoc

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.shoppingapppoc.ui.theme.ShoppingAppPOCTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                delay(2000)
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }


        }
        setContent {
            ShoppingAppPOCTheme {
               // Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CenterImage()
               // }
            }

        }
    }

   // @Preview(showBackground = true)
    @Composable
    private fun CenterImage() {
        Column(
            modifier = Modifier.fillMaxHeight(),  //important
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_icon),
                modifier = Modifier
                    .padding(all = 15.dp),
                contentDescription = "Center image"
            )
        }
    }
}