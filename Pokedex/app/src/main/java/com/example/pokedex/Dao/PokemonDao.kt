package com.example.pokedex.Dao

import androidx.room.*
import com.example.pokedex.Models.Pokemon

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun getAll(): List<Pokemon>

    @Query("SELECT * FROM pokemon WHERE id BETWEEN :offset AND :limit")
    fun loadGen(limit: Int, offset : Int): List<Pokemon>

    @Query("SELECT * FROM pokemon WHERE name LIKE :name ")
    fun findByName(name : String): Pokemon

    @Query("SELECT * FROM pokemon WHERE id = :id ")
    fun findByID(id : Int): Pokemon

    @Query("SELECT * FROM details WHERE id = :id ")
    fun findDetailsByID(id : Int): Pokemon

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg pokemon: Pokemon)

    @Update
    fun updatePoke(vararg pokemon: Pokemon)

    @Delete
    fun delete(pokemon: Pokemon)
}