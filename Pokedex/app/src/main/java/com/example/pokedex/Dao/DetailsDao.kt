package com.example.pokedex.Dao

import androidx.room.*
import com.example.pokedex.Models.DetailsPoke
import com.example.pokedex.Models.Pokemon

@Dao
interface DetailsDao {
    @Query("SELECT * FROM details")
    fun getAll(): DetailsPoke


    @Query("SELECT * FROM details WHERE name LIKE :name ")
    fun findDetailsByName(name : String): DetailsPoke

    @Query("SELECT * FROM details WHERE id = :id ")
    fun findDetailsByID(id : Int): DetailsPoke

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetails(vararg details: DetailsPoke)

    @Update
    fun updateDetails(vararg details: DetailsPoke)

    @Delete
    fun delete(details : DetailsPoke)
}