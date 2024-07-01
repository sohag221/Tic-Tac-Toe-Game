package com.example.tictactoe

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoegame.R

import com.example.tictactoegame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val combinationList = listOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(2, 4, 6),
        intArrayOf(0, 4, 8)
    )
    private var boxPositions = IntArray(9) { 0 }
    private var playerTurn = 1
    private var totalSelectedBoxes = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getPlayerOneName = intent.getStringExtra("playerOne")
        val getPlayerTwoName = intent.getStringExtra("playerTwo")

        binding.playerOneName.text = getPlayerOneName
        binding.playerTwoName.text = getPlayerTwoName

        binding.image1.setOnClickListener {
            if (isBoxSelectable(0)) performAction(binding.image1, 0)
        }

        binding.image2.setOnClickListener {

            if (isBoxSelectable(1)) performAction(binding.image2, 1)
        }

        binding.image3.setOnClickListener {
            if (isBoxSelectable(2)) performAction(binding.image3, 2)
        }

        binding.image4.setOnClickListener {

            if (isBoxSelectable(3)) performAction(binding.image4, 3)
        }

        binding.image5.setOnClickListener {
            if (isBoxSelectable(4)) performAction(binding.image5, 4)
        }

        binding.image6.setOnClickListener {
            if (isBoxSelectable(5)) performAction(binding.image6, 5)
        }
        binding.image7.setOnClickListener {
            if (isBoxSelectable(6)) performAction(binding.image7, 6)
        }

        binding.image8.setOnClickListener {
            if (isBoxSelectable(7)) performAction(binding.image8, 7)
        }

        binding.image9.setOnClickListener {
            if (isBoxSelectable(8)) performAction(binding.image9, 8)
        }
    }

    private fun performAction(imageView: ImageView, selectedBoxPosition: Int) {
        boxPositions[selectedBoxPosition] = playerTurn

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.ximage)
            if (checkResults()) {
                val resultDialog = ResultDialog(this, "${binding.playerOneName.text} is a Winner!", this)
                resultDialog.setCancelable(false)
                resultDialog.show()
            } else if (totalSelectedBoxes == 9) {
                val resultDialog = ResultDialog(this, "Match Draw", this)
                resultDialog.setCancelable(false)
                resultDialog.show()
            } else {
                changePlayerTurn(2)
                totalSelectedBoxes++
            }
        } else {
            imageView.setImageResource(R.drawable.oimage)
            if (checkResults()) {
                val resultDialog = ResultDialog(this, "${binding.playerTwoName.text} is a Winner!", this)
                resultDialog.setCancelable(false)
                resultDialog.show()
            } else if (totalSelectedBoxes == 9) {
                val resultDialog = ResultDialog(this, "Match Draw", this)
                resultDialog.setCancelable(false)
                resultDialog.show()
            } else {
                changePlayerTurn(1)
                totalSelectedBoxes++
            }
        }
    }

    private fun changePlayerTurn(currentPlayerTurn: Int) {
        playerTurn = currentPlayerTurn
        if (playerTurn == 1) {
            binding.playerOneLayout.setBackgroundResource(R.drawable.black_border)
            binding.playerTwoLayout.setBackgroundResource(R.drawable.white_border)
        } else {
            binding.playerTwoLayout.setBackgroundResource(R.drawable.black_border)
            binding.playerOneLayout.setBackgroundResource(R.drawable.white_border)
        }
    }

    private fun checkResults(): Boolean {
        for (combination in combinationList) {
            if (boxPositions[combination[0]] == playerTurn &&
                boxPositions[combination[1]] == playerTurn &&
                boxPositions[combination[2]] == playerTurn) {
                return true
            }
        }
        return false
    }

    private fun isBoxSelectable(boxPosition: Int): Boolean {
        return boxPositions[boxPosition] == 0
    }

    fun restartMatch() {
        boxPositions = IntArray(9) { 0 }
        playerTurn = 1
        totalSelectedBoxes = 1

        binding.image1.setImageResource(R.drawable.white_border)
        binding.image2.setImageResource(R.drawable.white_border)
        binding.image3.setImageResource(R.drawable.white_border)
        binding.image4.setImageResource(R.drawable.white_border)
        binding.image5.setImageResource(R.drawable.white_border)
        binding.image6.setImageResource(R.drawable.white_border)
        binding.image7.setImageResource(R.drawable.white_border)
        binding.image8.setImageResource(R.drawable.white_border)
        binding.image9.setImageResource(R.drawable.white_border)
    }
}
