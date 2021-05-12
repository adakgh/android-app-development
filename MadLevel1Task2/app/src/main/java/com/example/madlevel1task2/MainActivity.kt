package com.example.madlevel1task2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.madlevel1task2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Add onClickListeners to the submit button.
        binding.submitButton.setOnClickListener {
            onSubmitClick()
        }
    }

    /**
     * Checks if the answer is correct.
     */
    private fun onSubmitClick() {
        val firstAnswer = binding.row13.text.toString()
        val secondAnswer = binding.row23.text.toString()
        val thirdAnswer = binding.row33.text.toString()
        val fourthAnswer = binding.row43.text.toString()

        // When the answer is correct then display a correct message using a toast message.
        // Otherwise display an incorrect message.
        if ((firstAnswer == getString(R.string.trueConjunction)) &&
                (secondAnswer == getString(R.string.falseConjunction)) &&
                (thirdAnswer == getString(R.string.falseConjunction)) &&
                (fourthAnswer == getString(R.string.falseConjunction))) {
            return onAnswerCorrect()
        } else if (firstAnswer != getString(R.string.trueConjunction)) {
            counter++
            onAnswerIncorrect()
        } else if (secondAnswer != getString(R.string.trueConjunction)) {
            counter++
            onAnswerIncorrect()
        } else if (thirdAnswer != getString(R.string.trueConjunction)) {
            counter++
            onAnswerIncorrect()
        } else if (fourthAnswer != getString(R.string.trueConjunction)) {
            counter++
            onAnswerIncorrect()
        } else {
            onAnswerIncorrect()
        }
    }

    /**
     * Displays a successful Toast message.
     */
    private fun onAnswerCorrect() {
        Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
    }

    /**
     * Displays an incorrect Toast message.
     */
    private fun onAnswerIncorrect() {
        Toast.makeText(this, getString(R.string.incorrect, counter), Toast.LENGTH_LONG).show()
    }

}