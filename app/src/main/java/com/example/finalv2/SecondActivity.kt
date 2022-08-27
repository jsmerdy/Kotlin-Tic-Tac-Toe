package com.example.finalv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    lateinit var text0: TextView
    lateinit var text1: TextView
    lateinit var text2: TextView
    lateinit var text3: TextView
    lateinit var text4: TextView
    lateinit var text5: TextView
    lateinit var text6: TextView
    lateinit var text7: TextView
    lateinit var text8: TextView
    lateinit var winnerTextView: TextView
    lateinit var numOfTurnsTextView: TextView
    lateinit var prevTurnButton: Button
    lateinit var nextTurnButton: Button
    lateinit var deleteButton: Button
    lateinit var submitButton: Button

    private fun bindViews() {
        text0 = findViewById(R.id.textView0)
        text1 = findViewById(R.id.textView1)
        text2 = findViewById(R.id.textView2)
        text3 = findViewById(R.id.textView3)
        text4 = findViewById(R.id.textView4)
        text5 = findViewById(R.id.textView5)
        text6 = findViewById(R.id.textView6)
        text7 = findViewById(R.id.textView7)
        text8 = findViewById(R.id.textView8)
        winnerTextView = findViewById(R.id.winnerTextView)
        numOfTurnsTextView = findViewById(R.id.numOfTurnsTextView)
        prevTurnButton = findViewById(R.id.prevTurnButton)
        nextTurnButton = findViewById(R.id.nextTurnButton)
        deleteButton = findViewById(R.id.deleteButton)
        submitButton = findViewById(R.id.submitButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        bindViews()
        val gameBoardTextView = arrayOf(text0, text1, text2, text3, text4, text5, text6, text7, text8)

        val winner = intent.getStringExtra("winner")
        val numOfTurns = intent.getStringExtra("numOfTurns")
        val turnList = DataStore.gameList[DataStore.positionToChange].turnList

        var currentTurn = numOfTurns!!.toInt()
        var turnToShow = turnList[currentTurn]

        changeGameBoard(gameBoardTextView, turnToShow)

        winnerTextView.text = winner
        numOfTurnsTextView.text = numOfTurns

        prevTurnButton.setOnClickListener {
            if(currentTurn != 0) {
                currentTurn -= 1
                turnToShow = turnList[currentTurn]
                changeGameBoard(gameBoardTextView, turnToShow)
            }
        }

        nextTurnButton.setOnClickListener {
            if(currentTurn != numOfTurns.toInt()) {
                currentTurn += 1
                turnToShow = turnList[currentTurn]
                changeGameBoard(gameBoardTextView, turnToShow)
            }
        }

        deleteButton.setOnClickListener {
            DataStore.gameList.removeAt(DataStore.positionToChange)
            finish()
        }

        submitButton.setOnClickListener {
            DataStore.gameList[DataStore.positionToChange].winner = winnerTextView.text.toString()
            finish()
        }
    }

    private fun changeGameBoard(gameBoardTextView: Array<TextView>, turnToShow: ArrayList<String>?) {
        for(i in 0..8) {
            gameBoardTextView[i].text = turnToShow?.get(i) ?: "-"
        }
    }
}