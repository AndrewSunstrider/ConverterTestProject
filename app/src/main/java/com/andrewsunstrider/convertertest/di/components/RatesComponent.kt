package com.andrewsunstrider.convertertest.di.components

import com.andrewsunstrider.convertertest.di.modules.RatesModule
import com.andrewsunstrider.convertertest.di.scopes.FeatureScope
import com.andrewsunstrider.convertertest.features.rates.RatesDialogFragment
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [RatesModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [RatesModule::class],
    dependencies = [CoreComponent::class]
)
interface RatesComponent {

    /**
     * Inject dependencies on component.
     *
     * @param ratesFragment rates component.
     */
    fun inject(ratesFragment: RatesDialogFragment)
}
