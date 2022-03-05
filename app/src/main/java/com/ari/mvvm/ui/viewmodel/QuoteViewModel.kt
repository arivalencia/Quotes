package com.ari.mvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ari.mvvm.domain.GetQuotesUseCase
import com.ari.mvvm.domain.GetRandomQuoteUseCase
import com.ari.mvvm.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
): ViewModel() {

    val _currentQuote = MutableLiveData<Quote>()
    val _isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val result = getQuotesUseCase()

            if (!result.isNullOrEmpty()) {
                _currentQuote.postValue(result.first())
                _isLoading.postValue(false)
            }
        }
    }

    fun randomQuote() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            getRandomQuoteUseCase()?.let { quote ->
                _currentQuote.value = quote
            }
            _isLoading.postValue(false)
        }
    }

}