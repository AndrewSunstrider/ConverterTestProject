package com.andrewsunstrider.convertertest.features.converter.callback

import android.os.Parcelable

interface RatesDialogListener : Parcelable {
    fun onRatesDialogClosed(rate: String)
}