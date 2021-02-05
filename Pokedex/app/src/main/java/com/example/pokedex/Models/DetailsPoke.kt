package com.example.pokedex.Models

import android.os.Parcelable
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull

@Entity(tableName = "details")
@Parcelize
class DetailsPoke : Parcelable {
    @PrimaryKey @NotNull
    var id : Int? = null

    @ColumnInfo(name = "name") var name : String? = null
    @ColumnInfo(name = "height") var height : Int? = null
    @ColumnInfo(name = "weight") var weight : Int? = null
    @Ignore var types : List<PokemonType> = emptyList()
    @ColumnInfo(name = "type1") var type1 : String? = null
    @ColumnInfo(name = "type2") var type2 : String? = null
}
