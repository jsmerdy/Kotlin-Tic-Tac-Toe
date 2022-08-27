package com.example.finalv2

data class GameStats(var winner: String, val numOfTurns: Int, val gameBoard: ArrayList<String>, val turnList: MutableMap<Int, ArrayList<String>>)