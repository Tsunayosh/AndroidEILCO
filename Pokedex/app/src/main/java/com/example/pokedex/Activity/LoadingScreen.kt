package com.example.pokedex.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Dao
import androidx.room.Room
import com.example.pokedex.Api.PokemonService
import com.example.pokedex.Dao.PokeDatabase
import com.example.pokedex.Dao.PokemonDao
import com.example.pokedex.Models.AllPokemon
import com.example.pokedex.Models.Pokemon
import com.example.pokedex.R
import kotlinx.android.synthetic.main.item_pokemon.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoadingScreen : AppCompatActivity() {
lateinit var pokeDao : PokemonDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        val db = Room.databaseBuilder(this, PokeDatabase::class.java, "poke-database").fallbackToDestructiveMigration().allowMainThreadQueries().build()
        pokeDao = db.pokemonDao()
        obtenirPokemon(151, 0)

    }

    private fun obtenirPokemon(limit: Int, offset: Int) {
        val pokemonService = Retrofit.Builder()
            .baseUrl(PokemonService.URLList)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonService::class.java)
        pokemonService.getPokemon(limit, offset)!!.enqueue(object : Callback<AllPokemon?> {
            override fun onResponse(call: Call<AllPokemon?>?, response: Response<AllPokemon?>) {
                if (response.isSuccessful) {
                    val pokemons: AllPokemon = response.body()!!
                    val iterator = pokemons.results?.iterator()
                    //Log.d("check poke", pokemons.results?.get(1)?.url.toString())
                    iterator?.forEach {
                        //Log.d("check poke2", it.url.toString())
                        val split: List<String> = it.url?.split("/")!!
                        //Log.d("check poke3", split.toString())
                        it.id = split[split.size - 2].toInt()
                        pokeDao.insertAll(it)
                    }

                    //Log.d("check name", pokeDao.findByID(1).name.toString())


                    val intent = Intent(this@LoadingScreen, MainActivity::class.java)
                    intent.putExtra("gen1", pokemons)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<AllPokemon?>?, t: Throwable?) {Log.d("check poke", "Fail")}
        })
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}