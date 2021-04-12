package com.andrewsunstrider.convertertest.features.converter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.andrewsunstrider.convertertest.R
import kotlinx.android.synthetic.main.fragment_converter.*

class ConverterFragment : Fragment(R.layout.fragment_converter) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        button_choose_send.setOnClickListener {
            openRatesDialog()
        }
        button_choose_get.setOnClickListener {
            openRatesDialog()
        }
    }

    private fun openRatesDialog() {
        findNavController().navigate(R.id.action_converterFragment_to_ratesDialogFragment)
    }
}