package com.example.madlevel1task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.madlevel1task1.databinding.ActivityHigherLowerBinding

class HigherLowerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHigherLowerBinding
    private var currentThrow: Int = 1
    private var lastThrow: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHigherLowerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    /**
     * Set the initial (UI) state of the game.
     * Add onClickListeners to the higher, lower and equal buttons.
     */
    private fun initViews() {
        updateUI()

        binding.lowerButton.setOnClickListener {
            onLowerClick()
        }
        binding.equalButton.setOnClickListener {
            onEqualClick()
        }
        binding.higherButton.setOnClickListener {
            onHigherClick()
        }
    }

    /**
     * Update the last throw text and the dice image resource drawable with the current throw.
     */
    private fun updateUI() {
        binding.throwView.text = getString(R.string.last_throw, lastThrow)

        when (currentThrow) {
            1 -> binding.diceView.setImageResource(R.drawable.dice1)
            2 -> binding.diceView.setImageResource(R.drawable.dice2)
            3 -> binding.diceView.setImageResource(R.drawable.dice3)
            4 -> binding.diceView.setImageResource(R.drawable.dice4)
            5 -> binding.diceView.setImageResource(R.drawable.dice5)
            6 -> binding.diceView.setImageResource(R.drawable.dice6)
        }
    }

    /**
     * Replaces the previous dice value with the current one and replaces the current dice with a new dice
     * with a random number between 1 and 6 (inclusive).
     */
    private fun rollDice() {
        lastThrow = currentThrow
        currentThrow = (1..6).random()
        updateUI()
    }

    /**
     * Calls [rollDice] and checks if the answer is correct.
     */
    private fun onHigherClick() {
        rollDice()

        return if (currentThrow > lastThrow)
            onAnswerCorrect()
        else
            onAnswerIncorrect()
    }

    /**
     * Calls [rollDice] and checks if the answer is correct.
     */
    private fun onLowerClick() {
        rollDice()

        return if (currentThrow < lastThrow)
            onAnswerCorrect()
        else
            onAnswerIncorrect()
    }

    /**
     * Calls [rollDice] and checks if the answer is correct.
     */
    private fun onEqualClick() {
        rollDice()

        return if (currentThrow == lastThrow)
            onAnswerCorrect()
        else
            onAnswerIncorrect()
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
        Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_LONG).show()
    }

}