package com.example.pokedex.Models

import android.os.Parcelable
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "pokemon")
@Parcelize
 class Pokemon : Parcelable{@PrimaryKey
    var id : Int = 0

    @ColumnInfo (name = "name") var name: String? = null
    @ColumnInfo (name = "url") var url: String? = null
}


