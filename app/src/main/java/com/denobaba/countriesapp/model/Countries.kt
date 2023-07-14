package com.denobaba.countriesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Countries(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val CountryName: String?,

    @ColumnInfo(name = "capital")
    @SerializedName("capital")

    val CountryCapital: String?,

    @ColumnInfo(name = "region")
    @SerializedName("region")

    val CountryRegion: String?,

    @ColumnInfo(name = "currency")
    @SerializedName("currency")

    val CountryCurrency: String?,

    @ColumnInfo(name = "flag")
    @SerializedName("flag")

    val CountryFlag: String?,
    @SerializedName("language")

    val CountryLanguage: String?
    ){
    @PrimaryKey(autoGenerate = true)

    var uuid : Int = 0

}