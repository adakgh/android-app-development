package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.*
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    // Wiring the adapter to the RecyclerView.
    private val quizzes = arrayListOf<Quiz>()
    private val quizAdapter = QuizAdapter(quizzes)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter.
        binding.rvQuizzes.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvQuizzes.adapter = quizAdapter
        binding.rvQuizzes.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

        // Populate the places list
        for (i in Quiz.QUIZ_QUESTIONS.indices) {
            quizzes.add(Quiz(Quiz.QUIZ_QUESTIONS[i], Quiz.QUIZ_ANSWERS[i]))
        }

        // Adapter gets reminded that the data set has changed.
        quizAdapter.notifyDataSetChanged()

        // Attach the ItemTouchHelper to the RecyclerView.
        createItemTouchHelper().attachToRecyclerView(binding.rvQuizzes)
    }

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {
        // Callback which is used to create the ItemTouch helper.
        // Item can be dragged left or right.
        val callback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            // Is Disabled (false) because we don't implement this functionality.
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // Find the position of the viewholder in the adapter.
                val position = viewHolder.adapterPosition

                // Check if the quiz is answered right.
                // If not, return a Snackbar.
                // Else, remove the question from the list.
                if (direction == ItemTouchHelper.LEFT && quizzes[position].answers ||
                    direction == ItemTouchHelper.RIGHT && !quizzes[position].answers) {
                    Snackbar.make(binding.rvQuizzes, R.string.wrong, Snackbar.LENGTH_SHORT).show()
                } else {
                    // Remove question from quizzes list.
                    quizzes.removeAt(position)
                    // Adapter gets reminded that the data set has changed.
                    quizAdapter.notifyDataSetChanged()
                }

                // Adapter gets reminded that the data set has changed.
                quizAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }
}