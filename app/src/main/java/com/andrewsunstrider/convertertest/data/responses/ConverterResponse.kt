package com.andrewsunstrider.convertertest.data.responses

import com.google.gson.annotations.SerializedName

data class ConverterResponse(
    @SerializedName("result")
    val result: String,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("documentation")
    val documentation: String,
    @SerializedName("terms_of_use")
    val termsOfUse: String,
    @SerializedName("time_last_update_unix")
    val lastUpdateUnix: Int,
    @SerializedName("time_last_update_utc")
    val lastUpdateUtc: String,
    @SerializedName("time_next_update_unix")
    val nextUpdateUnix: Int,
    @SerializedName("time_next_update_utc")
    val nextUpdateUtc: String,
    @SerializedName("time_eol_unix")
    val timeEolUnix: Int,
    @SerializedName("base_code")
    val baseCode: String,
    @SerializedName("rates")
    val rates: HashMap<String, Float>
)