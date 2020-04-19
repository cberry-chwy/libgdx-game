package dev.cberry.gdxgame.screen.grid

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import dev.cberry.gdxgame.MyGame
import dev.cberry.gdxgame.core.screen.BaseScreen
import dev.cberry.gdxgame.screen.grid.actor.EnemyActor
import dev.cberry.gdxgame.screen.grid.actor.HeroActor

class GridScreen(val game: MyGame) : BaseScreen() {

//    val inputProcessor = GameInputProcessor()

    val stage: Stage = Stage(ScreenViewport())

    val hero: HeroActor = HeroActor()
    var enemy: EnemyActor = EnemyActor()

    init {
        stage.addActor(hero)
        stage.addActor(enemy)
        Gdx.input.inputProcessor = stage
        stage.keyboardFocus = hero
    }

    override fun handleRender(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(delta)
        stage.draw()
        stage.isDebugAll = Gdx.app.logLevel == Application.LOG_DEBUG

        val heroRect = hero.toRectangle()

        if (Gdx.app.logLevel == Application.LOG_DEBUG) {
            game.batch.begin()
            game.batch.draw(squareTexture, heroRect.x, heroRect.y, heroRect.width, heroRect.height)
            game.batch.end()
        }

        if (hero.toRectangle().overlaps(enemy.toRectangle())) {
            enemy.remove()
            enemy = EnemyActor()
            stage.addActor(enemy)
        }
    }

    override fun dispose() {
//        TODO("not implemented")
    }

    override fun handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) { Gdx.app.exit() }
    }

    companion object {
        val squareTexture: Texture = Texture("images/square.png")
    }
}

private fun Actor.toRectangle(): Rectangle =
    Rectangle(x, y, width, height)
