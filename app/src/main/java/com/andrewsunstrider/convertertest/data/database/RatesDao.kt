package com.andrewsunstrider.convertertest.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/**
 * The data access object for the [Rate] class.
 *
 * @see Dao
 */
@Dao
interface RatesDao {

    @Query("SELECT * FROM rate ORDER by currency")
    suspend fun getAllCurrencies(): List<Rate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(rates: List<Rate>)

    @Query("DELETE FROM rate")
    suspend fun deleteAllCurrencies()
}