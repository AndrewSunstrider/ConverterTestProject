package com.andrewsunstrider.convertertest.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity represents a rate for currency
 * info required for display on screen.
 */
@Entity(tableName = "rate")
data class Rate(
    @PrimaryKey val currency: String,
    @ColumnInfo(name = "value") val rate: Float
)