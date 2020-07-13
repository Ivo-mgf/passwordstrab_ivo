

package com.example.android.roomwordssample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PassListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<PassListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Pass>()

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
        val wordSiteView: TextView = itemView.findViewById(R.id.textViewSite)
        val wordDescricaoView: TextView = itemView.findViewById(R.id.textViewDescricao)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        holder.wordItemView.text = current.word
        holder.wordSiteView.text = current.site
        holder.wordDescricaoView.text = current.descricao
    }

    internal fun setWords(passes: List<Pass>) {
        this.words = passes
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size
}


