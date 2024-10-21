package com.conrradocamacho.aluvery.sampledata

import com.conrradocamacho.aluvery.R
import com.conrradocamacho.aluvery.model.Product
import java.math.BigDecimal

val sampleProducts = listOf(
    Product(
        name = "Hamburguer",
        price = BigDecimal("18.99"),
        image = R.drawable.burguer
    ),
    Product(
        name = "Pizza",
        price = BigDecimal("24.99"),
        image = R.drawable.pizza
    ),
    Product(
        name = "Batata frita",
        price = BigDecimal("11.99"),
        image = R.drawable.fries
    )
)
