package com.denobaba.countriesapp.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.denobaba.countriesapp.model.countries

@Dao
interface CountryDao {

    @Insert
    suspend fun insertAll(vararg Countries: countries): List<Long>
    //Insert -> Database erişim
    //suspend -> coroutine, pause & resume
    //vararg -> modeldeki herbir veriyi almak için
    //List<Long> primary keyi alıyor

    @Query("SELECT * FROM countries")
    suspend fun getAllCountries(): List<countries>

    @Query("SELECT * FROM countries WHERE uuid = :countryId")
    suspend fun getCountry(countryId: Int): countries

    @Query("DELETE FROM countries")
    suspend fun deleteAllCountries(): Int

}
