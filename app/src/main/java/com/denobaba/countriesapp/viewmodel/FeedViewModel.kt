package com.denobaba.countriesapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denobaba.countriesapp.model.countries
import com.denobaba.countriesapp.services.CountryApiService
import com.denobaba.countriesapp.services.CountryDatabase
import com.denobaba.countriesapp.util.CostomShredPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application): BaseViewModel(application) {

    private val countryApiService =  CountryApiService()
    private val disposible = CompositeDisposable() //kullan at yüzünden hafızayı verimli tutuyoruz

    private var customPreferences = CostomShredPreferences(getApplication())
    private var refreshtime = 10 * 60 * 1000 * 1000 * 1000L

    val countries1 = MutableLiveData<List<countries>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()

    fun refreshdata(){
        val updatetime = customPreferences.getTime()
        if (updatetime != null && updatetime !=0L && System.nanoTime()- updatetime < refreshtime){
            getdatafromsqlite()

        }else{
            getdatfromapi()

        }

    }

    private fun getdatafromsqlite(){
        launch {
            val countriess = CountryDatabase(getApplication()).countryDao().getAllCountries()
            showcountries(countriess)
            Toast.makeText(getApplication(),"countries from sqlite",Toast.LENGTH_LONG).show()

        }
    }

    fun getdatfromapi(){
        loading.value= true
        disposible.add(countryApiService.GetData().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<countries>>(){
                override fun onSuccess(t: List<countries>) {
                    Storeinsqlite(t)
                    Toast.makeText(getApplication(),"countries from api",Toast.LENGTH_LONG).show()


                }

                override fun onError(e: Throwable) {
                    loading.value = true
                    error.value = true
                    e.printStackTrace()
                }

            })
        )

    }

    private fun showcountries(t: List<countries>){
        countries1.value = t
        loading.value = false
        error.value = false

    }

    private fun Storeinsqlite(list: List<countries>){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val ListLong = dao.insertAll(*list.toTypedArray())//tek tek hale getiriyor listedeki verileri
            var i = 0
            while(i< list.size){
                list[i].uuid = ListLong[i].toInt()
                i= i+1
            }
            showcountries(list)

        }

        customPreferences.savetime(System.nanoTime())


    }





}