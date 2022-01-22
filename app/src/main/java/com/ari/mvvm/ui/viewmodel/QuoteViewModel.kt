package com.ari.mvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ari.mvvm.data.model.QuoteModel
import com.ari.mvvm.data.model.QuoteProvider
import com.ari.mvvm.domain.GetQuotesUseCase
import com.ari.mvvm.domain.GetRandomQuoteUseCase
import kotlinx.coroutines.launch

class QuoteViewModel: ViewModel() {

    val quoteModel = MutableLiveData<QuoteModel>()
    val isLoading = MutableLiveData<Boolean>()

    private val getQuotesUseCase = GetQuotesUseCase()
    private val getRandomQuoteUseCase = GetRandomQuoteUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()

            if (!result.isNullOrEmpty()) {
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote() {
        isLoading.postValue(true)
        getRandomQuoteUseCase()?.let { quote ->
            quoteModel.value = quote
        }
        isLoading.postValue(false)
    }

}