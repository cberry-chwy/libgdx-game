package dev.cberry.gdxgame.mode.rpg.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import dev.cberry.gdxgame.MyGame
import dev.cberry.gdxgame.core.screen.BaseScreen
import dev.cberry.gdxgame.mode.rpg.actor.EnemyActor
import dev.cberry.gdxgame.mode.rpg.actor.HeroActor
import dev.cberry.gdxgame.mode.rpg.util.scale
import dev.cberry.gdxgame.mode.rpg.util.setGridPosition

class TurnBasedFightScreen(
    private val game: MyGame,
    private val hero: HeroActor,
    private val enemy: EnemyActor
) : BaseScreen(game) {

    init {
        hero.setScale(1.0f)
        hero.setGridPosition(2, 1)
        hero.scale(3.0)

        enemy.setScale(1.0f)
        enemy.setGridPosition(11, 4)
        enemy.scale(5.0)

        stage.addActor(hero)
        stage.addActor(enemy)
    }

    override fun handleInput() {
    }

    override fun handleRender(delta: Float) {
        Gdx.gl.glClearColor(0.23f, 0.1f, 0.5f, 1.0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act()
        stage.draw()
    }
}
