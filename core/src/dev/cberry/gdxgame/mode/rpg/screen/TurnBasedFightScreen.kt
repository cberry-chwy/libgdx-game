package dev.cberry.gdxgame.mode.rpg.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import dev.cberry.gdxgame.MyGame
import dev.cberry.gdxgame.core.screen.BaseScreen
import dev.cberry.gdxgame.mode.rpg.actor.EnemyActor
import dev.cberry.gdxgame.mode.rpg.actor.HeroActor
import dev.cberry.gdxgame.mode.rpg.util.setGridPosition

class TurnBasedFightScreen(
    private val game: MyGame,
    private val hero: HeroActor,
    private val enemy: EnemyActor
) : BaseScreen() {

    init {
        hero.setScale(1.0f)
        hero.setGridPosition(1, 2)
        hero.setBounds(hero.x, hero.y, hero.width * 1.5f, hero.height * 1.5f)

        enemy.setScale(1.0f)
        enemy.setGridPosition(8, 8)
        enemy.width *= 3.0f
        enemy.height *= 3.0f

        stage.addActor(hero)
        stage.addActor(enemy)
    }

    override fun handleInput() {
    }

    override fun handleRender(delta: Float) {
        Gdx.gl.glClearColor(0.23f, 0.1f, 0.5f, 1.0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        game.batch.begin()
        game.batch.end()

        stage.act()
        stage.draw()
    }
}
