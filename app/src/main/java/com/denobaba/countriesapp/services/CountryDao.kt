package com.denobaba.countriesapp.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.denobaba.countriesapp.model.Countries

@Dao
interface CountryDao {

    @Insert
    suspend fun insertAll(vararg Countriess: Countries): List<Long>
    //Insert -> Database erişim
    //suspend -> coroutine, pause & resume
    //vararg -> modeldeki herbir veriyi almak için
    //List<Long> primary keyi alıyor

    @Query("SELECT * FROM Countries")
    suspend fun getAllCountries(): List<Countries>

    @Query("SELECT * FROM countries WHERE uuid = :countryId")
    suspend fun getCountry(countryId: Int): Countries

    @Query("DELETE FROM Countries")
    suspend fun deleteAllCountries(): Int



}
