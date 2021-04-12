package com.andrewsunstrider.convertertest

import android.content.Context
import com.andrewsunstrider.convertertest.di.components.CoreComponent
import com.andrewsunstrider.convertertest.di.components.DaggerAppComponent
import com.andrewsunstrider.convertertest.di.components.DaggerCoreComponent
import com.andrewsunstrider.convertertest.di.modules.ContextModule
import com.google.android.play.core.splitcompat.SplitCompatApplication

/**
 * Base class for maintaining global application state.
 *
 * @see SplitCompatApplication
 */
class ConverterApp : SplitCompatApplication() {

    lateinit var coreComponent: CoreComponent

    companion object {

        /**
         * Obtain core dagger component.
         *
         * @param context The application context
         */
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as? ConverterApp)?.coreComponent
    }

    /**
     * Called when the application is starting, before any activity, service, or receiver objects
     * (excluding content providers) have been created.
     *
     * @see SplitCompatApplication.onCreate
     */
    override fun onCreate() {
        super.onCreate()
        initCoreDependencyInjection()
        initAppDependencyInjection()
    }

    // ============================================================================================
    //  Private init methods
    // ============================================================================================

    /**
     * Initialize app dependency injection component.
     */
    private fun initAppDependencyInjection() {
        DaggerAppComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }

    /**
     * Initialize core dependency injection component.
     */
    private fun initCoreDependencyInjection() {
        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }
}
