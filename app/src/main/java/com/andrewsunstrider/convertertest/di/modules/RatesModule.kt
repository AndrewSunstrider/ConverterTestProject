package com.andrewsunstrider.convertertest.di.modules

import com.andrewsunstrider.convertertest.di.scopes.FeatureScope
import com.andrewsunstrider.convertertest.domain.repositories.RatesRepository
import com.andrewsunstrider.convertertest.domain.usecase.GetRates
import com.andrewsunstrider.convertertest.extensions.viewModel
import com.andrewsunstrider.convertertest.features.rates.RatesDialogFragment
import com.andrewsunstrider.convertertest.features.rates.RatesViewModel
import com.andrewsunstrider.convertertest.util.AppCoroutineDispatchers
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

/**
 * Class that contributes to the object graph [RatesComponent].
 *
 * @see Module
 */
@Module
class RatesModule(
    private val fragment: RatesDialogFragment
) {
    @Provides
    @FeatureScope
    fun provideGetRates(
        repository: RatesRepository
    ) = GetRates(
        repository = repository
    )

    /**
     * Create a provider method binding for [RatesViewModel].
     *
     * @return Instance of view model.
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun providesRatesViewModel(getRates: GetRates) =
        fragment.viewModel {
            RatesViewModel(
                getRates = getRates
            )
        }

    /**
     * Create a provider method binding for [AppCoroutineDispatchers].
     *
     * @return Instances of app coroutines dispatchers.
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun provideCoroutineDispatchers() = AppCoroutineDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )
}