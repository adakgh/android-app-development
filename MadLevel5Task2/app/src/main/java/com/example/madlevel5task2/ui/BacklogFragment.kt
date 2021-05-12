package com.example.madlevel5task2.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.databinding.FragmentBacklogBinding
import com.example.madlevel5task2.model.Game
import com.example.madlevel5task2.repository.GamesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class BacklogFragment : Fragment() {
    private var _binding: FragmentBacklogBinding? = null
    private val binding get() = _binding!!

    private val games = arrayListOf<Game>()
    private val backlogAdapter = BacklogAdapter(games)

    private val viewModel: GamesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBacklogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        requireActivity().title = getString(R.string.app_name)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        // Clicking on the floating action button redirects to the add game fragment
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_GameFragment_to_AddGameFragment)
        }

        initViews()
        observeAddGameResult()
    }

    // Deleting the history of games when clicking the menu item
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                viewModel.deleteAllGames()
                observeAddGameResult()
                Toast.makeText(context, getString(R.string.deleted_games), Toast.LENGTH_SHORT).show()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    // Populate the Recycler View
    private fun initViews() {
        // Initialize the recycler view with a linear layout manager and adapter
        binding.rvGame.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvGame.adapter = backlogAdapter

        // Add ability to interact with the games
        createItemTouchHelper().attachToRecyclerView(binding.rvGame)
    }


    private fun createItemTouchHelper(): ItemTouchHelper {
        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete = games[position]

                viewModel.deleteGame(gameToDelete)
                observeAddGameResult()
                Toast.makeText(context, getString(R.string.deleted_game), Toast.LENGTH_SHORT)
                    .show()
            }
        }
        return ItemTouchHelper(callback)
    }

    // Update the games backlog
    private fun observeAddGameResult() {
        viewModel.games.observe(viewLifecycleOwner, Observer { games ->
            this@BacklogFragment.games.clear()
            this@BacklogFragment.games.addAll(games)
            backlogAdapter.notifyDataSetChanged()
        })
    }

    private fun deleteAllGames() {
        viewModel.deleteAllGames()
        observeAddGameResult()
        Toast.makeText(context, getString(R.string.deleted_games), Toast.LENGTH_SHORT).show()
    }

}