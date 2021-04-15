package com.andrewsunstrider.convertertest.di.modules

import com.andrewsunstrider.convertertest.data.database.RatesDao
import com.andrewsunstrider.convertertest.data.networking.services.ConverterService
import com.andrewsunstrider.convertertest.data.repositories.DefaultCurrencyRepository
import com.andrewsunstrider.convertertest.domain.repositories.CurrencyRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    /**
     * Create a provider method binding for [DefaultCurrencyRepository]].
     *
     * @return Instance of Currency repository.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideDefaultRatesRepository(
        service: ConverterService,
        ratesDao: RatesDao
    ) = DefaultCurrencyRepository(service, ratesDao) as CurrencyRepository
}