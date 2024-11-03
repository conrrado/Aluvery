package com.conrradocamacho.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.conrradocamacho.aluvery.model.Product
import com.conrradocamacho.aluvery.sampledata.sampleProducts
import com.conrradocamacho.aluvery.sampledata.sampleSections
import com.conrradocamacho.aluvery.ui.components.ProductSection

@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
) {
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

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(sampleSections)
}
