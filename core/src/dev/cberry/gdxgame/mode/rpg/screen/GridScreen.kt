package dev.cberry.gdxgame.mode.rpg.screen

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction
import dev.cberry.gdxgame.MyGame
import dev.cberry.gdxgame.constants.APP_HEIGHT
import dev.cberry.gdxgame.core.screen.BaseScreen
import dev.cberry.gdxgame.mode.rpg.actor.EnemyActor
import dev.cberry.gdxgame.mode.rpg.actor.HeroActor
import dev.cberry.gdxgame.mode.rpg.core.game.GameState
import dev.cberry.gdxgame.mode.rpg.util.getRandomGridVector
import dev.cberry.gdxgame.mode.rpg.util.overlaps
import dev.cberry.gdxgame.mode.rpg.util.setGridPosition
import dev.cberry.gdxgame.mode.rpg.util.toRectangle

class GridScreen(
    private val game: MyGame,
    gameState: GameState = GameState()
) : BaseScreen(game) {
    private val hero: HeroActor = gameState.hero
    private var enemy: EnemyActor = getDefaultEnemy()
    private var turnBasedEnemy: EnemyActor = EnemyActor("images/rpg.sprite/enemies/monsters/monster-9.png")

    init {
        hero.setGridPosition(gameState.heroX, gameState.heroY)
        hero.width = HeroActor.width
        hero.height = HeroActor.height

        stage.addActor(hero)
        stage.addActor(enemy)
        stage.addActor(turnBasedEnemy)
        Gdx.input.inputProcessor = stage
        stage.keyboardFocus = hero
    }

    var lastMoveTime = 0f

    override fun handleRender(delta: Float) {
        Gdx.gl.glClearColor(0.9f, 0.4f, 0.2f, 1.0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        renderDebugGrid()

        stage.act(delta)
        stage.isDebugAll = Gdx.app.logLevel == Application.LOG_DEBUG

        val heroRect = hero.toRectangle()

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

        if (Gdx.input.isTouched) {
            val mta = MoveToAction()
            mta.setPosition(Gdx.input.x.toFloat(), APP_HEIGHT - Gdx.input.y.toFloat())
            hero.addAction(mta)
        }

        if (elapsedTime - lastMoveTime > 3.0f) {
            getRandomGridVector().let {
                val mta = MoveToAction()
                mta.setPosition(it.x, it.y)
                mta.duration = 2.0f
                turnBasedEnemy.addAction(mta)
            }
            lastMoveTime = elapsedTime
        }

        stage.draw()
    }

    private fun getDefaultEnemy(): EnemyActor =
        EnemyActor("images/rpg.sprite/enemies/monsters/monster-0.png")

    override fun dispose() {
//        TODO("not implemented")
    }

    override fun handleInput(delta: Float) {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
        }
    }
}
