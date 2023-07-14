package com.denobaba.countriesapp.services

import com.denobaba.countriesapp.model.Countries
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    //GET, POST
    //VERİLERİ ALIRKEN GET, DÜZENLERKEN POST KULLAN
    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    //BASE_URL = https://raw.githubusercontent.com/
    //EXT = atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries():Single<List<Countries>>
    //veriyi anlık olarak güncellemen gerekiyorsa Observable kullan, bir defalıgına alacaksan single kullan

}