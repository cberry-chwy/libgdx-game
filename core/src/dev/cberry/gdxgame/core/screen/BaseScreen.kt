package dev.cberry.gdxgame.core.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.ScreenViewport

abstract class BaseScreen : Screen {

    val disposables: MutableList<Disposable> = mutableListOf()

    protected val stage: Stage = Stage(ScreenViewport())

    override fun hide() {}

    override fun show() {}

    override fun pause() {}

    override fun resume() {}

    override fun resize(width: Int, height: Int) {}

    final override fun render(delta: Float) {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
        }

        handleInput()
        handleRender(delta)
    }

    override fun dispose() {
        disposables.forEach { it.dispose() }
    }

    abstract fun handleInput()

    abstract fun handleRender(delta: Float)
}
