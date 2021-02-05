package com.example.pokedex.Dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedex.Models.DetailsPoke
import com.example.pokedex.Models.EvolutionPokemon
import com.example.pokedex.Models.Pokemon
import com.example.pokedex.Models.PokemonSpecies

@Database(entities = [Pokemon::class, DetailsPoke::class, PokemonSpecies::class, EvolutionPokemon::class], version = 6)
abstract class PokeDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun datailsDao(): DetailsDao
    abstract fun speciesDao(): SpeciesDao
    abstract fun evolutionDao(): EvolutionDao
}