package com.andrewsunstrider.convertertest.domain.repositories

import com.andrewsunstrider.convertertest.data.networking.models.RatesEntity

interface RatesRepository {
    suspend fun getRates(): RatesEntity
}