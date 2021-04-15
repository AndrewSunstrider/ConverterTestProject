package com.andrewsunstrider.convertertest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andrewsunstrider.convertertest.BuildConfig

/**
 * Currency room database storing the currency and rates information
 *
 * @see Database
 */
@Database(
    entities = [Rate::class],
    exportSchema = false,
    version = BuildConfig.DATABASE_VERSION
)
abstract class CurrencyDatabase : RoomDatabase() {

    /**
     * Get rates data access object.
     *
     * @return Rates dao.
     */
    abstract fun ratesDao(): RatesDao
}