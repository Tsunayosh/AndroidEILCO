package com.example.pokedex.Models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "evolution")
@Parcelize
class EvolutionPokemon : Parcelable{
    @PrimaryKey
    var id : Int = 0

    @ColumnInfo(name = "name")var name : String = ""
    @Ignore var family : Family? = null
    @ColumnInfo(name = "family") var familyList : String = ""
}
