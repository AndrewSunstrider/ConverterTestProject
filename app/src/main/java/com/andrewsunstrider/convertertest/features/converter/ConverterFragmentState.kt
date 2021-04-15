package com.andrewsunstrider.convertertest.features.converter

sealed class ConverterFragmentState {
    object Idle : ConverterFragmentState()
    object Launching : ConverterFragmentState()
    object Success : ConverterFragmentState()
    data class Failed(val reason: Throwable) : ConverterFragmentState()
    data class ShowSendAmount(val value: Float) : ConverterFragmentState()
    data class ShowGetAmount(val value: Float) : ConverterFragmentState()
    data class ShowSendKey(val value: String) : ConverterFragmentState()
    data class ShowGetKey(val value: String) : ConverterFragmentState()
}