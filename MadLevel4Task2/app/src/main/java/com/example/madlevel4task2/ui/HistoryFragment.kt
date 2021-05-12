package com.example.madlevel4task2.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.R
import com.example.madlevel4task2.databinding.FragmentHistoryBinding
import com.example.madlevel4task2.model.Game
import com.example.madlevel4task2.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    private val games = arrayListOf<Game>()
    private val gamesAdapter = GameAdapter(games)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        getGamesFromDatabase()
        gameRepository = GameRepository(requireContext())

        initRv()
    }

    /**
     * Changing toolbar title and setting icons to visible or not, and inflate the menu; this adds items to the action bar if it is present.
     */
    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        requireActivity().title = getString(R.string.history_menu_title)

        menu.findItem(R.id.action_history).isVisible = false
        menu.findItem(R.id.action_delete).isVisible = true

        super.onCreateOptionsMenu(menu, menuInflater)
    }

    /**
     * Deleting the history of games when clicking the menu item.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                true
            }
            R.id.action_delete -> {
                deleteAllGames()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    /**
     * Get all the games from the database using the repository, clear the current games list,
     * add the games from the database and notifies the adapter that the data set was changed.
     */
    private fun getGamesFromDatabase() {
        mainScope.launch {
            val gamesHistory = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }
            this@HistoryFragment.games.clear()
            this@HistoryFragment.games.addAll(gamesHistory)
            this@HistoryFragment.gamesAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Puts the game history in the Recycler View.
     */
    private fun initRv() {
        // Initialize the recycler view with a linear layout manager, adapter
        binding.rvGames.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = gamesAdapter
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    /**
     * Deleting all games from the history.
     */
    private fun deleteAllGames() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteAllGames()
            }
            getGamesFromDatabase()
        }

        Toast.makeText(context, getString(R.string.deleted), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}