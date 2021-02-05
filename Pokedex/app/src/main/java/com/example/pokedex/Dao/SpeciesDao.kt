package com.example.pokedex.Dao

import androidx.room.*
import com.example.pokedex.Models.DetailsPoke
import com.example.pokedex.Models.Pokemon
import com.example.pokedex.Models.PokemonSpecies

@Dao
interface SpeciesDao {
    @Query("SELECT * FROM species")
    fun getAll(): PokemonSpecies

    @Query("SELECT * FROM species WHERE id = :id ")
    fun findSpeciesByID(id : Int): PokemonSpecies

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecies(vararg species: PokemonSpecies)

    @Update
    fun updateSpecies(vararg species: PokemonSpecies)

    @Delete
    fun delete(species: PokemonSpecies)
}