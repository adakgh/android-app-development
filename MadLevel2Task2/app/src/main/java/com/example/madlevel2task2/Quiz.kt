package com.example.madlevel2task2

data class Quiz(var question: String, var answers: Boolean) {
    companion object {
        val QUIZ_QUESTIONS = arrayOf(
            "A 'var' and 'val' are the same.",
            "Mobile Application Development grants 12 ECTS."
        )

        val QUIZ_ANSWERS = arrayOf(
            false,
            true
        )
    }
}
