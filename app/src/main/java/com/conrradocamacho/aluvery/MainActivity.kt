package com.conrradocamacho.aluvery

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.conrradocamacho.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AluveryTheme {
                Surface {
                    ProductItem()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductItem() {
    Column {
        Box(modifier = Modifier
            .height(50.dp)
            .width(50.dp)
            .background(Color.Blue)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Image of product"
        )
        Text(text = "Title")
        Text(text = "Price")
    }
}

