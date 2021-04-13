package com.andrewsunstrider.convertertest.features.converter

sealed class ConverterFragmentState {
    object Idle : ConverterFragmentState()
    object Launching : ConverterFragmentState()
    object Success : ConverterFragmentState()
    data class Failed(val reason: Throwable) : ConverterFragmentState()
}