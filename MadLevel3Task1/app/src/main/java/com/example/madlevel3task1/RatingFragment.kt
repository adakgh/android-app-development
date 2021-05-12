package com.example.madlevel3task1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.madlevel3task1.databinding.FragmentRatingBinding
import com.example.madlevel3task1.databinding.FragmentStartBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RatingFragment : Fragment() {
    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRatingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToSummary.setOnClickListener {
            navigateToSummary()
        }

        showRandomAssessableGame()
    }

    /**
     * Show a randomized game name.
     */
    private fun showRandomAssessableGame() {
        val randomGame = listOf(
            "Red Dead Redemption 2", "Rocket League", "Shadow of the Tombraider"
        ).random()

        binding.txtGame.text = randomGame
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToSummary() {

        val args = Bundle()
        args.putFloat(ARG_GAME_RATING, binding.ratingBar.rating)
        args.putString(ARG_GAME_NAME, binding.txtGame.text.toString())

        findNavController().navigate(R.id.action_ratingFragment_to_summaryFragment, args)
    }
}