package com.ari.mvvm.domain

import com.ari.mvvm.data.QuoteRepository
import com.ari.mvvm.data.database.dao.QuoteDao
import com.ari.mvvm.data.database.entities.toDomain
import com.ari.mvvm.data.model.QuoteModel
import com.ari.mvvm.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository
){

    suspend operator fun invoke(): Quote? {
        val quotes = quoteRepository.getQuotesFromDB()
        return if (!quotes.isNullOrEmpty()) quotes.random() else null
    }

}