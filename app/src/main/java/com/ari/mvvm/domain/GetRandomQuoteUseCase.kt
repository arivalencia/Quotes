package com.ari.mvvm.domain

import com.ari.mvvm.data.model.QuoteModel
import com.ari.mvvm.data.model.QuoteProvider
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val quoteProvider: QuoteProvider
){

    operator fun invoke(): QuoteModel? {
        val quotes = quoteProvider.quotes

        if (!quotes.isNullOrEmpty()) {
            return quotes.random()
        }

        return null
    }

}