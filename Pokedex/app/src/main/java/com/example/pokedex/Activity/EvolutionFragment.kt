package com.example.pokedex.Activity

import com.example.pokedex.R
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
import com.example.pokedex.Dao.DetailsDao
import com.example.pokedex.Dao.PokeDatabase
import com.example.pokedex.Dao.SpeciesDao
import com.example.pokedex.Models.DetailsPoke
import com.example.pokedex.Models.Pokemon
import com.example.pokedex.Models.PokemonSpecies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class EvolutionFragment : Fragment() {
    private var name: TextView? = null
    private var index : TextView? = null
    private var mapType: HashMap<String, String> = HashMap()
    private var back: ImageView? = null
    private var newId: String? = null
    private var number: TextView? = null
    private var img: ImageView? = null
    private var rvPoke: RecyclerView? = null
    private var mPokedexAdapter: PokedexAdapter? = null
    private var list: List<Pokemon>? = null
    private lateinit var detailsDao: DetailsDao
    private lateinit var speciesDao: SpeciesDao
    private var details : DetailsPoke? = null
    private var species : PokemonSpecies? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_evolution, container, false)
        val db = Room.databaseBuilder(requireContext(), PokeDatabase::class.java, "poke-database").allowMainThreadQueries().build()
        detailsDao = db.datailsDao()
        speciesDao = db.speciesDao()
        val id: Int? = this.arguments?.getString("id")?.toInt()
        details = detailsDao.findDetailsByID(id!!.toInt())
        species = speciesDao.findSpeciesByID(id.toInt())
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

        var nom = ""
        if (id < 494) {
            val splitName: List<String> = species!!.namesList?.split("/")!!
            nom = splitName[8]
        }else{
            val splitName: List<String> = species!!.namesList?.split("/")!!
            nom = splitName[6]
        }
        name?.text = nom

        //Log.d("taille liste", list.toString())
        mPokedexAdapter = PokedexAdapter(list,this)
        rvPoke!!.adapter = mPokedexAdapter
        rvPoke!!.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 3)
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
