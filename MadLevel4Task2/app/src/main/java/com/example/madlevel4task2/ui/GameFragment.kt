package com.example.madlevel4task2.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.madlevel4task2.model.Game
import com.example.madlevel4task2.repository.GameRepository
import com.example.madlevel4task2.R
import com.example.madlevel4task2.databinding.FragmentGameBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    private val choices = arrayListOf("rock", "paper", "scissors")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        gameRepository = GameRepository(requireContext())

        // Setting the player's choice.
        binding.rock.setOnClickListener { playerChoice("rock") }
        binding.paper.setOnClickListener { playerChoice("paper") }
        binding.scissors.setOnClickListener { playerChoice("scissors") }
    }


    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        requireActivity().title = getString(R.string.app_name)
        menu.findItem(R.id.action_delete).isVisible = false

        super.onCreateOptionsMenu(menu, menuInflater)
    }

    /**
     * Save the game in the history after you played.
     */
    private fun addGame(computerChoice: String, playerChoice: String, result: String) {
        mainScope.launch {
            val game = Game(
                date = Date().toString(),
                computer_move = computerChoice,
                player_move = playerChoice,
                result = result
            )

            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }
        }
    }

    /**
     * Put the right image with the player's choice and generates the result.
     */
    private fun playerChoice(item: String) {
        if (item == "rock") {
            binding.ivYou.setImageResource(R.drawable.rock)
        } else if (item == "paper") {
            binding.ivYou.setImageResource(R.drawable.paper)
        } else if (item == "scissors") {
            binding.ivYou.setImageResource(R.drawable.scissors)
        }

        calculateResult(randomComputerChoice(), item)
    }

    /**
     * Generate the computer's random choice.
     */
    private fun randomComputerChoice(): String {
        val randomChoice = choices.random()

        if (randomChoice == "rock") {
            binding.ivComputer.setImageResource(R.drawable.rock)
        } else if (randomChoice == "paper") {
            binding.ivComputer.setImageResource(R.drawable.paper)
        } else if (randomChoice == "scissors") {
            binding.ivComputer.setImageResource(R.drawable.scissors)
        }

        return randomChoice
    }

    /**
     * Calculate who won the game and binding the message.
     */
    private fun calculateResult(computerChoice: String, playerChoice: String) {
        if (playerChoice == computerChoice) {
            binding.tvResult.setText(R.string.draw)
            return addGame(computerChoice, playerChoice, "Draw")
        } else if ((playerChoice == "scissors" && computerChoice == "rock") || (playerChoice == "rock"
                    && computerChoice == "paper") || (playerChoice == "paper" && computerChoice == "scissors")
        ) {
            binding.tvResult.setText(R.string.lose)
            return addGame(computerChoice, playerChoice, "Computer wins!")
        } else if ((computerChoice == "scissors" && playerChoice == "rock") || (computerChoice == "rock"
                    && playerChoice == "paper") || (computerChoice == "paper" && playerChoice == "scissors")
        ) {
            binding.tvResult.setText(R.string.win)
            return addGame(computerChoice, playerChoice, "You win!")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}