package com.andrewsunstrider.convertertest.data.networking.mappers

import com.andrewsunstrider.convertertest.data.database.Rate
import com.andrewsunstrider.convertertest.data.networking.models.RatesEntity
import com.andrewsunstrider.convertertest.data.networking.responses.ConverterResponse

fun ConverterResponse.toEntity() = RatesEntity(
    rates = rates.toSortedMap()
)

fun List<Rate>.toEntity(): RatesEntity {
    val list = mutableMapOf<String, Float>()
    this.forEach { list[it.currency] = it.rate }
    return RatesEntity(rates = list.toSortedMap())
}

fun RatesEntity.toListDao(): List<Rate> {
    val list = mutableListOf<Rate>()
    this.rates.forEach { (currency, value) ->
        list.add(
            Rate(
                currency = currency,
                rate = value
            )
        )
    }

    return list.toList()
}