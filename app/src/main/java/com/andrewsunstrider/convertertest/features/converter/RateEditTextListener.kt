package com.andrewsunstrider.convertertest.features.converter

import android.text.Editable
import android.text.TextWatcher

class RateEditTextListener(
    private val type: RatesPosition,
    private val shareAction: (Pair<RatesPosition, Float>) -> Unit
) : TextWatcher {


    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { /* do nothing */ }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

        if (s.isEmpty()) return

        shareAction.invoke(Pair(type, s.toString().toFloat()))
    }

    override fun afterTextChanged(s: Editable) { /* do nothing */ }
}