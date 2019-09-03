package com.einvopos.ratemevrmuseum.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.einvopos.ratemevrmuseum.R
import com.einvopos.ratemevrmuseum.data.models.Exhibit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.exhibit_item.view.*

/**
 * basic adapter to work with the recycler view
 */
class RecyclerViewExhibitsAdapter(private val exhibits: List<Exhibit>, private val listener: (Exhibit) -> Unit) : RecyclerView.Adapter<RecyclerViewExhibitsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.exhibit_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return exhibits.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(exhibits[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(exhibit: Exhibit, listener: (Exhibit) -> Unit)= with(itemView) {
            Picasso.get().load(exhibit.imgUrl).resize(100, 100).centerCrop().error(R.drawable.ic_launcher_background).into(image)
            title.text = exhibit.name
            description.text = exhibit.description
            setOnClickListener { listener(exhibit) }
        }
    }

}