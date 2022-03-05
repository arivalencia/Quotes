package com.ari.mvvm.domain

import com.ari.mvvm.data.QuoteRepository
import com.ari.mvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetRandomQuoteUseCaseTest{

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(quoteRepository)
    }

    @Test
    fun `when the data base return an empty list then get null`() = runBlocking {
        // Given
        coEvery { quoteRepository.getQuotesFromDB() } returns emptyList()

        // When
        val response: Quote? = getRandomQuoteUseCase()

        // Then
        assert(response == null)
    }

//    @Test
//    fun `when the data base return null then get null`() = runBlocking {
//        // Given
//        coEvery { quoteRepository.getQuotesFromDB() } returns null
//
//        // When
//        val response: Quote? = getRandomQuoteUseCase()
//
//        // Then
//        assert(response == null)
//    }

    @Test
    fun `when the data base return an not empty list then get random quote`() = runBlocking {
        // Given
        val quotes = listOf<Quote>(
            Quote(
                quote = "This a quote",
                author = "Ari Valencia"
            ),
            Quote(
                quote = "This a quote",
                author = "Ari Valencia"
            )
        )
        coEvery { quoteRepository.getQuotesFromDB() } returns quotes

        // When
        val response: Quote? = getRandomQuoteUseCase()

        // Then
        assert(response is Quote)
        assert(response == quotes.first())
    }

}