package com.andrewsunstrider.convertertest.features.converter.callback

import android.os.Parcelable
import com.andrewsunstrider.convertertest.features.converter.RatesPosition

interface RatesDialogListener : Parcelable {
    fun onRatesDialogClosed(data: Triple<RatesPosition, String, Float>)
}