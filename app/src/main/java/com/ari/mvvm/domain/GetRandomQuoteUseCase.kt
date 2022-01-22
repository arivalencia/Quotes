package com.ari.mvvm.domain

import com.ari.mvvm.data.model.QuoteModel
import com.ari.mvvm.data.model.QuoteProvider

class GetRandomQuoteUseCase {

    operator fun invoke(): QuoteModel? {
        val quotes = QuoteProvider.quotes

        if (!quotes.isNullOrEmpty()) {
            return quotes.random()
        }

        return null
    }

}