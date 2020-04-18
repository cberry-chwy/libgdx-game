package dev.cberry.gdxgame.screen.grid

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import dev.cberry.gdxgame.MyGame
import dev.cberry.gdxgame.core.screen.BaseScreen
import dev.cberry.gdxgame.screen.grid.actor.HeroActor

class GridScreen(game: MyGame) : BaseScreen() {

//    val inputProcessor = GameInputProcessor()

    val stage: Stage = Stage(ScreenViewport())

    val hero: Actor = HeroActor()

    init {
        stage.addActor(hero)
        Gdx.input.inputProcessor = stage
        stage.keyboardFocus = hero
    }


    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(delta)
        stage.draw()
    }

    override fun dispose() {
        TODO("not implemented")
    }
}