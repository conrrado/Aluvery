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
import com.conrradocamacho.aluvery.sampledata.sampleProducts
import com.conrradocamacho.aluvery.sampledata.sampleSections
import com.conrradocamacho.aluvery.ui.components.CardProductItem
import com.conrradocamacho.aluvery.ui.components.ProductSection
import com.conrradocamacho.aluvery.ui.components.SearchTextField
import com.conrradocamacho.aluvery.ui.theme.AluveryTheme

class HomeScreenUiState(searchText: String = "") {

    var text by mutableStateOf(searchText)
        private set

    val searchedProducts get() =
        if (text.isNotBlank()) {
            sampleProducts.filter {
                it.name.contains(text, ignoreCase = true) ||
                        it.description?.contains(text, ignoreCase = true) ?: false
            }
        } else emptyList()

    fun isShowSections() = text.isBlank()

    val onSearchChange: (String) -> Unit = {
        text = it
    }
}

@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
    state: HomeScreenUiState = HomeScreenUiState("")
) {
    Column {
        val searchedProducts = remember(state.text) {
            state.searchedProducts
        }
        SearchTextField(
            searchText = state.text,
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
                HomeScreen(sampleSections)
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
                    sampleSections,
                    state = HomeScreenUiState("pizza")
                )
            }
        }
    }
}
