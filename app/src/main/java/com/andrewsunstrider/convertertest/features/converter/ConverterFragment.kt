package com.andrewsunstrider.convertertest.features.converter

import android.os.Bundle
import android.os.Parcel
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.andrewsunstrider.convertertest.ConverterApp
import com.andrewsunstrider.convertertest.R
import com.andrewsunstrider.convertertest.di.components.DaggerConverterComponent
import com.andrewsunstrider.convertertest.di.modules.ConverterModule
import com.andrewsunstrider.convertertest.features.converter.callback.RatesDialogListener
import kotlinx.android.synthetic.main.fragment_converter.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConverterFragment : Fragment(R.layout.fragment_converter) {

    @Inject
    lateinit var viewModel: ConverterViewModel

    private val ratesDialogListener by lazy {
        object : RatesDialogListener {
            override fun onRatesDialogClosed(data: Triple<RatesPosition, String, Float>) {
                viewModel.setRates(data)
            }

            override fun describeContents(): Int = 0

            override fun writeToParcel(p0: Parcel?, p1: Int) { /* do nothing */ }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onInitDependencyInjection()
        super.onViewCreated(view, savedInstanceState)

        initListeners()

        lifecycleScope.launch {
            viewModel.bind().collect { render(it) }
        }
    }

    private fun render(state: ConverterFragmentState) {
        when (state) {
            ConverterFragmentState.Idle -> launch()
            ConverterFragmentState.Launching -> { /* do nothing */
            }
            ConverterFragmentState.Success -> Log.d("Success", "Success -> Auth Activity running.")
            is ConverterFragmentState.ShowSendAmount -> showSendAmount(state.value)
            is ConverterFragmentState.ShowGetAmount -> showGetAmount(state.value)
            is ConverterFragmentState.ShowSendKey -> showSendKey(state.value)
            is ConverterFragmentState.ShowGetKey -> showGetKey(state.value)
            is ConverterFragmentState.Failed -> Log.e("Error", "Error -> ${state.reason}")
            else -> throw IllegalArgumentException("Unknown type for $state.")
        }
    }

    private fun showSendAmount(amount: Float) {
        input_field_send.setText(amount.toString())
    }

    private fun showGetAmount(amount: Float) {
        input_field_get.setText(amount.toString())
    }

    private fun showSendKey(key: String) {
        button_choose_send.text = key
    }

    private fun showGetKey(key: String) {
        button_choose_get.text = key
    }

    private fun launch() {
        viewModel.handleApplicationLaunch()
    }

    private fun initListeners() {
        // TODO: 14.04.2021 change name views
        button_choose_send.setOnClickListener {
            openRatesDialog(RatesPosition.TOP)
        }
        button_choose_get.setOnClickListener {
            openRatesDialog(RatesPosition.BOTTOM)
        }
        input_field_send.apply {
            addTextChangedListener(RateEditTextListener { value ->
                viewModel.setAmount(RatesPosition.TOP, value)
            })
        }
        input_field_get.apply {
            addTextChangedListener(RateEditTextListener { value ->
                viewModel.setAmount(RatesPosition.BOTTOM, value)
            })
        }
    }

    private fun openRatesDialog(position: RatesPosition) {
        val action = ConverterFragmentDirections.actionConverterFragmentToRatesDialogFragment(
            ratesDialogListener,
            position
        )
        findNavController().navigate(action)
    }

    private fun onInitDependencyInjection() {
        DaggerConverterComponent
            .builder()
            .coreComponent(ConverterApp.coreComponent(requireContext()))
            .converterModule(ConverterModule(this))
            .build()
            .inject(this)
    }
}

enum class RatesPosition {
    TOP, BOTTOM
}