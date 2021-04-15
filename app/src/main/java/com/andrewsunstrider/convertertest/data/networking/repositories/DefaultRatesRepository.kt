package com.andrewsunstrider.convertertest.data.networking.repositories

import com.andrewsunstrider.convertertest.data.networking.mappers.toEntity
import com.andrewsunstrider.convertertest.data.networking.models.RatesEntity
import com.andrewsunstrider.convertertest.data.networking.services.ConverterService
import com.andrewsunstrider.convertertest.domain.repositories.RatesRepository


class DefaultRatesRepository(
    private val service: ConverterService
) : RatesRepository {
    override suspend fun getRates(): RatesEntity = service.getLatest().toEntity()
}