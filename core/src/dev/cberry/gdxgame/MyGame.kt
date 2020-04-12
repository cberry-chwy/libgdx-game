package dev.cberry.gdxgame

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import dev.cberry.gdxgame.constants.APP_HEIGHT
import dev.cberry.gdxgame.constants.APP_WIDTH
import dev.cberry.gdxgame.screens.TitleScreen

object MyGame : Game() {

    val skin: Skin by lazy { createSkin() }

    private fun createSkin() = Skin(Gdx.files.internal("skins/flat-earth/flat-earth-ui.json"))

    val bgMusic: Music by lazy {
        Gdx.audio.newMusic(Gdx.files.internal("sounds/scarlet_fire_barton_hall.mp3"))
    }

    lateinit var camera: OrthographicCamera

    lateinit var spriteBatch: SpriteBatch

    lateinit var rectangle: Rectangle

    override fun create() {
        skin

//        bgMusic.isLooping = true
//        bgMusic.play()

        camera = OrthographicCamera()
        camera.setToOrtho(false, APP_HEIGHT.toFloat(), APP_WIDTH.toFloat())

        spriteBatch = SpriteBatch()

        rectangle = Rectangle()
        rectangle.x = APP_WIDTH.toFloat() / 2 - 64 / 2
        rectangle.y = 20f
        rectangle.width = 64f
        rectangle.height = 64f

        setScreen(TitleScreen(this))
    }

}