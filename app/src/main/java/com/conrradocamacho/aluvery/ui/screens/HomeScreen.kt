package com.conrradocamacho.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.conrradocamacho.aluvery.model.Product
import com.conrradocamacho.aluvery.sampledata.sampleSections
import com.conrradocamacho.aluvery.ui.components.ProductSection
import com.conrradocamacho.aluvery.ui.theme.AluveryTheme

@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
) {
    Column {
        var text by remember { mutableStateOf("") }
        OutlinedTextField(value = text, onValueChange = {
            text = it
        })
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            sections.forEach {
                val title = it.key
                val products = it.value
                item {
                    ProductSection(title = title, products = products)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AluveryTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                HomeScreen(sampleSections)
            }
        }
    }
}
