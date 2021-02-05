package com.example.pokedex.Adapter

import com.example.pokedex.R
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokedex.Activity.DetailActivity
import com.example.pokedex.Models.Pokemon


class PokedexAdapter(val list : List<Pokemon>?, private val mContext: Context) : RecyclerView.Adapter<PokedexAdapter.ViewHolder>() {
    //private val p: Pokemon? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imagePokemon: ImageView = view.findViewById(R.id.image_pokemon)


    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Log.d("check Adapter", list?.get(0)?.url.toString())
        val pokemon: Pokemon = list?.get(position)!!
        Glide.with(mContext)
            .load("https://pokeres.bastionbot.org/images/pokemon/" + pokemon.id.toString() + ".png")
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.imagePokemon)
        holder.imagePokemon.setOnClickListener { v ->
            val intent = Intent(v.context, DetailActivity::class.java)
            intent.putExtra("id", pokemon.id)
            v.context.startActivity(intent)
        }

    }

}