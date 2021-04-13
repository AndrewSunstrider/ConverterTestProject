package com.andrewsunstrider.convertertest.features.converter

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.andrewsunstrider.convertertest.ConverterApp
import com.andrewsunstrider.convertertest.R
import com.andrewsunstrider.convertertest.di.components.DaggerConverterComponent
import com.andrewsunstrider.convertertest.di.modules.ConverterModule
import com.andrewsunstrider.convertertest.features.rates.RatesDialogState
import com.andrewsunstrider.convertertest.features.rates.RatesViewModel
import kotlinx.android.synthetic.main.fragment_converter.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConverterFragment : Fragment(R.layout.fragment_converter) {

    @Inject
    lateinit var viewModel: ConverterViewModel

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
            ConverterFragmentState.Launching -> { }
            ConverterFragmentState.Success -> Log.d("Success", "Success -> Auth Activity running.")
            is ConverterFragmentState.Failed -> Log.e("Error", "Error -> ${state.reason}")
            else -> throw IllegalArgumentException("Unknown type for $state.")
        }
    }

    private fun launch() {
        viewModel.handleApplicationLaunch()
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

    private fun onInitDependencyInjection() {
        DaggerConverterComponent
            .builder()
            .coreComponent(ConverterApp.coreComponent(requireContext()))
            .converterModule(ConverterModule(this))
            .build()
            .inject(this)
    }
}