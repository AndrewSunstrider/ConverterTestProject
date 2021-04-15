package com.andrewsunstrider.convertertest.domain.repositories

import com.andrewsunstrider.convertertest.data.networking.models.RatesEntity

interface CurrencyRepository {
    suspend fun getRatesRemote(): RatesEntity
    suspend fun getRatesLocal(): RatesEntity
    suspend fun fetchRates(rates: RatesEntity)
}