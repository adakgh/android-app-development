package com.example.madlevel5task1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class NotepadViewModel(application: Application) : AndroidViewModel(application) {

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val notepadRepository = NotepadRepository(application.applicationContext)

    // A note object of type LiveData which has a note from the repository.
    val note = notepadRepository.getNotepad()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    // Uses the isNoteValid() method to check if the note is valid. If the return type was true
    // then it uses the noteRepository to update the note with newNote.
    fun updateNote(title: String, text: String) {
        // If there is an existing note, take that id to update it instead of adding a new one.
        val newNote = Note(
            id = note.value?.id,
            title = title,
            lastUpdated = Date(),
            text = text
        )

        if (isNoteValid(newNote)) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    notepadRepository.updateNotepad(newNote)
                }
                success.value = true
            }
        }
    }

    // Validates if the note is valid according to our business rules. If one of those business
    // rules is violated then the value of the error object is changed to the error and the UI
    // will be triggered by that error.
    private fun isNoteValid(note: Note): Boolean {
        return when {
            note.title.isBlank() -> {
                error.value = "Title must not be empty"
                false
            }
            else -> true
        }
    }

}