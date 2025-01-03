package com.asta.meteoapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherRepository {
    @Insert
    suspend fun addFavorite(weather: Weather):Long

    @Query("delete from weather where id = :id")
    suspend fun removeFavorite(id: Int)

    @Query("select * from weather")
    suspend fun getAllFavorite():List<Weather>
}