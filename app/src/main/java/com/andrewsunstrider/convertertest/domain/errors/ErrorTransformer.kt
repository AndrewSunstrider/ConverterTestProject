package com.andrewsunstrider.convertertest.domain.errors

interface ErrorTransformer {

    suspend fun transform(incoming: Throwable): Throwable
}
