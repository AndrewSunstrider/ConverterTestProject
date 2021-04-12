package com.andrewsunstrider.convertertest

import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompatApplication

/**
 * Base class for maintaining global application state.
 *
 * @see SplitCompatApplication
 */
class ConverterApp : SplitCompatApplication() {


    /**
     * Called when the application is starting, before any activity, service, or receiver objects
     * (excluding content providers) have been created.
     *
     * @see SplitCompatApplication.onCreate
     */
    override fun onCreate() {
        super.onCreate()
    }
}
