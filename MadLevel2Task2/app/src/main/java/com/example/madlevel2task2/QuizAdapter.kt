package com.example.madlevel2task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ItemQuizBinding

class QuizAdapter (private val quizzes: List<Quiz>) : RecyclerView.Adapter<QuizAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemQuizBinding.bind(itemView)

        // Used to populate the widgets with data from the Quiz object.
        // Sets the text from the TextView to the text from the Quiz String.
        fun databind(quiz: Quiz) {
            binding.tvQuiz.text = quiz.question
        }
    }

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_quiz, parent, false))
    }

    /**
     * Returns the size of the list.
     */
    override fun getItemCount(): Int {
        return quizzes.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(quizzes[position])
    }

}