package com.andrewsunstrider.convertertest.data.services

import com.andrewsunstrider.convertertest.data.responses.ConverterResponse
import retrofit2.http.GET

interface ConverterService {
    @GET("/latest")
    suspend fun getLatest(): ConverterResponse
}