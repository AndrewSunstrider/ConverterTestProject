package com.andrewsunstrider.convertertest.di.modules

import com.andrewsunstrider.convertertest.di.scopes.FeatureScope
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
    fun providesConverterViewModel() =
        fragment.viewModel {
            ConverterViewModel()
        }
}