package com.example.pokedex.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
 class AllPokemon(var results: ArrayList<Pokemon>? = null) : Parcelable {

}