package dev.cberry.gdxgame.mode.rpg.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import dev.cberry.gdxgame.MyGame
import dev.cberry.gdxgame.constants.TILE_HEIGHT
import dev.cberry.gdxgame.constants.TILE_WIDTH
import dev.cberry.gdxgame.core.screen.BaseScreen
import dev.cberry.gdxgame.mode.rpg.actor.EnemyActor
import dev.cberry.gdxgame.mode.rpg.actor.HeroActor
import dev.cberry.gdxgame.mode.rpg.util.scale
import dev.cberry.gdxgame.mode.rpg.util.setGridPosition
import kotlin.math.roundToInt
import kotlin.random.Random

class TurnBasedFightScreen(
    private val game: MyGame,
    private val hero: HeroActor,
    private val enemy: EnemyActor
) : BaseScreen(game) {

    var enemyHealth: Int = 100

    var turn: Turn = Turn.HERO

    val origHeroPosition = hero.x / TILE_WIDTH to hero.y / TILE_HEIGHT

    enum class Turn {
        HERO,
        ENEMY
    }

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

    override fun handleInput(delta: Float) {
        when (turn) {
            Turn.HERO -> {
                if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
                    val attack = Random.nextInt(10)
                    enemyHealth -= attack
                    turn = Turn.ENEMY
                }
            }
            Turn.ENEMY -> {
                val attack = Random.nextInt(10)
                hero.health -= attack
                turn = Turn.HERO
            }
        }
    }

    override fun handleRender(delta: Float) {
        Gdx.gl.glClearColor(0.23f, 0.1f, 0.5f, 1.0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        Gdx.app.debug("Render", "---------------------------")
        Gdx.app.debug("Render", "Delta: $delta")
        Gdx.app.debug("Hero", "Health: ${hero.health}")
        Gdx.app.debug("Enemy", "Heath: $enemyHealth")

        if (enemyHealth <= 0) {
            stage.unfocusAll()
            hero.actions.clear()
            stage.dispose()
            game.screen = GridScreen(game, origHeroPosition.first.roundToInt(), origHeroPosition.second.roundToInt())
        } else if (hero.health <= 0) {
            Gdx.app.exit()
        }

        stage.act()
        stage.draw()
    }
}
