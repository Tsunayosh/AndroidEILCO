package com.example.pokedex.Activity

import android.graphics.Color
import com.example.pokedex.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.pokedex.Api.PokemonService
import com.example.pokedex.Dao.DetailsDao
import com.example.pokedex.Dao.PokeDatabase
import com.example.pokedex.Dao.SpeciesDao
import com.example.pokedex.Models.DetailsPoke
import com.example.pokedex.Models.PokemonSpecies
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class DescriptionFragment : Fragment() {
    private var name: TextView? = null
    private var img: ImageView? = null
    private var number: TextView? = null
    private var height: TextView? = null
    private var weight: TextView? = null
    private var type1: TextView? = null
    private var type2: TextView? = null
    private var back: ImageView? = null
    private var mapType: HashMap<String, String> = HashMap()
    private var newId: String? = null
    private var flavor : TextView? = null
    private lateinit var detailsDao: DetailsDao
    private lateinit var speciesDao: SpeciesDao
    private var details : DetailsPoke? = null
    private var species : PokemonSpecies? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_description, container, false)
        val db = Room.databaseBuilder(requireContext(), PokeDatabase::class.java, "poke-database").allowMainThreadQueries().build()
        detailsDao = db.datailsDao()
        speciesDao = db.speciesDao()
        val id: Int? = this.arguments?.getString("id")?.toInt()
        details = detailsDao.findDetailsByID(id!!)
        Log.d("check type", details?.height.toString())
        species = speciesDao.findSpeciesByID(id)
        makeMapType()

        name = view.findViewById(R.id.name) as TextView
        back = view.findViewById(R.id.header) as ImageView
        type1 = view.findViewById(R.id.type1)  as TextView
        type2 = view.findViewById(R.id.type2)
        number = view.findViewById(R.id.index)
        img = view.findViewById(R.id.myPoke_img) as ImageView
        height = view.findViewById(R.id.taille)
        weight = view.findViewById(R.id.weight)
        flavor = view.findViewById(R.id.description)

        val tempHeight = details?.height.toString() + " dm"
        val tempWeight = details?.weight.toString() + " hg"
        height?.text = tempHeight
        weight?.text = tempWeight
        type1?.text = details?.type1?.capitalize()
        Log.d("check type", type1?.text.toString())
        if (details?.type2 != null) {
            type2?.text = details?.type2
        }
        var text = ""
        var nom = ""
        if (id < 494) {
            val splitName: List<String> = species!!.namesList?.split("/")!!
            nom = splitName[8]
        }else{
            val splitName: List<String> = species!!.namesList?.split("/")!!
            nom = splitName[6]
        }

        if (id < 650){
            val splitText: List<String> = species!!.flavorText?.split("/")!!
            val splitText2: List<String> = splitText[1].split("\n")
            splitText2.forEach {
                text = text + it + " "
            }
        }else {
            if (id < 722) {
                val splitText: List<String> = species!!.flavorText?.split("/")!!
                val splitText2: List<String> = splitText[6].split("\n")
                splitText2.forEach {
                    text = text + it + " "
                }
            } else {
                if (id < 834) {
                    val splitText: List<String> = species!!.flavorText?.split("/")!!
                    val splitText2: List<String> = splitText[7].split("\n")
                    splitText2.forEach {
                        text = text + it + " "
                    }
                } else {
                    val splitText: List<String> = species!!.flavorText?.split("/")!!
                    val splitText2: List<String> = splitText[15].split("\n")
                    splitText2.forEach {
                        text = text + it + " "
                    }
                }
            }
        }

        Log.d("check hashtype", details?.type1.toString())
        back?.setBackgroundColor(Color.parseColor(mapType[details?.type1]))
        name?.text = nom
        flavor?.text = text

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

        Glide.with(this)
            .load("https://pokeres.bastionbot.org/images/pokemon/$id.png")
            .centerCrop()
            .into(img!!)
        number!!.text = newId
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