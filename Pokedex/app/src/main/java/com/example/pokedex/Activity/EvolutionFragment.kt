package com.example.pokedex.Activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.pokedex.Adapter.PokedexAdapter
import com.example.pokedex.Dao.*
import com.example.pokedex.Models.DetailsPoke
import com.example.pokedex.Models.EvolutionPokemon
import com.example.pokedex.Models.Pokemon
import com.example.pokedex.Models.PokemonSpecies
import com.example.pokedex.R


class EvolutionFragment : Fragment() {
    private var name: TextView? = null
    private var index : TextView? = null
    private var mapType: HashMap<String, String> = HashMap()
    private var back: ImageView? = null
    private var newId: String? = null
    private var img: ImageView? = null
    private var rvPoke: RecyclerView? = null
    private var mPokedexAdapter: PokedexAdapter? = null
    private var list: List<Pokemon>? = null
    private lateinit var detailsDao: DetailsDao
    private lateinit var speciesDao: SpeciesDao
    private lateinit var pokemonDao: PokemonDao
    private lateinit var evolutionDao : EvolutionDao
    private var details : DetailsPoke? = null
    private var species : PokemonSpecies? = null
    private var pokemon : Pokemon? = null
    private var evolution : EvolutionPokemon? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_evolution, container, false)
        val db = Room.databaseBuilder(requireContext(), PokeDatabase::class.java, "poke-database").allowMainThreadQueries().build()
        detailsDao = db.datailsDao()
        speciesDao = db.speciesDao()
        pokemonDao = db.pokemonDao()
        evolutionDao = db.evolutionDao()
        val id: Int? = this.arguments?.getString("id")?.toInt()
        details = detailsDao.findDetailsByID(id!!.toInt())
        species = speciesDao.findSpeciesByID(id.toInt())
        evolution = evolutionDao.findEvolutionByID(id.toInt())
        makeMapType()

        img = view.findViewById(R.id.myPoke_img_evolve) as ImageView
        index = view.findViewById(R.id.index_evolve)
        back = view.findViewById(R.id.header_evolve) as ImageView
        name = view.findViewById(R.id.name_evolve)
        rvPoke = view.findViewById(R.id.rvPokemon)



        Glide.with(this)
            .load("https://pokeres.bastionbot.org/images/pokemon/$id.png")
            .centerCrop()
            .into(img!!)
        back?.setBackgroundColor(Color.parseColor(mapType[details?.type1]))


        newId = when (id.toString().length) {
            1 -> {
                "#00$id"
            }
            2 -> {
                "#0$id"
            }
            3 -> {
                "#$id"
            }
            else -> {
                "#$id"
            }
        }
        index!!.text = newId

        val nom: String
        nom = if (id < 494) {
            val splitName: List<String> = species!!.namesList?.split("/")!!
            splitName[8]
        }else{
            val splitName: List<String> = species!!.namesList?.split("/")!!
            splitName[6]
        }
        name?.text = nom

        val tempEvolutionList = evolution?.familyList?.split("/")
        Log.e("Check taille", tempEvolutionList?.size.toString())
        val tempPokeMutableList = mutableListOf<Pokemon>()
        tempEvolutionList?.forEach {
            if (it != "") {
                pokemon = pokemonDao.findByName(it)
                Log.e("Check evo", pokemon!!.id.toString())
                tempPokeMutableList.add(pokemon!!)
            }
        }
        list = tempPokeMutableList

        //Log.d("taille liste", list.toString())
        mPokedexAdapter = PokedexAdapter(list,requireContext())
        rvPoke!!.adapter = mPokedexAdapter
        rvPoke!!.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(requireContext(), 3)
        rvPoke!!.layoutManager = layoutManager

        return view
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
