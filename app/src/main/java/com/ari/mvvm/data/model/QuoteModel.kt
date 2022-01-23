package com.ari.mvvm.data.model

import com.ari.mvvm.domain.model.Quote

data class QuoteModel(val quote: String, val author: String)

fun QuoteModel.toDomain() = Quote(quote, author)
