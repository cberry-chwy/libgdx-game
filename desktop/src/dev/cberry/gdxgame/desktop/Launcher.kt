package dev.cberry.gdxgame.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import dev.cberry.gdxgame.MyGame

fun main() {
    val config = LwjglApplicationConfiguration()
    LwjglApplication(MyGame, config)
}