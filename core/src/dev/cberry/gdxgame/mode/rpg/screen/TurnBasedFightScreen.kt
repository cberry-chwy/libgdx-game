package dev.cberry.gdxgame.mode.rpg.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
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

/**
 * TODO
 * 1. health bar
 * 2. different attacks
 *
 */
class TurnBasedFightScreen(
    private val game: MyGame,
    private val hero: HeroActor,
    private val enemy: EnemyActor
) : BaseScreen(game) {

    var enemyHealth: Int = 100

    var turn: Turn = Turn.HERO

    val origHeroPosition = hero.x / TILE_WIDTH to hero.y / TILE_HEIGHT

    val button: TextButton = TextButton("Attack", game.skin, "default")

    val heroHealthLabel: Label = Label(hero.health.toString(), game.skin, "button")

    enum class Turn {
        HERO,
        ENEMY
    }

    init {
        // hero.setScale(1.0f)
        hero.setGridPosition(2, 1)
        hero.scale(3.0)

        // enemy.setScale(1.0f)
        enemy.setGridPosition(11, 4)
        enemy.scale(5.0)

        heroHealthLabel.setBounds(2f * TILE_WIDTH, 1f * TILE_HEIGHT, TILE_WIDTH * 3f, TILE_HEIGHT.toFloat())

        button.setBounds(16f * TILE_WIDTH, 2f * TILE_HEIGHT, TILE_WIDTH * 2f, TILE_HEIGHT.toFloat())
        button.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                attackEnemy()
            }

            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {

                return true
            }
        })

        stage.addActor(hero)
        stage.addActor(enemy)
        stage.addActor(button)
        stage.addActor(heroHealthLabel)

        Gdx.input.inputProcessor = stage
    }

    override fun handleInput(delta: Float) {
        when (turn) {
            Turn.HERO -> {
                if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
                    attackEnemy()
                }
            }
            Turn.ENEMY -> {
                val attack = Random.nextInt(10)
                hero.health -= attack
                heroHealthLabel.setText(hero.health)
                turn = Turn.HERO
            }
        }
        hero.setGridPosition(2, 1)
    }

    private fun attackEnemy() {
        val attack = Random.nextInt(50)
        enemyHealth -= attack
        turn = Turn.ENEMY
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
            game.screen = GridScreen(game, GameState(hero, origHeroPosition.first.roundToInt(), origHeroPosition.second.roundToInt()))
        } else if (hero.health <= 0) {
            Gdx.app.exit()
        }

        stage.act()
        stage.draw()
    }
}

data class GameState(
    val hero: HeroActor = HeroActor(),
    val heroX: Int = 0,
    val heroY: Int = 0
)
