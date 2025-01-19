package com.conrradocamacho.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.conrradocamacho.aluvery.dao.ProductDao
import com.conrradocamacho.aluvery.ui.screens.ProductFormScreen
import com.conrradocamacho.aluvery.ui.screens.ProductFormScreenUIState
import com.conrradocamacho.aluvery.ui.theme.AluveryTheme

class ProductFormActivity : ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AluveryTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .padding(innerPadding)
                    ) {
                        val state = remember {
                            ProductFormScreenUIState(
                                onSaveClick = { product ->
                                    dao.save(product)
                                    finish()
                                }
                            )
                        }
                        ProductFormScreen(state = state)
                    }
                }
            }
        }
    }
}
