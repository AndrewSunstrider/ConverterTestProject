package com.andrewsunstrider.convertertest.di.components

import android.content.Context
import com.andrewsunstrider.convertertest.di.modules.NetworkModule
import com.andrewsunstrider.convertertest.di.modules.ContextModule
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
        NetworkModule::class
    ]
)
interface CoreComponent {

    /**
     * Provide dependency graph Context
     *
     * @return Context
     */
    fun context(): Context
}
