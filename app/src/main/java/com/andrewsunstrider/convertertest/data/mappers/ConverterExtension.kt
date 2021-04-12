package com.andrewsunstrider.convertertest.data.mappers

import com.andrewsunstrider.convertertest.data.models.RatesEntity
import com.andrewsunstrider.convertertest.data.responses.ConverterResponse

fun ConverterResponse.toEntity() = RatesEntity(
    rates = rates
)