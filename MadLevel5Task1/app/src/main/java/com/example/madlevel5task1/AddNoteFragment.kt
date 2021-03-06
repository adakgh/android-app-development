package com.example.madlevel5task1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task1.databinding.FragmentAddNoteBinding
import kotlinx.android.synthetic.main.fragment_add_note.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddNoteFragment : Fragment() {
    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NotepadViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Add or edit a Note.
        binding.btnSave.setOnClickListener {
            saveNote()
        }

        observeNote()
    }

    private fun observeNote() {
        // Fill the text fields with the current text and title from the viewmodel.
        // Populate the title and note input fields.
        viewModel.note.observe(viewLifecycleOwner, Observer { note ->
            note?.let {
                binding.tilNoteTitle.editText?.setText(it.title)
                binding.tilNoteText.editText?.setText(it.text)
            }

        })

        // Display the error message in a Toast widget.
        viewModel.error.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.success.observe(viewLifecycleOwner, Observer { success ->
            //"pop" the backstack, so destroy this fragment and go back to the RemindersFragment.
            findNavController().popBackStack()
        })
    }

    // Save the Note with the user input gathered from the view.
    private fun saveNote() {
        viewModel.updateNote(
            binding.tilNoteTitle.editText?.text.toString(),
            binding.tilNoteText.editText?.text.toString()
        )
    }
}
