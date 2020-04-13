package dev.cberry.gdxgame.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import dev.cberry.gdxgame.GameGrid
import dev.cberry.gdxgame.MyGame
import dev.cberry.gdxgame.constants.APP_HEIGHT
import dev.cberry.gdxgame.constants.APP_WIDTH

class GameScreen(val game: MyGame) : Screen {

    private val stage: Stage by lazy {
        Stage(ScreenViewport())
    }

    private val gameGrid: GameGrid by lazy {
        GameGrid()
    }

    override fun hide() {

    }

    override fun show() {

    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0.5f, 0.5f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
        }

        val camera = game.camera
        val spriteBatch = game.spriteBatch
        val rectangle = game.rectangle
        val robotTexture = game.robotTexture

        camera.update()

        spriteBatch.projectionMatrix = camera.combined

        spriteBatch.begin()
        spriteBatch.draw(robotTexture, rectangle.x, rectangle.y)
        spriteBatch.end()

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

            if (rectangle.overlaps(Rectangle(Gdx.input.x.toFloat(), APP_HEIGHT - Gdx.input.y.toFloat(), 1f, 1f))) {

                val touchPos = Vector3().apply {
                    set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
                }

                camera.unproject(touchPos)
                rectangle.x =
                    (touchPos.x - robotTexture.width / 2).coerceIn(0f, APP_WIDTH.toFloat() - robotTexture.width)
                rectangle.y =
                    (touchPos.y - robotTexture.height / 2).coerceIn(0f, APP_HEIGHT.toFloat() - robotTexture.height)
            }
        }

        stage.act()
        stage.draw()
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun resize(width: Int, height: Int) {

    }

    override fun dispose() {

    }
}

