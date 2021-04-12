package com.andrewsunstrider.convertertest.domain.usecase

import com.andrewsunstrider.convertertest.data.responses.ConverterResponse
import com.andrewsunstrider.convertertest.data.services.ConverterService

class GetLatest(private val service: ConverterService) {
    suspend fun getChannels(): ConverterResponse = service.getLatest()
}