package com.andrewsunstrider.convertertest.di.components

import com.andrewsunstrider.convertertest.di.modules.ConverterModule
import com.andrewsunstrider.convertertest.di.scopes.FeatureScope
import com.andrewsunstrider.convertertest.features.converter.ConverterFragment
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [ConverterModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [ConverterModule::class],
    dependencies = [CoreComponent::class]
)
interface ConverterComponent {

    /**
     * Inject dependencies on component.
     *
     * @param converterFragment rates component.
     */
    fun inject(converterFragment: ConverterFragment)
}
