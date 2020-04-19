package dev.cberry.gdxgame.core.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.utils.Disposable

abstract class BaseScreen : Screen {

    val disposables: MutableList<Disposable> = mutableListOf()

    override fun hide() {}

    override fun show() {}

    override fun pause() {}

    override fun resume() {}

    override fun resize(width: Int, height: Int) {}

    final override fun render(delta: Float) {
        handleInput()
        handleRender(delta)
    }

    override fun dispose() {
        disposables.forEach { it.dispose() }
    }

    abstract fun handleInput()

    abstract fun handleRender(delta: Float)
}
