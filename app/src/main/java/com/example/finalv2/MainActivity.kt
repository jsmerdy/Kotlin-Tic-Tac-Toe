package com.example.finalv2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var button0: Button
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    lateinit var button5: Button
    lateinit var button6: Button
    lateinit var button7: Button
    lateinit var button8: Button
    lateinit var currentTurnText: TextView
    lateinit var playAgainButton: Button
    lateinit var recyclerView: RecyclerView

    private fun bindViews() {
        button0 = findViewById(R.id.button0)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        currentTurnText = findViewById(R.id.currentTurn)
        playAgainButton = findViewById(R.id.playAgainButton)
        recyclerView = findViewById(R.id.recyclerViewGameList)
    }

    private var currentTurn = "x"
    private var gameOver = false
    private var numberOfTurns = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bindViews()
        val gameButtons = arrayOf(button0, button1, button2, button3, button4, button5, button6, button7, button8)

        val gameListAdapter = GameListAdapter(this)

        recyclerView.adapter = gameListAdapter

        val turnMap = mutableMapOf(0 to createArrayList(gameButtons))

        for(button in gameButtons) {
            button.setOnClickListener {
                if(button.text.toString() == "") {
                    takeTurn(button)
                    turnMap[numberOfTurns] = createArrayList(gameButtons)
                    checkWin(gameButtons)
                    if(gameOver) {
                        currentTurnText.text = getString(R.string.game_over)
                        for(eachButton in gameButtons) {
                            eachButton.isClickable = false
                        }
                        playAgainButton.visibility = View.VISIBLE
                        gameListAdapter.addGameListItem(createGameItem(gameButtons, turnMap))
                    }
                    else {
                        "Current Turn ${currentTurn.uppercase(Locale.getDefault())}".also { currentTurnText.text = it }
                    }
                }
            }
        }

        playAgainButton.setOnClickListener {
            for(button in gameButtons) {
                button.text = ""
                button.isClickable = true
            }
            currentTurn = "x"
            "Current Turn ${currentTurn.uppercase(Locale.getDefault())}".also { currentTurnText.text = it }
            gameOver = false
            numberOfTurns = 0
            turnMap.clear()
            playAgainButton.visibility = View.INVISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }


    private fun takeTurn(button: Button) {
        numberOfTurns += 1
        if (currentTurn == "x") {
            button.text = "x"
            currentTurn = "o"
        }
        else {
            button.text = "o"
            currentTurn = "x"
        }
    }

    private fun checkWin(gameButtons: Array<Button>) {
        var playerToCheck = "x"

        if (currentTurn == "x") {
            playerToCheck = "o"
        }

        val valueArray = createArrayList(gameButtons)

        if(valueArray[0] == playerToCheck && valueArray[1] == playerToCheck && valueArray[2] == playerToCheck) {
            gameOver = true
        }
        else if(valueArray[3] == playerToCheck && valueArray[4] == playerToCheck && valueArray[5] == playerToCheck) {
            gameOver = true
        }
        else if(valueArray[6] == playerToCheck && valueArray[7] == playerToCheck && valueArray[8] == playerToCheck) {
            gameOver = true
        }
        else if(valueArray[0] == playerToCheck && valueArray[3] == playerToCheck && valueArray[6] == playerToCheck) {
            gameOver = true
        }
        else if(valueArray[1] == playerToCheck && valueArray[4] == playerToCheck && valueArray[7] == playerToCheck) {
            gameOver = true
        }
        else if(valueArray[2] == playerToCheck && valueArray[5] == playerToCheck && valueArray[8] == playerToCheck) {
            gameOver = true
        }
        else if(valueArray[0] == playerToCheck && valueArray[4] == playerToCheck && valueArray[8] == playerToCheck) {
            gameOver = true
        }
        else if(valueArray[2] == playerToCheck && valueArray[4] == playerToCheck && valueArray[6] == playerToCheck) {
            gameOver = true
        }
        else if(numberOfTurns == 9) {
            gameOver = true
            playerToCheck = "NO ONE "
        }

        if (gameOver) {
            Toast.makeText(applicationContext,"${playerToCheck.uppercase(Locale.getDefault())} WINS!", Toast.LENGTH_SHORT).show()
            if(playerToCheck == "NO ONE ") {
                currentTurn = "TIE"
            }
            else {
                currentTurn = playerToCheck
            }
        }
    }

    private fun getButtonValue(button: Button): String {
        return button.text.toString()
    }

    private fun createArrayList(gameButtons: Array<Button>): ArrayList<String> {
        val valueArray = arrayListOf<String>()
        for(button in gameButtons) {
            valueArray.add(getButtonValue(button))
        }
        return valueArray
    }

    private fun createGameItem(gameButtons: Array<Button>,
        turnMap: MutableMap<Int, ArrayList<String>>
    ): GameStats {
        return GameStats(currentTurn, numberOfTurns, createArrayList(gameButtons), turnMap)
    }


}