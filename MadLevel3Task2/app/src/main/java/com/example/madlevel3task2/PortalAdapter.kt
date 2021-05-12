package com.example.madlevel3task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel3task2.databinding.RecyclerViewItemBinding

class PortalAdapter(private val portals: List<Portal>) :
        RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    var onItemClick: ((Portal) -> Unit)? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(portals[adapterPosition])
            }
        }

        val binding = RecyclerViewItemBinding.bind(itemView)

        // Used to populate the widgets with data from the Reminder object.
        // Sets the text from the TextView to the text from the Reminder String.
        fun databind(portal: Portal) {
            binding.tvPortalTitle.text = portal.title
            binding.tvPortalURL.text = portal.url
        }
    }

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        )
    }

    /**
     * Returns the size of the list.
     */
    override fun getItemCount(): Int {
        return portals.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(portals[position])
    }
}