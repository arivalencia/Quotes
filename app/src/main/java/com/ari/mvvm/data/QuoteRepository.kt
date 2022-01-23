package com.ari.mvvm.data

import com.ari.mvvm.data.database.dao.QuoteDao
import com.ari.mvvm.data.database.entities.QuoteEntity
import com.ari.mvvm.data.database.entities.toDomain
import com.ari.mvvm.data.model.toDomain
import com.ari.mvvm.data.network.QuoteService
import com.ari.mvvm.domain.model.Quote
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
){
    suspend fun getQuotesFromApi(): List<Quote> {
        val quotes = api.getQuotes()
        return quotes.map { quoteModel -> quoteModel.toDomain() }
    }

    suspend fun getQuotesFromDB(): List<Quote> {
        val quotes = quoteDao.getAllQuotes()
        return quotes.map { quoteEntity -> quoteEntity.toDomain() }
    }

    suspend fun insertQuotes(quotes: List<QuoteEntity>) = quoteDao.insertList(quotes)

    suspend fun clearQuotes() = quoteDao.clearTable()

}