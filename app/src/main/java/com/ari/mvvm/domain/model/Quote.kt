package com.ari.mvvm.domain.model

import com.ari.mvvm.data.database.entities.QuoteEntity

data class Quote(
    val quote: String,
    val author: String
)

fun Quote.toDataBase() = QuoteEntity(quote = quote, author = author)