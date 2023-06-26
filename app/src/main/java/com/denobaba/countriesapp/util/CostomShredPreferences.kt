package com.denobaba.countriesapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class CostomShredPreferences {

    companion object{

        private val priferencestime= "preferencestime"
        private var sharedPreferences: SharedPreferences?= null
        @Volatile private var instance: CostomShredPreferences?= null
        private val lock=  Any()


        operator fun invoke(context: Context) : CostomShredPreferences = instance?: synchronized(lock){
            instance?: makecostomsharedpreferences(context).also {
                instance = it
            }
        }
        private fun makecostomsharedpreferences(context: Context): CostomShredPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CostomShredPreferences()
        }

    }

    fun savetime(time :Long){
        sharedPreferences?.edit(commit = true){
            putLong(priferencestime,time)
        }
    }

    fun getTime() = sharedPreferences?.getLong(priferencestime,0)


}