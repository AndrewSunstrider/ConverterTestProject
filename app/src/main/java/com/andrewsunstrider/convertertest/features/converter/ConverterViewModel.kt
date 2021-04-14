package com.andrewsunstrider.convertertest.features.converter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrewsunstrider.convertertest.extensions.roundToTwoDecimalPlace
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConverterViewModel : ViewModel() {

    private val states = MutableStateFlow<ConverterFragmentState>(ConverterFragmentState.Idle)

    private var dataView = ShowDataPresentation()

    fun bind() = states.asStateFlow()

    fun handleApplicationLaunch() {
        viewModelScope.launch {
            states.value = ConverterFragmentState.Launching
            try {
                ConverterFragmentState.Success
            } catch (error: Throwable) {
                states.value = ConverterFragmentState.Failed(error)
            }
        }
    }

    fun setRates(data: Triple<RatesPosition, String, Float>) {
        calculateValue(data.first)
        when (data.first) {
            RatesPosition.TOP -> {
                dataView.sendKey = data.second
                dataView.sendRate = data.third
                updateSendKey()
                updateGetAmount()
            }
            RatesPosition.BOTTOM -> {
                dataView.getKey = data.second
                dataView.getRate = data.third
                updateGetKey()
                updateSendAmount()
            }
        }

    }

    private fun updateSendAmount() {
        states.value = ConverterFragmentState.ShowSendAmount(dataView.sendValue)
    }

    private fun updateGetAmount() {
        states.value = ConverterFragmentState.ShowGetAmount(dataView.getValue)
    }

    private fun updateSendKey() {
        states.value = ConverterFragmentState.ShowSendKey(dataView.sendKey)
    }

    private fun updateGetKey() {
        states.value = ConverterFragmentState.ShowGetKey(dataView.getKey)
    }

    fun setAmount(type: RatesPosition, amount: Float) {
        dataView.type = type
        when (type) {
            RatesPosition.TOP -> {
                if (dataView.sendValue == amount) return
                dataView.sendValue = amount
                dataView.getValue = calculateValue(type)
                updateGetAmount()
            }
            RatesPosition.BOTTOM -> {
                if (dataView.sendValue == amount) return
                dataView.getValue = amount
                dataView.sendValue = calculateValue(type)
                updateSendAmount()
            }
        }
    }

    private fun calculateValue(type: RatesPosition): Float = when (type) {
        RatesPosition.TOP -> ((dataView.sendValue / dataView.sendRate) * dataView.getRate).roundToTwoDecimalPlace()
        RatesPosition.BOTTOM -> ((dataView.getValue / dataView.getRate) * dataView.sendRate).roundToTwoDecimalPlace()
    }
}

data class ShowDataPresentation(
    var type: RatesPosition = RatesPosition.TOP,
    var sendValue: Float = DEFAULT_AMOUNT,
    var sendRate: Float = DEFAULT_RATE_VALUE,
    var sendKey: String = DEFAULT_RATE,
    var getValue: Float = DEFAULT_AMOUNT,
    var getRate: Float = DEFAULT_RATE_VALUE,
    var getKey: String = DEFAULT_RATE
) {

    companion object {
        const val DEFAULT_AMOUNT = 0.0f
        const val DEFAULT_RATE_VALUE = 1.0f
        const val DEFAULT_RATE = "USD"
    }
}