package com.andrewsunstrider.convertertest.features.rates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrewsunstrider.convertertest.R
import com.andrewsunstrider.convertertest.data.networking.models.RatesEntity
import kotlinx.android.synthetic.main.item_rate.view.*

class RatesAdapter(
    private val rates: RatesEntity,
    private val onEventClickListener: (rateKey: RatesEntity) -> Unit
) : RecyclerView.Adapter<RatesAdapter.RatesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rate, parent, false)
        return RatesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        holder.bind(rates, position)
    }

    override fun getItemCount(): Int = rates.rates.size

    class RatesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val selectedRate = view.selected_rate

        fun bind(ratesView: RatesEntity, position: Int) {
            selectedRate.text = ratesView.rates.map {
                it.key
            }[position]
        }
    }
}