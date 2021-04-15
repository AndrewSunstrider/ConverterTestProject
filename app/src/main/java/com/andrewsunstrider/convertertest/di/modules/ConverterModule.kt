package com.andrewsunstrider.convertertest.di.modules

import com.andrewsunstrider.convertertest.di.scopes.FeatureScope
import com.andrewsunstrider.convertertest.domain.repositories.CurrencyRepository
import com.andrewsunstrider.convertertest.domain.usecase.FetchRates
import com.andrewsunstrider.convertertest.extensions.viewModel
import com.andrewsunstrider.convertertest.features.converter.ConverterFragment
import com.andrewsunstrider.convertertest.features.converter.ConverterViewModel
import com.andrewsunstrider.convertertest.features.rates.RatesViewModel
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [ConverterComponent].
 *
 * @see Module
 */
@Module
class ConverterModule(
    private val fragment: ConverterFragment
) {

    /**
     * Create a provider method binding for [RatesViewModel].
     *
     * @return Instance of view model.
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun providesConverterViewModel(fetchRates: FetchRates) =
        fragment.viewModel {
            ConverterViewModel(fetchRates = fetchRates)
        }

    /**
     * Create a provider method binding for [FetchRates].
     *
     * @return Instance FetchRates.
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun provideFetchRates(
        currencyRepository: CurrencyRepository
    ) = FetchRates(
        currencyRepository = currencyRepository
    )
}