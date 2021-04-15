package com.andrewsunstrider.convertertest.di.modules

import com.andrewsunstrider.convertertest.di.scopes.FeatureScope
import com.andrewsunstrider.convertertest.domain.repositories.CurrencyRepository
import com.andrewsunstrider.convertertest.domain.usecase.GetRates
import com.andrewsunstrider.convertertest.extensions.viewModel
import com.andrewsunstrider.convertertest.features.rates.RatesDialogFragment
import com.andrewsunstrider.convertertest.features.rates.RatesViewModel
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [RatesComponent].
 *
 * @see Module
 */
@Module
class RatesModule(
    private val fragment: RatesDialogFragment
) {

    /**
     * Create a provider method binding for [GetRates].
     *
     * @return Instance of GetRates
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun provideGetRates(
        currencyRepository: CurrencyRepository
    ) = GetRates(
        currencyRepository = currencyRepository
    )


    /**
     * Create a provider method binding for [RatesViewModel].
     *
     * @return Instance of view model.
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun providesRatesViewModel(
        getRates: GetRates
    ) =
        fragment.viewModel {
            RatesViewModel(
                getRates = getRates
            )
        }
}