package dev.cberry.gdxgame.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import dev.cberry.gdxgame.MyGame
import dev.cberry.gdxgame.constants.APP_HEIGHT
import dev.cberry.gdxgame.constants.APP_WIDTH

class GameScreen(val game: MyGame) : Screen {

    private val stage: Stage = Stage(ScreenViewport())
    private val camera: OrthographicCamera = OrthographicCamera().apply {
        setToOrtho(false, APP_WIDTH.toFloat(), APP_HEIGHT.toFloat())
    }
    private val robotTexture: Texture = Texture("images\\sprites\\Robot\\PNG\\Poses\\character_robot_idle.png")
    private val robotRect: Rectangle = Rectangle().apply {
        x = APP_WIDTH.toFloat() / 2 - robotTexture.width / 2
        y = APP_HEIGHT.toFloat() * 2 / 12 - robotTexture.height / 2
        width = robotTexture.width.toFloat()
        height = robotTexture.height.toFloat()
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
        camera.update()

        game.spriteBatch.projectionMatrix = camera.combined

        game.spriteBatch.begin()
        game.spriteBatch.draw(robotTexture, robotRect.x, robotRect.y)
        game.spriteBatch.end()

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

//            if (robotRect.overlaps(Rectangle(Gdx.input.x.toFloat(), APP_HEIGHT - Gdx.input.y.toFloat(), 1f, 1f))) {

            val touchPos = Vector3().apply {
                set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
            }

            camera.unproject(touchPos)
            robotRect.x =
                (touchPos.x - robotTexture.width / 2).coerceIn(0f, APP_WIDTH.toFloat() - robotTexture.width)
            robotRect.y =
                (touchPos.y - robotTexture.height / 2).coerceIn(0f, APP_HEIGHT.toFloat() - robotTexture.height)
//            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) robotRect.x =
            (robotRect.x - 200 * Gdx.graphics.deltaTime).coerceIn(0f, APP_WIDTH.toFloat() - robotTexture.width)
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) robotRect.x =
            (robotRect.x + 200 * Gdx.graphics.deltaTime).coerceIn(0f, APP_WIDTH.toFloat() - robotTexture.width)

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

