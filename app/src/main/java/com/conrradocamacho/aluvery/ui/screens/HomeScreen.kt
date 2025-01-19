package com.conrradocamacho.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.conrradocamacho.aluvery.sampledata.sampleCandies
import com.conrradocamacho.aluvery.sampledata.sampleDrinks
import com.conrradocamacho.aluvery.sampledata.sampleProducts
import com.conrradocamacho.aluvery.sampledata.sampleSections
import com.conrradocamacho.aluvery.ui.components.CardProductItem
import com.conrradocamacho.aluvery.ui.components.ProductSection
import com.conrradocamacho.aluvery.ui.components.SearchTextField
import com.conrradocamacho.aluvery.ui.theme.AluveryTheme

class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {
    fun isShowSections() = searchText.isBlank()
}

@Composable
fun HomeScreen(
    products: List<Product>
) {
    val sections = mapOf(
        "Todos produtos" to products,
        "Promoções" to sampleDrinks + sampleCandies,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks
    )
    var text by remember {
        mutableStateOf("")
    }
    fun containsInNameOrDescription(): (Product) -> Boolean = {
        it.name.contains(text, ignoreCase = true) ||
                it.description?.contains(text, ignoreCase = true) ?: false
    }
    val searchedProducts = remember(text, products) {
        if (text.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription()) +
                    products.filter(containsInNameOrDescription())
        } else emptyList()
    }
    val state = remember(products, text) {
        HomeScreenUiState(
            sections = sections,
            searchedProducts = searchedProducts,
            searchText = text,
            onSearchChange = {
                text = it
            }
        )
    }
    HomeScreen(state = state)
}

@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState()
) {
    Column {
        val sections = state.sections
        val text = state.searchText
        val searchedProducts = state.searchedProducts
        SearchTextField(
            searchText = text,
            modifier = Modifier.padding(16.dp),
            onSearchChange = state.onSearchChange
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (state.isShowSections()) {
                sections.forEach {
                    val title = it.key
                    val products = it.value
                    item {
                        ProductSection(title = title, products = products)
                    }
                }
            } else {
                items(searchedProducts) {
                    CardProductItem(
                        it,
                        Modifier.padding(horizontal = 16.dp),
                    )
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
                HomeScreen(HomeScreenUiState(sections = sampleSections))
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenWithSearchTextPreview() {
    AluveryTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                HomeScreen(
                    state = HomeScreenUiState(sections = sampleSections, searchText = "pizza")
                )
            }
        }
    }
}
