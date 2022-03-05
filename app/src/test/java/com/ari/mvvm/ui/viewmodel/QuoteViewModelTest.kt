package com.ari.mvvm.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ari.mvvm.domain.GetQuotesUseCase
import com.ari.mvvm.domain.GetRandomQuoteUseCase
import com.ari.mvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuoteViewModelTest{

    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetQuotesUseCase

    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    private lateinit var quoteViewModel: QuoteViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotesUseCase, getRandomQuoteUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }


    @Test
    fun `when viewModel is created at the first time, get all quotes and set the first value`() = runTest {
        // Given
        val quotes = listOf<Quote>(
            Quote(quote = "Test made by Ari Valencia Delgado", author = "I am a software engineer"),
            Quote(quote = "Test made by Ari Valencia Delgado", author = "I am a software engineer")
        )
        coEvery { getQuotesUseCase() } returns quotes

        // When
        quoteViewModel.onCreate()

        // Then
        assert(quoteViewModel._currentQuote.value == quotes.first())
    }

    @Test
    fun `when randomQuoteUseCase return a quote set on the liveData`() = runTest {
        // Given
        val quote = Quote(quote = "This is my test quote", author = "Ari Valencia Delgado")
        coEvery { getRandomQuoteUseCase() } returns quote

        // When
        quoteViewModel.randomQuote()

        // Then
        assert(quoteViewModel._currentQuote.value is Quote)
        assert(quoteViewModel._currentQuote.value == quote)
    }

    @Test
    fun `if randomQuoteUseCase return null keep the last value`() = runTest {
        // Given
        val lastQuote = Quote(quote = "This is my test quote", author = "Ari Valencia Delgado")
        quoteViewModel._currentQuote.value = lastQuote
        coEvery { getRandomQuoteUseCase() } returns null

        // When
        quoteViewModel.randomQuote()

        // Then
        assert(quoteViewModel._currentQuote.value is Quote)
        assert(quoteViewModel._currentQuote.value == lastQuote)
    }



}