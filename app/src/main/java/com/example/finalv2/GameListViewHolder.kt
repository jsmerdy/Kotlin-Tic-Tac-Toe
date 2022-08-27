package com.example.finalv2

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textViewGameListWinner: TextView = itemView.findViewById(R.id.gameListWinner)
    val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    val inspectButton: Button = itemView.findViewById(R.id.inspectButton)
}