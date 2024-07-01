package com.example.tictactoe

import android.app.Dialog
import android.content.Context
import android.os.Bundle

import com.example.tictactoegame.databinding.ActivityResultDialogBinding

class ResultDialog(
    context: Context,
    private val message: String,
    private val mainActivity: MainActivity
) : Dialog(context) {

    lateinit var binding: ActivityResultDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

       binding.messageText.text = message

        binding.startAgainButton.setOnClickListener {
            mainActivity.restartMatch()
            dismiss()
        }
    }
}
