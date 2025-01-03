package com.asta.meteoapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class Weather(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val weatherdata: String
)
