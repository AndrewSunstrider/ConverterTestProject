package com.andrewsunstrider.convertertest.features.converter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConverterViewModel : ViewModel() {

    private val states = MutableStateFlow<ConverterFragmentState>(ConverterFragmentState.Idle)

    fun bind() = states.asStateFlow()

    fun handleApplicationLaunch() {
        viewModelScope.launch {
            states.value = ConverterFragmentState.Launching
            try {
                states.value = ConverterFragmentState.Success
            } catch (error: Throwable) {
                states.value = ConverterFragmentState.Failed(error)
            }
        }
    }
}