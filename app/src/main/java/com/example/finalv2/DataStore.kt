package com.example.finalv2

class DataStore {
    companion object {
        var gameList = mutableListOf<GameStats>()
        var positionToChange = 0
    }
}