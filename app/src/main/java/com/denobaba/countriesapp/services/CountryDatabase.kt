package com.denobaba.countriesapp.services

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.denobaba.countriesapp.model.countries

//abstract sınıflar soyut sınıflardır, normal bir classa extend edilerek kullanılabilir, fonksiyonlarıda override edilir

@Database(entities = arrayOf(countries::class), version = 1) //roomda değişiklik yaparsan versionu değiştir
abstract class CountryDatabase: RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object{  //companion object içinde yapıyoruz diger scopelerden ulaşalım
        @Volatile private var instance: CountryDatabase? = null //tek bir instance objesi oluşturuluyor

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: MakeDatabase(context).also {
                instance = it //instance varmı yokmu kontrol ediyoruz,varsa instance dönüyor yoksa makedatabase çağırıyoruz

            }
        }


        private fun MakeDatabase(context: Context) =  Room.databaseBuilder(context.applicationContext,CountryDatabase::class.java,
            "countrydatabase"
        ).build() //databasemizi oluşturuyoruz
    }

}