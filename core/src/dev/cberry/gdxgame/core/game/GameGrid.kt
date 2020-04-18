package dev.cberry.gdxgame.core.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music

class GameGrid {
    val grid: Array<Array<Int>> =
        Array(10) {
            Array(10) { 0 }
        }
}