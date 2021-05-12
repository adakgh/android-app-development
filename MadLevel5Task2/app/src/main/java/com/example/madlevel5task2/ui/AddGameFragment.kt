package com.example.madlevel5task2.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task2.R
import com.example.madlevel5task2.databinding.FragmentAddGameBinding
import com.example.madlevel5task2.model.Game
import kotlinx.android.synthetic.main.fragment_add_game.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment() {
    private var _binding: FragmentAddGameBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GamesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        // Clicking on the floating action button saves the game
        binding.fab2.setOnClickListener {
            onAddGame()
            //"pop" the backstack, this means we destroy this fragment and go back to the BacklogFragment
            findNavController().popBackStack()
        }

        observeBacklog()
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        // Enable the back button, change the title of the page and hide the delete icon in the appbar
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        requireActivity().title = getString(R.string.app_name2)
        menu.findItem(R.id.action_delete).isVisible = false

        super.onCreateOptionsMenu(menu, menuInflater)
    }

    // Adding a game to the backlog
    private fun onAddGame() {
        viewModel.insertGame(
            Game(
                binding.etGameTitle.editText?.text.toString(),
                binding.etGamePlatformText.editText?.text.toString(),
                Date(
                    etGameReleaseYear.editText?.text.toString().toInt() - 1900,
                    etGameReleaseMonth.editText?.text.toString().toInt() - 1,
                    etGameReleaseDay.editText?.text.toString().toInt()
                )
            )
        )
    }

    private fun observeBacklog() {
        // Display the error message in a Toast widget.
        viewModel.error.observe(viewLifecycleOwner, { message ->
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        })

        // Navigates back to the backlog fragment when the addition of the game was successful
        viewModel.success.observe(viewLifecycleOwner, { success ->
            run {
                if (success) findNavController().navigate(R.id.action_AddGameFragment_to_GameFragment)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}