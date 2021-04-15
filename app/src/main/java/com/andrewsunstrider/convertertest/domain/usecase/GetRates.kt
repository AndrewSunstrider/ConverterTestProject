package com.andrewsunstrider.convertertest.domain.usecase

import com.andrewsunstrider.convertertest.data.networking.models.RatesEntity
import com.andrewsunstrider.convertertest.domain.repositories.CurrencyRepository
import javax.inject.Inject

class GetRates @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {
    suspend fun getRates(): RatesEntity = currencyRepository.getRatesLocal()
}