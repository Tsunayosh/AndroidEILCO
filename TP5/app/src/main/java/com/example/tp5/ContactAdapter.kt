package com.example.tp5

import android.content.Context
import android.icu.number.NumberFormatter.with
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop


class ContactAdapter(val mContacts : List<Contact>, val context: Context):RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_contact, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("check", mContacts.size.toString())
        val contact = mContacts[position]
        val firstNameTextView = holder.firstNameTextView
        firstNameTextView.text = contact.prenom
        val lastNameTextView = holder.lastNameTextView
        lastNameTextView.text = contact.nom
        val imageView = holder.image
        Glide.with(context).load(contact.imageURL).into(imageView)

    }

    override fun getItemCount(): Int {
        return mContacts.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var firstNameTextView : TextView = itemView.findViewById(R.id.prenom) as TextView
        var lastNameTextView : TextView = itemView.findViewById(R.id.nom) as TextView
        var image : ImageView = itemView.findViewById(R.id.imageURL) as ImageView
    }
}