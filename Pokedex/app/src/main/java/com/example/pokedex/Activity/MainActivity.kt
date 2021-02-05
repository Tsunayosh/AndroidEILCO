package com.example.pokedex.Activity


import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.pokedex.Adapter.PokedexAdapter
import com.example.pokedex.Api.PokemonService
import com.example.pokedex.Dao.PokeDatabase
import com.example.pokedex.Dao.PokemonDao
import com.example.pokedex.Models.AllPokemon
import com.example.pokedex.Models.Pokemon
import com.example.pokedex.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private var rvPoke: RecyclerView? = null
    private var mPokedexAdapter: PokedexAdapter? = null
    private var list: List<Pokemon>? = null
    lateinit var pokeDao : PokemonDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = Room.databaseBuilder(this, PokeDatabase::class.java, "poke-database").allowMainThreadQueries().build()
        pokeDao = db.pokemonDao()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val gen1 = findViewById<ImageView>(R.id.gen1)
        val gen2 = findViewById<ImageView>(R.id.gen2)
        val gen3 = findViewById<ImageView>(R.id.gen3)
        val gen4 = findViewById<ImageView>(R.id.gen4)
        val gen5 = findViewById<ImageView>(R.id.gen5)
        val gen6 = findViewById<ImageView>(R.id.gen6)
        val gen7 = findViewById<ImageView>(R.id.gen7)
        val gen8 = findViewById<ImageView>(R.id.gen8)
        toolbar.title = "Première génération"
        list = pokeDao.loadGen(151,0)

        rvPoke = findViewById(R.id.rvPokemon)
        //Log.d("taille liste", list.toString())
        mPokedexAdapter = PokedexAdapter(list,this)
        rvPoke!!.adapter = mPokedexAdapter
        rvPoke!!.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 3)
        rvPoke!!.layoutManager = layoutManager

        gen1.setOnClickListener {
            obtenirPokemon(151, 0)
            toolbar.title = "Première génération"
            gen1.setBackgroundResource(R.drawable.ball_gen)
            gen2.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen3.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen4.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen5.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen6.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen7.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen8.setBackgroundResource(R.drawable.ball_gen_non_selec)
        }

        gen2.setOnClickListener {
            obtenirPokemon(251, 152)
            toolbar.title = "Deuxième génération"
            gen1.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen2.setBackgroundResource(R.drawable.ball_gen)
            gen3.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen4.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen5.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen6.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen7.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen8.setBackgroundResource(R.drawable.ball_gen_non_selec)
        }
        gen3.setOnClickListener {
            obtenirPokemon(386, 252)
            toolbar.title = "Troisième génération"
            gen1.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen2.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen3.setBackgroundResource(R.drawable.ball_gen)
            gen4.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen5.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen6.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen7.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen8.setBackgroundResource(R.drawable.ball_gen_non_selec)
        }
        gen4.setOnClickListener {
            obtenirPokemon(493, 387)
            toolbar.title = "Quatrième génération"
            gen1.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen2.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen3.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen4.setBackgroundResource(R.drawable.ball_gen)
            gen5.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen6.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen7.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen8.setBackgroundResource(R.drawable.ball_gen_non_selec)
        }
        gen5.setOnClickListener {
            obtenirPokemon(649, 494)
            toolbar.title = "Cinquième génération"
            gen1.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen2.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen3.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen4.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen5.setBackgroundResource(R.drawable.ball_gen)
            gen6.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen7.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen8.setBackgroundResource(R.drawable.ball_gen_non_selec)
        }
        gen6.setOnClickListener {
            obtenirPokemon(721, 650)
            toolbar.title = "Sixième génération"
            gen1.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen2.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen3.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen4.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen5.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen6.setBackgroundResource(R.drawable.ball_gen)
            gen7.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen8.setBackgroundResource(R.drawable.ball_gen_non_selec)
        }
        gen7.setOnClickListener {
            obtenirPokemon(809, 722)
            toolbar.title = "Septième génération"
            gen1.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen2.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen3.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen4.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen5.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen6.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen7.setBackgroundResource(R.drawable.ball_gen)
            gen8.setBackgroundResource(R.drawable.ball_gen_non_selec)
        }
        gen8.setOnClickListener {
            obtenirPokemon(898, 810)
            toolbar.title = "Huitième génération"
            gen1.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen2.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen3.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen4.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen5.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen6.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen7.setBackgroundResource(R.drawable.ball_gen_non_selec)
            gen8.setBackgroundResource(R.drawable.ball_gen)
        }
    }

    private fun obtenirPokemon(limit: Int, offset: Int) {
        list = pokeDao.loadGen(limit, offset)
        if ((limit-offset) > list!!.size){
            val pokemonService: PokemonService = Retrofit.Builder()
                .baseUrl(PokemonService.URLList)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokemonService::class.java)
            pokemonService.getPokemon(limit, offset)?.enqueue(object : Callback<AllPokemon?>{
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
                    }
                }
                override fun onFailure(call: Call<AllPokemon?>?, t: Throwable?) {}
            })
        }

        rvPoke = findViewById(R.id.rvPokemon)
        mPokedexAdapter = PokedexAdapter(list,this@MainActivity)
        rvPoke?.adapter = mPokedexAdapter
        rvPoke?.hasFixedSize()
        val layoutManager = GridLayoutManager(this@MainActivity, 3)
        rvPoke?.layoutManager = layoutManager

    }
}

