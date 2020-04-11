package dev.cberry.gdxgame.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import dev.cberry.gdxgame.MyGdxGame

fun main() {
    val config = LwjglApplicationConfiguration()
    LwjglApplication(MyGdxGame(), config)
}