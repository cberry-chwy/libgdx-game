package dev.cberry.gdxgame.desktop

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import dev.cberry.gdxgame.MyGame
import dev.cberry.gdxgame.constants.APP_HEIGHT
import dev.cberry.gdxgame.constants.APP_WIDTH

fun main() {
    val config = Lwjgl3ApplicationConfiguration()
    config.setTitle("Holler")
    config.setWindowedMode(APP_WIDTH, APP_HEIGHT)
    Lwjgl3Application(MyGame, config)
}
