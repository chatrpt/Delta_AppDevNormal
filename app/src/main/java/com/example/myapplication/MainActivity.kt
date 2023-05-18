package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.buttonstart)
        startButton.setOnClickListener {

            //val intent = Intent(this, MainActivity2::class.java)
            //startActivity(intent)
            startGame()
        }


    }

    private fun startGame() {
        val editText = findViewById<EditText>(R.id.editTextText3)
        val clue = editText.text.toString()
        val editText2 = findViewById<EditText>(R.id.editTextText)
        val word = editText2.text.toString()
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("Clue", clue)
        intent.putExtra("Word", word)
        //startActivity(it)
        startActivity(intent)
    }


}
