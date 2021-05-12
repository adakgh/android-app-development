package com.example.madlevel5task2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.databinding.ItemGameBinding
import com.example.madlevel5task2.model.Game
import java.text.SimpleDateFormat

class BacklogAdapter(private val games: List<Game>) :
    RecyclerView.Adapter<BacklogAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemGameBinding.bind(itemView)

        fun databind(game: Game) {
            val date = SimpleDateFormat("dd MMMM yyyy").format(game.releaseDate).toString()
            binding.tvGameTitle.text = game.title
            binding.tvGamePlatformText.text = game.platform
            binding.tvGameReleaseText.text =
                itemView.context.resources.getString(R.string.release, date)
        }
    }

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    /**
     * Returns the size of the list.
     */
    override fun getItemCount(): Int {
        return games.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }
}