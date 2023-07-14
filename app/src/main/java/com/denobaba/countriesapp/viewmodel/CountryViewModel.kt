package com.denobaba.countriesapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denobaba.countriesapp.model.Countries
import com.denobaba.countriesapp.services.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application): BaseViewModel(application) {
    val countryLiveData = MutableLiveData<List<Countries>>()
    fun getdatafromroom(uuid: Int){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            val countries= dao.getCountry(uuid)
            countryLiveData.value = listOf(countries)


        }



    }
}