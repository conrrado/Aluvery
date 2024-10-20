package com.conrradocamacho.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.conrradocamacho.aluvery.sampledata.sampleProducts
import com.conrradocamacho.aluvery.ui.components.ProductSection

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ProductSection(
            title = "Promoções",
            products = sampleProducts
        )
        ProductSection(
            title = "Doces",
            products = sampleProducts
        )
        ProductSection(
            title = "Bebidas",
            products = sampleProducts
        )
        ProductSection(
            title = "Novidades",
            products = listOf()
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
