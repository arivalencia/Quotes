package com.ari.mvvm.domain

import com.ari.mvvm.data.QuoteRepository
import com.ari.mvvm.data.model.QuoteModel
import com.ari.mvvm.domain.model.Quote
import com.ari.mvvm.domain.model.toDataBase
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val repository: QuoteRepository
) {

    suspend operator fun invoke(): List<Quote> {
        val quotes = repository.getQuotesFromApi()

        return if (quotes.isNotEmpty()) {
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { quote -> quote.toDataBase() })
            quotes
        } else {
            repository.getQuotesFromDB()
        }
    }
}