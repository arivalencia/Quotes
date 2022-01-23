package com.ari.mvvm.di

import android.content.Context
import androidx.room.Room
import com.ari.mvvm.data.database.QuoteDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val QUOTE_DATABASE_NAME = "quote_database"

    @Singleton
    @Provides
    fun provideQuoteDatabase(@ApplicationContext context: Context) = Room
        .databaseBuilder(
            context, QuoteDataBase::class.java,
            QUOTE_DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideQuoteDao(quoteDataBase: QuoteDataBase) = quoteDataBase.getDao()

}