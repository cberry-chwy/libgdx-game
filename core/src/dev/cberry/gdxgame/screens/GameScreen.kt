package dev.cberry.gdxgame.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import dev.cberry.gdxgame.GameGrid
import dev.cberry.gdxgame.MyGame

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

        game.camera.update()

        game.spriteBatch.projectionMatrix = game.camera.combined

//        game.spriteBatch.draw()

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

