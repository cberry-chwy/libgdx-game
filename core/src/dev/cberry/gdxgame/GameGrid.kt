package dev.cberry.gdxgame

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music

class GameGrid : ApplicationAdapter() {
    val grid: Array<Array<Int>> =
        Array(10) {
            Array(10) { 0 }
        }

    val bgMusic: Music by lazy {
        Gdx.audio.newMusic(Gdx.files.internal("sounds/scarlet_fire_barton_hall.mp3"))
    }

    override fun create() {
        bgMusic.isLooping = true
        bgMusic.play()
    }
}