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
            override fun onRatesDialogClosed(rate: String) {
                button_choose_send.text = rate
            }

            override fun describeContents(): Int = 0

            override fun writeToParcel(p0: Parcel?, p1: Int) {
                // todo nothing
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onInitDependencyInjection()
        super.onViewCreated(view, savedInstanceState)

        initDefaultButtonsTitle()
        initListeners()

        lifecycleScope.launch {
            viewModel.bind().collect { render(it) }
        }
    }

    private fun render(state: ConverterFragmentState) {
        when (state) {
            ConverterFragmentState.Idle -> launch()
            ConverterFragmentState.Launching -> { }
            ConverterFragmentState.Success -> Log.d("Success", "Success -> Auth Activity running.")
            is ConverterFragmentState.Failed -> Log.e("Error", "Error -> ${state.reason}")
            else -> throw IllegalArgumentException("Unknown type for $state.")
        }
    }

    private fun launch() {
        viewModel.handleApplicationLaunch()
    }

    private fun initDefaultButtonsTitle() {
        button_choose_send.text = DEFAULT_RATE
        button_choose_get.text = DEFAULT_RATE
    }


    private fun initListeners() {
        button_choose_send.setOnClickListener {
            openRatesDialog(button_choose_send.text.toString())
        }
        button_choose_get.setOnClickListener {
            openRatesDialog(button_choose_get.text.toString())
        }
    }

    private fun openRatesDialog(rate: String) {
        val action = ConverterFragmentDirections.actionConverterFragmentToRatesDialogFragment(
            rate,
            ratesDialogListener
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

    companion object {
        const val DEFAULT_RATE = "USD"
    }
}