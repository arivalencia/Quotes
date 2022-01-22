package com.ari.mvvm.di

import com.ari.mvvm.core.ApiClient
import com.ari.mvvm.core.Constants
import com.ari.mvvm.data.network.QuoteApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Inject
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Inject
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit): QuoteApiClient =
        retrofit.create(QuoteApiClient::class.java)

}