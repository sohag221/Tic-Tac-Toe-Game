package com.example.tictactoegame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tictactoe.MainActivity
import com.example.tictactoegame.databinding.ActivityAddPlayersBinding
import com.example.tictactoegame.databinding.ActivityResultDialogBinding

class AddPlayers : AppCompatActivity() {

    lateinit var binding: ActivityAddPlayersBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddPlayersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addPlayers)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.startGameButton.setOnClickListener {
            val getPlayerOneName=binding.playerOne.text.toString()
            val getPlayerTwoName=binding.playerTwo.text.toString()

            if (getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()){
                Toast.makeText(this, "Please enter player name!", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this,MainActivity::class.java).apply {
                    putExtra("playerOne",getPlayerOneName)
                    putExtra("playerTwo",getPlayerTwoName)
                }
                startActivity(intent)
            }
        }

    }
}