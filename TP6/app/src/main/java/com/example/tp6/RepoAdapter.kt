package com.example.td6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp6.R
import com.example.tp6.Repo

class RepoAdapter(val mRepo : Repos?, val context: Context) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val repoView: View = inflater.inflate(R.layout.activity_result, parent, false)
        return ViewHolder(repoView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo : Repo? = mRepo?.items?.get(position)
        val repo_id = holder.repo_id
        repo_id.text = repo?.id.toString()
        val repo_name = holder.repo_name
        repo_id.text = repo?.name.toString()
        val repo_fullname = holder.repo_fullname
        repo_id.text = repo?.full_name.toString()
        val repo_url = holder.repo_url
        repo_id.text = repo?.html_url.toString()

    }

    override fun getItemCount(): Int {
        if (mRepo != null) {
            return mRepo.items?.size!!
        } else
            return 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var repo_id: TextView
        var repo_name: TextView
        var repo_fullname: TextView
        var repo_url: TextView

        init {
            repo_id = itemView.findViewById<View>(R.id.id_repo) as TextView
            repo_name = itemView.findViewById<View>(R.id.nom_repo) as TextView
            repo_fullname = itemView.findViewById<View>(R.id.fullname_repo) as TextView
            repo_url = itemView.findViewById<View>(R.id.url_repo) as TextView
        }
    }



}