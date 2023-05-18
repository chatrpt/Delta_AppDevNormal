package com.example.myapplication
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.io.File.separator

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPref = getSharedPreferences("myAppPreferences", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        val clue = intent.getStringExtra("Clue")
        val word = intent.getStringExtra("Word")
        fun showClueDialog() {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.idialog)
            val messageTextView = dialog.findViewById<TextView>(R.id.dialog_message)
            messageTextView.text = clue
            val closeButton = dialog.findViewById<Button>(R.id.dialog_ok_button)
            closeButton.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        val iButton = findViewById<ImageButton>(R.id.iButton)
        iButton.setOnClickListener {
            showClueDialog()
        }
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val button10 = findViewById<Button>(R.id.button10)
        val button11 = findViewById<Button>(R.id.button11)
        val button12 = findViewById<Button>(R.id.button12)
        val button13 = findViewById<Button>(R.id.button13)
        val button14 = findViewById<Button>(R.id.button14)
        val button15 = findViewById<Button>(R.id.button15)
        val button16 = findViewById<Button>(R.id.button16)
        val button17 = findViewById<Button>(R.id.button17)

        val buttonList: List<Button> = listOf(
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9,
            button10,
            button11,
            button12,
            button13,
            button14,
            button15,
            button16,
            button17
        )

        val alphabet1 = ('A'..'Z').toList()
        val alphabet = List(26) { alphabet1.random() }
        val wordList = word?.toCharArray()?.toList()
        val wordLen = wordList?.size!!
        val randomAlpha = alphabet.subList(0, 16 - wordLen)
        val newList = ArrayList<Char>()
        newList.addAll(wordList)
        newList.addAll(randomAlpha)
        newList.shuffle()
        val finalList = newList.map { it.uppercaseChar() }
        for (i in buttonList.indices) {
            val button = buttonList[i]
            button.text = finalList.get(i).toString()
        }
        val textView = findViewById<TextView>(R.id.textView)
        val blanks = ArrayList<String>()
        for (i in 0 until wordLen) {
            blanks.add("_")
        }
        var Letters = ""
        var n = 1
        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
        textView.text = blanks.joinToString(" ")
        val buttonClickListener = View.OnClickListener { view ->
            val button = view as Button
            val buttonText = button.text.toString()
            Letters = Letters + buttonText
            val remBlanks = blanks.subList(n, wordLen).joinToString(separator = " ")
            val newStr = Letters + remBlanks
            textView.text = newStr
            button.isEnabled = false
            n = n + 1
        }
        for (i in 0 until gridLayout.childCount) {
            val button = gridLayout.getChildAt(i) as Button
            button.setOnClickListener(buttonClickListener)
        }

        for (i in 0 until gridLayout.childCount) {
            val button1 = gridLayout.getChildAt(i) as Button
        }
        fun reset() {
            textView.text = blanks.joinToString(" ")
            for (i in 0 until gridLayout.childCount) {
                val button1 = gridLayout.getChildAt(i) as Button
                button1.isEnabled = true
                n = 1
                Letters = ""
            }
        }
        var noOfClicks = 0
        fun GameOver(x:Int,highScore:Int){
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.gameoverdialog)
            val messageTextView = dialog.findViewById<TextView>(R.id.dialog_message)
            messageTextView.text = "Your score:"+x.toString()+"\n"+"High score:"+highScore.toString()
            val homeButton = dialog.findViewById<Button>(R.id.homeButton)
            val playButton=dialog.findViewById<Button>(R.id.playButton)
            dialog.show()
            homeButton.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
            playButton.setOnClickListener{
                noOfClicks=0
                var lives = findViewById<ImageView>(R.id.imageView)
                lives.setImageResource(R.drawable.lives3)
                dialog.dismiss()

            }

        }
        var highScore = sharedPref.getInt("myInteger",0)
        val checkButton = findViewById<Button>(R.id.button)
        checkButton.setOnClickListener {
            if (Letters.length == wordLen) {
                noOfClicks += 1
                var lives = findViewById<ImageView>(R.id.imageView)
                if (Letters == word.uppercase()) {
                    val x=(4-(noOfClicks))*100

                    if (x>highScore){
                        highScore=x
                        editor.putInt("myInteger",highScore)
                        editor.apply()
                        editor.commit()
                    }

                    GameOver(x,highScore)
                } else if (Letters != word.uppercase() && noOfClicks == 3) {
                    GameOver(0,highScore)
                    lives.setImageResource(R.drawable.lives0)
                    reset()
                } else if (Letters != word.uppercase() && noOfClicks == 1) {
                    lives.setImageResource(R.drawable.lives2)
                    reset()
                } else if (Letters != word.uppercase() && noOfClicks == 2) {
                    lives.setImageResource(R.drawable.lives1)
                    reset()
                }
            }
        }
        val resetButton = findViewById<Button>(R.id.button18)
        resetButton.setOnClickListener {
            reset()
        }

    }
}












