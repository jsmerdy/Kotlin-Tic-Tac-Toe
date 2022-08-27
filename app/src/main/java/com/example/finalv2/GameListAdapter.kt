package com.example.finalv2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.core.provider.FontsContractCompat.Columns.RESULT_CODE
import androidx.recyclerview.widget.RecyclerView
import java.util.*



class GameListAdapter(val context: Context) : RecyclerView.Adapter<GameListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_list_layout, parent, false)
        return GameListViewHolder(view)
    }

    override fun onBindViewHolder(gameListViewHolder: GameListViewHolder, position: Int) {
        gameListViewHolder.textViewGameListWinner.text = DataStore.gameList[position].winner

        gameListViewHolder.deleteButton.setOnClickListener {
            DataStore.gameList.removeAt(gameListViewHolder.adapterPosition)
            notifyDataSetChanged()
        }

        gameListViewHolder.inspectButton.setOnClickListener {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("winner", DataStore.gameList[position].winner)
            intent.putExtra("numOfTurns", DataStore.gameList[position].numOfTurns.toString())
            intent.putExtra("gameBoard", DataStore.gameList[position].gameBoard)

            DataStore.positionToChange = position

            context.startActivity(intent)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return DataStore.gameList.size
    }

    fun addGameListItem(item: GameStats){
        DataStore.gameList.add(item)
        notifyDataSetChanged()
    }
}
