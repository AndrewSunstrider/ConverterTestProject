package com.andrewsunstrider.convertertest.data.networking.mappers

import com.andrewsunstrider.convertertest.data.networking.models.RatesEntity
import com.andrewsunstrider.convertertest.data.networking.responses.ConverterResponse

fun ConverterResponse.toEntity() = RatesEntity(
    rates = rates
)