package com.andrewsunstrider.convertertest.data.networking.services

import com.andrewsunstrider.convertertest.data.networking.responses.ConverterResponse
import retrofit2.http.GET

interface ConverterService {
    @GET("latest")
    suspend fun getLatest(): ConverterResponse
}