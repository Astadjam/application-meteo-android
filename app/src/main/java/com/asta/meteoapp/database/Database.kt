package com.asta.meteoapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Weather::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun getWeatherRepository(): WeatherRepository
}