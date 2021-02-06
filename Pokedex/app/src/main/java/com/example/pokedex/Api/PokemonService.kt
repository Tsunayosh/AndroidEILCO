package com.example.pokedex.Api

import androidx.room.Dao
import com.example.pokedex.Models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface PokemonService {
    @GET("pokemon/")
    fun getPokemon(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<AllPokemon?>?

    @GET("pokemon/{pokemonName}")
    fun getPokemonByName(@Path("pokemonName") name: String): Call<Pokemon?>?

    @GET("pokemon/{pokemonID}")
    fun getDetails(@Path("pokemonID") id: String): Call<DetailsPoke?>?

    @GET("pokemon-species/{pokemonID}")
    fun getPokemonSpecies(@Path("pokemonID") id: Int): Call<PokemonSpecies?>?

    @GET("pokemon/{pokemonID}")
    fun getEvolutions(@Path("pokemonID") id: String) : Call<List<EvolutionPokemon?>?>

    companion object {
        const val URLList = "https://pokeapi.co/api/v2/"
        const val URLEvo = "https://pokeapi.glitch.me/v1/"
    }
}