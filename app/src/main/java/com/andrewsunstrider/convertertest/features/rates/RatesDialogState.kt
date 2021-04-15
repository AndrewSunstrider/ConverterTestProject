package com.andrewsunstrider.convertertest.features.rates

import com.andrewsunstrider.convertertest.data.networking.models.RatesEntity

sealed class RatesDialogState {
    object Idle : RatesDialogState()
    object Launching : RatesDialogState()
    data class Success(val value: RatesEntity) : RatesDialogState()
    data class Failed(val reason: Throwable) : RatesDialogState()
}