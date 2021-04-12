package com.andrewsunstrider.convertertest.di.modules

import android.content.Context
import com.andrewsunstrider.convertertest.ConverterApp
import com.andrewsunstrider.convertertest.util.AppCoroutineDispatchers
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Class that contributes to the object graph [AppComponent].
 *
 * @see Module
 */
@Module
class AppModule {

    /**
     * Create a provider method binding for [Context].
     *
     * @param application Sample Application.
     * @return Instance of context.
     * @see Provides
     */
    @Provides
    fun provideContext(application: ConverterApp): Context = application.applicationContext

    /**
     * Create a provider method binding for [AppCoroutineDispatchers].
     *
     * @return Instances of app coroutines dispatchers.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideCoroutineDispatchers() = AppCoroutineDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )
}