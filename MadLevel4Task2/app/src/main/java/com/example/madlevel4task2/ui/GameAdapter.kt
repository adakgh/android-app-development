package com.example.madlevel4task2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.model.Game
import com.example.madlevel4task2.R
import com.example.madlevel4task2.databinding.ItemGameBinding

class GameAdapter(private val games: List<Game>) :
    RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemGameBinding.bind(itemView)

        // Used to populate the widgets with data from the Game object.
        // Sets the text from the TextView to the text from the Game String.
        fun databind(game: Game) {
            binding.tvResult.text = game.result
            binding.tvDate.text = game.date.toString()
            when (game.computer_move) {
                "rock" -> binding.ivComputer.setImageResource(R.drawable.rock)
                "paper" -> binding.ivComputer.setImageResource(R.drawable.paper)
                "scissors" -> binding.ivComputer.setImageResource(R.drawable.scissors)
            }
            when (game.player_move) {
                "rock" -> binding.ivYou.setImageResource(R.drawable.rock)
                "paper" -> binding.ivYou.setImageResource(R.drawable.paper)
                "scissors" -> binding.ivYou.setImageResource(R.drawable.scissors)
            }
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