package com.ari.mvvm.domain

import com.ari.mvvm.data.QuoteRepository
import com.ari.mvvm.domain.model.Quote
import com.ari.mvvm.domain.model.toDataBase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuotesUseCaseTest{

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    private lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(quoteRepository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        // Given
        coEvery { quoteRepository.getQuotesFromApi() } returns emptyList()

        // When
        getQuotesUseCase()

        // Then
        coVerify(exactly = 0) { quoteRepository.clearQuotes() }
        coVerify(exactly = 0) { quoteRepository.insertQuotes(any()) }
        coVerify(exactly = 1) { quoteRepository.getQuotesFromDB() }
    }

    @Test
    fun `when the api return a list not empty then clean and insert quotes`() = runBlocking {
        // Given
        val quotes = listOf<Quote>(
            Quote(
                quote = "Los test son geniales",
                author = "Ari Valencia"
            ),
            Quote(
                quote = "Los test son geniales",
                author = "Ari Valencia"
            )
        )
        coEvery { quoteRepository.getQuotesFromApi() } returns quotes

        // When
        val response: List<Quote> = getQuotesUseCase()

        // Then
        coVerify(exactly = 1) { quoteRepository.clearQuotes() }
        coVerify(exactly = 1) { quoteRepository.insertQuotes(quotes.map { quote -> quote.toDataBase() }) }
        coVerify(exactly = 0) { quoteRepository.getQuotesFromDB() }
        assert(quotes == response)
    }
}