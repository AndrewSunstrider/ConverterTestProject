package com.andrewsunstrider.convertertest.data.networking.responses

import com.google.gson.annotations.SerializedName

data class ConverterResponse(
    @SerializedName("rates")
    val rates: HashMap<String, Float>
)