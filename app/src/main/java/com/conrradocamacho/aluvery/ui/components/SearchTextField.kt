package com.conrradocamacho.aluvery.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.conrradocamacho.aluvery.ui.theme.AluveryTheme

@Composable
fun SearchTextField(
    searchText: String,
    modifier: Modifier = Modifier,
    onSearchChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = searchText,
        onValueChange = {
            onSearchChange(it)
        },
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(100),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "ícone de busca"
            )
        },
        label = {
            Text(text = "Produto")
        },
        placeholder = {
            Text(text = "O que você procura?")
        }
    )
}

@Preview
@Composable
private fun SearchTextFieldPreview() {
    AluveryTheme {
        Surface {
            SearchTextField(
                searchText = "",
                modifier = Modifier.padding(16.dp),
                onSearchChange = {},
            )
        }
    }
}

@Preview
@Composable
private fun SearchTextFieldWithTextPreview() {
    AluveryTheme {
        Surface {
            SearchTextField(
                searchText = "pizza",
                modifier = Modifier.padding(16.dp),
                onSearchChange = {},
            )
        }
    }
}