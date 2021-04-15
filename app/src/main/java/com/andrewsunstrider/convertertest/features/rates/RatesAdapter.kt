package com.andrewsunstrider.convertertest.features.rates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrewsunstrider.convertertest.R
import com.andrewsunstrider.convertertest.data.networking.models.RatesEntity
import kotlinx.android.synthetic.main.item_rate.view.*


class RatesAdapter(
    //change to List<String>
    private val rates: RatesEntity
) : RecyclerView.Adapter<RatesAdapter.RatesViewHolder>() {

    private var lastSelectedRatePosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rate, parent, false)
        return RatesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        holder.bind(rates, position, lastSelectedRatePosition)
    }

    override fun getItemCount(): Int = rates.rates.size

    inner class RatesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val selectedRate = view.selected_rate
        private val selectButton = view.button_select

        @SuppressLint("NotifyDataSetChanged")
        fun bind(ratesView: RatesEntity, position: Int, selectedPosition: Int) {

            //try to do it in viewmodel/usecase
            selectedRate.text = ratesView.rates.map {
                it.key
            }[position]

            when {
                selectedPosition == 0 && position == 0 -> selectButton.isChecked = true
                selectedPosition == position -> selectButton.isChecked = true
                else -> selectButton.isChecked = false
            }

            selectButton.setOnClickListener {
                lastSelectedRatePosition = adapterPosition
                notifyDataSetChanged()
            }
        }

        fun getRate(): Pair<String, Float> {
            return rates.rates.map {
                it.toPair()
            }[lastSelectedRatePosition]
        }
    }
}