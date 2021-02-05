package com.example.pokedex.Models

import androidx.room.*

@Entity(tableName = "species")
class PokemonSpecies {
    @PrimaryKey
    var id : Int = 0

    @Ignore var flavor_text_entries : List<FlavorText>? = emptyList()
    @ColumnInfo (name = "lang") var lang : String? = null
    @ColumnInfo (name = "desc") var flavorText : String? = null
    @Ignore  var names : List<Name>? = null
    @ColumnInfo (name = "noms") var namesList : String? = null
    //@ColumnInfo (name = "variete") var varieties : List<Variete> = emptyList()
}
