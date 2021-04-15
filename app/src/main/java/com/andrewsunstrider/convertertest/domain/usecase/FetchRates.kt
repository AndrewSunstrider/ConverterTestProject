package com.andrewsunstrider.convertertest.domain.usecase

import com.andrewsunstrider.convertertest.domain.repositories.CurrencyRepository
import javax.inject.Inject

class FetchRates @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {

    suspend fun fetchRates() {
        currencyRepository.fetchRates(currencyRepository.getRatesRemote())
    }
}