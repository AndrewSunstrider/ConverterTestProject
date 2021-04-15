package com.andrewsunstrider.convertertest.features.converter

import android.text.Editable
import android.text.TextWatcher

class RateEditTextListener(
    private val shareAction: (Float) -> Unit
) : TextWatcher {


    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { /* do nothing */ }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        val value = if (s.isEmpty()) 0F else s.toString().toFloat()
        shareAction.invoke(value)
    }

    override fun afterTextChanged(s: Editable) { /* do nothing */ }
}