package com.andrewsunstrider.convertertest.data.repositories

import com.andrewsunstrider.convertertest.data.database.RatesDao
import com.andrewsunstrider.convertertest.data.networking.mappers.toEntity
import com.andrewsunstrider.convertertest.data.networking.mappers.toListDao
import com.andrewsunstrider.convertertest.data.networking.models.RatesEntity
import com.andrewsunstrider.convertertest.data.networking.services.ConverterService
import com.andrewsunstrider.convertertest.domain.repositories.CurrencyRepository


class DefaultCurrencyRepository(
    private val service: ConverterService,
    private val rateDao: RatesDao
) : CurrencyRepository {

    override suspend fun getRatesRemote(): RatesEntity = service.getLatest().toEntity()

    override suspend fun getRatesLocal(): RatesEntity = rateDao.getAllCurrencies().toEntity()

    override suspend fun fetchRates(rates: RatesEntity) {
        rateDao.insertCurrencies(rates = rates.toListDao())
    }
}