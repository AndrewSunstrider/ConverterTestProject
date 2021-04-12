package com.andrewsunstrider.convertertest.di.components

import com.andrewsunstrider.convertertest.ConverterApp
import com.andrewsunstrider.convertertest.di.modules.AppModule
import com.andrewsunstrider.convertertest.di.scopes.AppScope
import dagger.Component

/**
 * App component that application component's components depend on.
 *
 * @see Component
 */
@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {

    /**
     * Inject dependencies on application.
     *
     * @param application The sample application.
     */
    fun inject(application: ConverterApp)
}