package com.andrewsunstrider.convertertest.data.repositories

import com.andrewsunstrider.convertertest.data.mappers.toEntity
import com.andrewsunstrider.convertertest.data.models.RatesEntity
import com.andrewsunstrider.convertertest.data.services.ConverterService

class ConverterRepository(
    private val service: ConverterService,
) {

}