package com.conrradocamacho.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.conrradocamacho.aluvery.R
import com.conrradocamacho.aluvery.model.Product
import com.conrradocamacho.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal

class ProductFormScreenUIState(
    onSaveClick: (Product) -> Unit = {}
) {
    var url by mutableStateOf("")
    val onValueChangeUrl: (String) -> Unit = {
        url = it
    }

    var name by mutableStateOf("")
    val onValueChangeName: (String) -> Unit = {
        name = it
    }

    var price by mutableStateOf("")
    var isPriceError by mutableStateOf(false)
    val onValueChangePrice: (String) -> Unit = {
        isPriceError = try {
            BigDecimal(it)
            false
        } catch (e: IllegalArgumentException) {
            it.isNotEmpty()
        }
        price = it
    }

    var description by mutableStateOf("")
    val onValueChangeDescription: (String) -> Unit = {
        description = it
    }

    val onSaveClick = {
        val convertedPrice = try {
            BigDecimal(price)
        } catch (e: NumberFormatException) {
            BigDecimal.ZERO
        }
        val product = Product(
            name = name,
            image = url,
            price = convertedPrice,
            description = description
        )
        onSaveClick(product)
    }
}

@Composable
fun ProductFormScreen(
    state: ProductFormScreenUIState = ProductFormScreenUIState()
) {
    val url = state.url
    val name = state.name
    val price = state.price
    val isPriceError = state.isPriceError
    val description = state.description
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .windowInsetsPadding(
                WindowInsets.ime.only(
                WindowInsetsSides.Bottom + WindowInsetsSides.Top)),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Criando um produto",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 28.sp
        )

        if (url.isNotBlank()) {
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder)
            )
        }
        TextField(
            value = url,
            onValueChange = state.onValueChangeUrl,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Url da imagem")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = name,
            onValueChange = state.onValueChangeName,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Nome")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            )
        )

        TextField(
            value = price,
            onValueChange = state.onValueChangePrice,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Preço")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            isError = isPriceError
        )
        if (isPriceError) {
            Text(
                text = "Preço deve ser um número decimal",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        TextField(
            value = description,
            onValueChange = state.onValueChangeDescription,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            label = {
                Text(text = "Descrição")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        Button(
            onClick = state.onSaveClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Salvar")
        }
    }
}

@Preview
@Composable
private fun ProductFormScreenPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen()
        }
    }
}