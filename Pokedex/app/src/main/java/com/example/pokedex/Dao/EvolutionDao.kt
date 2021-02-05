package com.example.pokedex.Dao

import androidx.room.*
import com.example.pokedex.Models.DetailsPoke
import com.example.pokedex.Models.EvolutionPokemon

@Dao
interface EvolutionDao {
    @Query("SELECT * FROM evolution")
    fun getAll(): List<EvolutionPokemon>


    @Query("SELECT * FROM evolution WHERE name LIKE :name ")
    fun findEvolutionByName(name : String): EvolutionPokemon

    @Query("SELECT * FROM evolution WHERE id = :id ")
    fun findEvolutionByID(id : Int): EvolutionPokemon

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvolution(vararg evolution: EvolutionPokemon)

    @Update
    fun updateEvolution(vararg evolution: EvolutionPokemon)

    @Delete
    fun delete(evolution : EvolutionPokemon)
}