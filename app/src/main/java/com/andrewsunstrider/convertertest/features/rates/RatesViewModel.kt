package com.andrewsunstrider.convertertest.features.rates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrewsunstrider.convertertest.data.networking.models.RatesEntity
import com.andrewsunstrider.convertertest.domain.usecase.GetRates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RatesViewModel @Inject constructor(
    private val getRates: GetRates
) : ViewModel() {

    private val states = MutableStateFlow<RatesDialogState>(RatesDialogState.Idle)

    fun bind() = states.asStateFlow()

    fun handleApplicationLaunch() {
        viewModelScope.launch {
            states.value = RatesDialogState.Launching
            try {
                states.value = RatesDialogState.Success(getRates())
            } catch (error: Throwable) {
                states.value = RatesDialogState.Failed(error)
            }
        }
    }

    private suspend fun getRates(): RatesEntity {
        return getRates.getRates()
    }
}