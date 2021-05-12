package com.example.madlevel5task1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task1.databinding.FragmentNotepadBinding
import kotlinx.android.synthetic.main.fragment_notepad.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NoteFragment : Fragment() {
    private var _binding: FragmentNotepadBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NotepadViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNotepadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAddNoteResult()
    }

    // Initialize the viewmodel and set up the observers. The Note LiveData object is observed
    // and whenever it changes the textviews title, last updated and text will be updated.
    private fun observeAddNoteResult() {
        viewModel.note.observe(viewLifecycleOwner, Observer{ note ->
            note?.let {
                binding.tvNoteTitle.text = it.title
                binding.tvLastUpdated.text = getString(R.string.last_updated, it.lastUpdated.toString())
                binding.tvNoteText.text = it.text
            }
        })
    }
}