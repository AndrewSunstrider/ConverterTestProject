package com.andrewsunstrider.convertertest.di.modules

import android.content.Context
import androidx.room.Room
import com.andrewsunstrider.convertertest.BuildConfig
import com.andrewsunstrider.convertertest.data.database.CurrencyDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    /**
     * Create a provider method binding for [CurrencyDatabase].
     *
     * @return Instance of database
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideCurrencyDatabase(context: Context) = Room.databaseBuilder(
        context,
        CurrencyDatabase::class.java,
        BuildConfig.DATABASE_NAME
    )
        // TODO: 15.04.2021 add migration
        .build()

    /**
     * Create a provider method binding for [RateDao].
     *
     * @return Instance of RateDao
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideRateDao(currencyDatabase: CurrencyDatabase) =
        currencyDatabase.ratesDao()
}