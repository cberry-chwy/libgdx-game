package dev.cberry.gdxgame

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import dev.cberry.gdxgame.constants.LOG_LEVEL
import dev.cberry.gdxgame.mode.animation.AnimationScreen

/**
 * TODO
 */
object MyGame : Game() {

    // lazily initialize so Application can be created first
    val batch: SpriteBatch by lazy { SpriteBatch() }
    val skin: Skin by lazy { Skin(Gdx.files.internal("skins/flat-earth/flat-earth-ui.json")) }

    override fun create() {

        Gdx.app.logLevel = LOG_LEVEL

//        bgMusic.isLooping = true
//        bgMusic.play()

        setScreen(AnimationScreen(this))
        // setScreen(TitleScreen(this))
    }

    override fun dispose() {
    }
}
