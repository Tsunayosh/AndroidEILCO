package com.example.pokedex.Activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.room.Room
import com.example.pokedex.Api.PokemonService
import com.example.pokedex.Dao.*
import com.example.pokedex.Models.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.pokedex.R
import kotlinx.coroutines.android.awaitFrame
import retrofit2.Callback
import retrofit2.Response


class DetailActivity : AppCompatActivity() {
    private var bottomNav: BottomNavigationView? = null
    val fragment1: Fragment = DescriptionFragment()
    val fragment2: Fragment = EvolutionFragment()
    val fm: FragmentManager = supportFragmentManager
    var active: Fragment = fragment1
    private var mapType: HashMap<String, String> = HashMap()
    lateinit var detailsDao: DetailsDao
    lateinit var speciesDao: SpeciesDao
    lateinit var pokemonDao: PokemonDao
    lateinit var evolutionDao: EvolutionDao
    var details : DetailsPoke? = null
    private var species : PokemonSpecies? = null
    var pokemon : Pokemon? = null
    private var evolution : EvolutionPokemon? = null
    var reponseDetailsDone : Boolean = false
    var reponseSpeciesDone : Boolean = false
    var reponseEvolutionDone : Boolean = false
    var comptAttente = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val db = Room.databaseBuilder(this, PokeDatabase::class.java, "poke-database")
            .allowMainThreadQueries().build()
        detailsDao = db.datailsDao()
        speciesDao = db.speciesDao()
        pokemonDao = db.pokemonDao()
        evolutionDao = db.evolutionDao()
        bottomNav = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        bottomNav!!.setOnNavigationItemSelectedListener(listener)
        val pokeIntent = intent.extras
        val pokeId = pokeIntent!!.getInt("id")
        evolution = chercherEvolution(id = pokeId)
        species = chercherSpecies(id = pokeId)
        details = chercherDetails(id = pokeId)
    }

    private val listener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.nav_description -> {
                        fm.beginTransaction().hide(active).show(fragment1).commit()
                        active = fragment1
                        return true
                    }
                    R.id.nav_evolve -> {
                        fm.beginTransaction().hide(active).show(fragment2).commit()
                        active = fragment2
                        return true
                    }
                }
                return false
            }
        }

    private fun pokeDetail(id: Int) {
        details = detailsDao.findDetailsByID(id)
        species = speciesDao.findSpeciesByID(id)
        makeMapType()
        bottomNav = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        bottomNav!!.setBackgroundColor(Color.parseColor(mapType[details!!.type1]))

        val bundle = Bundle()
        bundle.putString("id", details!!.id!!.toString())
        fragment1.arguments = bundle
        fragment2.arguments = bundle
        fm.beginTransaction().add(R.id.fragment_content, fragment1).commit()
        fm.beginTransaction().add(R.id.fragment_content, fragment2, "2").hide(fragment2)
            .commit()
    }


    private fun chercherDetails(id: Int): DetailsPoke {
        val pokemonService: PokemonService = Retrofit.Builder()
            .baseUrl(PokemonService.URLList)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonService::class.java)
        pokemonService.getDetails(id.toString())?.enqueue(object : Callback<DetailsPoke?> {
            override fun onResponse(call: Call<DetailsPoke?>?, response: Response<DetailsPoke?>) {
                if (response.isSuccessful) {
                    val detailsPoke: DetailsPoke = response.body()!!
//                    Log.e("check Types", detailsPoke.types[0].type?.name.toString())
                    detailsPoke.type1 = detailsPoke.types[0].type?.name
                    if (detailsPoke.types.size > 1) {
                        detailsPoke.type2 = detailsPoke.types[1].type?.name
                    }
//                    Log.e("id",detailsPoke.id.toString())
//                    Log.e("type1",detailsPoke.type1.toString())
//                    Log.e("type2",detailsPoke.type2.toString())
//                    Log.e("height",detailsPoke.height.toString())
//                    Log.e("weight",detailsPoke.weight.toString())
//                    Log.e("name",detailsPoke.name.toString())

                    detailsDao.insertDetails(detailsPoke)

                    details = detailsDao.findDetailsByID(id)
                    reponseDetailsDone = true
                    Log.e("Check in table", details?.type1.toString())
                    if(reponseDetailsDone && reponseSpeciesDone && reponseEvolutionDone){
                        pokeDetail(id)
                    }

                }
                Log.e("Check sortie response", "sortie")

            }
            override fun onFailure(call: Call<DetailsPoke?>?, t: Throwable?) {
                Log.e("failure","failure")

            }
        })
        Log.e("sortie detail","failure")
        return detailsDao.findDetailsByID(id)
    }

    private fun chercherSpecies(id : Int) : PokemonSpecies{
        val speciesService: PokemonService = Retrofit.Builder()
            .baseUrl(PokemonService.URLList)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonService::class.java)
        //Log.e("check ChercherSpecies", "Entrer chercher species")

        speciesService.getPokemonSpecies(id)?.enqueue(object : Callback<PokemonSpecies?> {
            override fun onResponse(
                call: Call<PokemonSpecies?>,
                response: Response<PokemonSpecies?>
            ) {
                Log.e("check on response", "Entrer on response")

                if (response.isSuccessful) {
                    val speciesPoke: PokemonSpecies = response.body()!!
                    var tempText: String? = ""
                    //var tempLang: String? = ""
                    var tempNames: String? = ""

                    val iterator = speciesPoke.flavor_text_entries?.iterator()

                    iterator?.forEach {
                        tempText = tempText + (it.flavor_text.toString()) + "/"
                    }
                    val iteratorNames = speciesPoke.names?.iterator()
                    iteratorNames?.forEach {
                        if (it.name != null) {
                            tempNames = tempNames + (it.name.toString()) + "/"
                        }
                    }
                    speciesPoke.namesList = tempNames
                    //speciesPoke.lang = tempLang
                    speciesPoke.flavorText = tempText
                    //Log.e("Check pre Lang", tempLang.toString())
                    //Log.e("Check pre Flavor", tempText.toString())


                    speciesDao.insertSpecies(speciesPoke)
                    reponseSpeciesDone = true
                    if(reponseDetailsDone && reponseSpeciesDone && reponseEvolutionDone){
                        pokeDetail(id)
                    }
                }
            }
            override fun onFailure(call: Call<PokemonSpecies?>, t: Throwable) {
                //Log.e("check Failure", "Entrer Failure")
            }
        })
       // Log.e("sortie detail","failure Species")
        return speciesDao.findSpeciesByID(id)
    }


    private fun chercherEvolution(id: Int) : EvolutionPokemon{
        val evolutionService: PokemonService = Retrofit.Builder()
            .baseUrl(PokemonService.URLEvo)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonService::class.java)

        evolutionService.getEvolutions(id.toString()).enqueue(object : Callback<List<EvolutionPokemon?>?>{
            override fun onResponse(
                call: Call<List<EvolutionPokemon?>?>,
                response: Response<List<EvolutionPokemon?>?>
            ) {
                if (response.isSuccessful) {
                    Log.e("Check Evoution", "entre evo")

                    val evolutionPokemon : EvolutionPokemon = response.body()?.get(0)!!

                    var tempfamily = ""
                    val templist = mutableListOf("")
                    templist.remove("")
                    evolutionPokemon.family?.evolutionLine?.forEach {
                        val split : List<String> = it.split("/")
                        split. forEach { it1 ->
                            if(it1 != evolutionPokemon.name) {
                                templist.add(it1)
                            }
                        }
                    }
                    templist.forEach{
                        tempfamily = "$tempfamily$it/"
                        pokemon = pokemonDao.findByName(it)

                        if (pokemon == null){
                            chercherPokemon(it)
                            comptAttente ++
                        }
                    }
                    while (comptAttente != 0){
                        Thread.sleep(10)
                        Log.e("Nombre en attente", comptAttente.toString())
                    }
                    evolutionPokemon.familyList = tempfamily
                    evolutionDao.insertEvolution(evolutionPokemon)


                    Log.e("Check Evoution", "arriver fin evo")
                    reponseEvolutionDone = true
                    if(reponseDetailsDone && reponseSpeciesDone && reponseEvolutionDone){
                        pokeDetail(id)
                    }
                }
            }

            override fun onFailure(call: Call<List<EvolutionPokemon?>?>, t: Throwable) {
                Log.e("Check Evoution", "Fail evo")
                Log.e("Check evo fail", t.cause.toString())

            }


        })
        return evolutionDao.findEvolutionByID(id)
    }

    private fun chercherPokemon(name : String){
        val pokemonService: PokemonService = Retrofit.Builder()
            .baseUrl(PokemonService.URLList)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonService::class.java)
        pokemonService.getPokemonByName(name)?.enqueue(object : Callback<Pokemon?>{
            override fun onResponse(call: Call<Pokemon?>?, response: Response<Pokemon?>) {
                if (response.isSuccessful) {
                    Log.e("ENtrer chercher pokemon", "Entreeeeeeeeeeeeeeeeer")

                    val pokemon: Pokemon = response.body()!!
                    pokemonDao.insertAll(pokemon)
                    comptAttente--
                }
            }
            override fun onFailure(call: Call<Pokemon?>?, t: Throwable?) {
                Log.e("Failure chercher pokemon", "Faiiiiiiiiiiil")
            }
        })
    }

    private fun makeMapType() {
        mapType["bug"] = "#A8B820"
        mapType["dragon"] = "#7038F8"
        mapType["ice"] = "#98D8D8"
        mapType["fire"] = "#F08030"
        mapType["water"] = "#6890F0"
        mapType["grass"] = "#78C850"
        mapType["fighting"] = "#C03028"
        mapType["flying"] = "#A890F0"
        mapType["ghost"] = "#705898"
        mapType["ground"] = "#E0C068"
        mapType["rock"] = "#B8A038"
        mapType["psychic"] = "#F85888"
        mapType["poison"] = "#A040A0"
        mapType["normal"] = "#A8A878"
        mapType["electric"] = "#F8D030"
    }
}
