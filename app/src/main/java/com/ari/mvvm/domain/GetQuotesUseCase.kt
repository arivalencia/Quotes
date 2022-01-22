package com.ari.mvvm.domain

import com.ari.mvvm.data.QuoteRepository
import com.ari.mvvm.data.model.QuoteModel

class GetQuotesUseCase {

    private val repository = QuoteRepository()

    suspend operator fun invoke(): List<QuoteModel>? = repository.getQuotes()
}