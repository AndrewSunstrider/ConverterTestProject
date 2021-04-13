package com.andrewsunstrider.convertertest.domain.usecase

import com.andrewsunstrider.convertertest.data.networking.models.RatesEntity
import com.andrewsunstrider.convertertest.domain.repositories.RatesRepository
import javax.inject.Inject

class GetRates @Inject constructor(
    private val repository: RatesRepository
) {
    suspend fun getRates(): RatesEntity = repository.getRates()
}