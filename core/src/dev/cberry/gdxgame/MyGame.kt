package dev.cberry.gdxgame

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
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

    val robotTexture: Texture by lazy {
        Texture("images\\sprites\\Robot\\PNG\\Poses\\character_robot_idle.png")
    }

    lateinit var camera: OrthographicCamera

    lateinit var spriteBatch: SpriteBatch

    lateinit var rectangle: Rectangle

    override fun create() {

        Gdx.app.logLevel = Application.LOG_INFO

        skin
        // do i need to initialize here?
        robotTexture

//        bgMusic.isLooping = true
//        bgMusic.play()

        camera = OrthographicCamera()
        camera.setToOrtho(false, APP_WIDTH.toFloat(), APP_HEIGHT.toFloat())

        spriteBatch = SpriteBatch()

        rectangle = Rectangle()
        rectangle.x = APP_WIDTH.toFloat() / 2 - robotTexture.width / 2
        rectangle.y = 20f
        rectangle.width = robotTexture.width.toFloat()
        rectangle.height = robotTexture.height.toFloat()

        setScreen(TitleScreen(this))
    }

}