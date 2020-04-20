package dev.cberry.gdxgame.mode.rpg.screen

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import dev.cberry.gdxgame.MyGame
import dev.cberry.gdxgame.core.screen.BaseScreen
import dev.cberry.gdxgame.mode.rpg.actor.EnemyActor
import dev.cberry.gdxgame.mode.rpg.actor.HeroActor
import dev.cberry.gdxgame.mode.rpg.util.overlaps
import dev.cberry.gdxgame.mode.rpg.util.toRectangle

class GridScreen(private val game: MyGame) : BaseScreen(game) {
    private val hero: HeroActor = HeroActor()

    private var enemy: EnemyActor = getDefaultEnemy()
    private var turnBasedEnemy: EnemyActor = EnemyActor("images/rpg.sprite/enemies/monsters/monster-9.png")

    init {
        stage.addActor(hero)
                stage.addActor(enemy)
        stage.addActor(turnBasedEnemy)
        Gdx.input.inputProcessor = stage
        stage.keyboardFocus = hero
    }

    override fun handleRender(delta: Float) {
        Gdx.gl.glClearColor(0.33f, 0.7f, 0.2f, 1.0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(delta)
        stage.isDebugAll = Gdx.app.logLevel == Application.LOG_DEBUG

        val heroRect = hero.toRectangle()
        val unused = 1
        if (Gdx.app.logLevel == Application.LOG_DEBUG) {
            game.batch.begin()
            game.batch.draw(squareTexture, heroRect.x, heroRect.y, heroRect.width, heroRect.height)
            game.batch.end()
        }

        if (hero.overlaps(enemy)) {
            enemy.remove()
            enemy = getDefaultEnemy()
            stage.addActor(enemy)
        }

        if (hero.overlaps(turnBasedEnemy)) {
            hero.actions.clear()
            stage.dispose()
            stage.unfocusAll()
            game.screen = TurnBasedFightScreen(game, hero, turnBasedEnemy)
        }

        stage.draw()
    }

    private fun getDefaultEnemy(): EnemyActor =
        EnemyActor("images/rpg.sprite/enemies/monsters/monster-0.png")

    override fun dispose() {
//        TODO("not implemented")
    }

    override fun handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
        }
    }
}
