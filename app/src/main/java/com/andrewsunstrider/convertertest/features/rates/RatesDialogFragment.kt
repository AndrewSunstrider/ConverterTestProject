package com.andrewsunstrider.convertertest.features.rates

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrewsunstrider.convertertest.ConverterApp
import com.andrewsunstrider.convertertest.R
import com.andrewsunstrider.convertertest.data.networking.models.RatesEntity
import com.andrewsunstrider.convertertest.di.components.DaggerRatesComponent
import com.andrewsunstrider.convertertest.di.modules.RatesModule
import kotlinx.android.synthetic.main.fragment_rates_dialog.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class RatesDialogFragment : DialogFragment() {

    @Inject
    lateinit var viewModel: RatesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_rates_dialog, container, false)

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        initListeners()
        onInitDependencyInjection()

        lifecycleScope.launch {
            delay(300)
            viewModel.bind().collect { render(it) }
        }
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    private fun render(state: RatesDialogState) {
        when (state) {
            RatesDialogState.Idle -> launch()
            RatesDialogState.Launching -> { }
            is RatesDialogState.Success -> {
                showRates(state.value)
                Log.d("Success", "Success -> Auth Activity running.")
            }
            is RatesDialogState.Failed -> Log.e("Error", "Error -> ${state.reason}")
            else -> throw IllegalArgumentException("Unknown type for $state.")
        }
    }

    private fun launch() {
        viewModel.handleApplicationLaunch()
    }

    private fun initListeners() {
        button_cancel.setOnClickListener {
            dismiss()
        }
    }

    private fun showRates(rates: RatesEntity) {
        rv_rates.layoutManager = LinearLayoutManager(this.requireContext())
        rv_rates.adapter = RatesAdapter(rates) { /* click listener callback */ }
    }

    private fun onInitDependencyInjection() {
        DaggerRatesComponent
            .builder()
            .coreComponent(ConverterApp.coreComponent(requireContext()))
            .ratesModule(RatesModule(this))
            .build()
            .inject(this)
    }
}