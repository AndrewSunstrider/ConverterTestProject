package com.andrewsunstrider.convertertest.di.components

import android.content.Context
import com.andrewsunstrider.convertertest.data.database.RatesDao
import com.andrewsunstrider.convertertest.data.networking.services.ConverterService
import com.andrewsunstrider.convertertest.di.modules.ContextModule
import com.andrewsunstrider.convertertest.di.modules.DatabaseModule
import com.andrewsunstrider.convertertest.di.modules.NetworkModule
import com.andrewsunstrider.convertertest.di.modules.RepositoriesModule
import com.andrewsunstrider.convertertest.domain.repositories.CurrencyRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Core component that all module's components depend on.
 *
 * @see Component
 */
@Singleton
@Component(
    modules = [
        ContextModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        RepositoriesModule::class
    ]
)
interface CoreComponent {

    /**
     * Provide dependency graph Context
     *
     * @return Context
     */
    fun context(): Context

    /**
     * Provide dependency graph RatesRepository
     *
     * @return RatesRepository
     */
    fun ratesRepository(): RatesRepository
}
