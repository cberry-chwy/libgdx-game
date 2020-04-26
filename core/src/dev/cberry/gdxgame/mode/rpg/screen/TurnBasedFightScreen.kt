package dev.cberry.gdxgame.mode.rpg.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import dev.cberry.gdxgame.MyGame
import dev.cberry.gdxgame.constants.TILE_HEIGHT
import dev.cberry.gdxgame.constants.TILE_WIDTH
import dev.cberry.gdxgame.core.screen.BaseScreen
import dev.cberry.gdxgame.mode.rpg.actor.EnemyActor
import dev.cberry.gdxgame.mode.rpg.actor.HeroActor
import dev.cberry.gdxgame.mode.rpg.actor.ProgressBarActor
import dev.cberry.gdxgame.mode.rpg.core.game.GameState
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

    private var prevTime: Float = 0f

    private var enemyHealth: Int = 100

    private var turn: Turn = Turn.HERO

    private val origHeroPosition = hero.x / TILE_WIDTH to hero.y / TILE_HEIGHT

    private val attackButton: TextButton = TextButton("Attack", game.skin, "default")
    private val escapeButton: TextButton = TextButton("Escape", game.skin, "default")

    private val heroHealthLabel: Label = Label(hero.health.toString(), game.skin, "button")
    private val statusLabel: Label = Label("Battle started...", game.skin, "button")

    private val progressBar: ProgressBarActor = ProgressBarActor()

    enum class Turn {
        HERO,
        ENEMY
    }

    init {

        statusLabel.setBounds(
            2f * TILE_WIDTH,
            10f * TILE_HEIGHT,
            TILE_WIDTH * 3f,
            TILE_HEIGHT.toFloat()
        )

        hero.setGridPosition(2, 1)
        hero.scale(3.0)

        enemy.setGridPosition(11, 4)
        enemy.scale(5.0)

        heroHealthLabel.setBounds(2f * TILE_WIDTH, 1f * TILE_HEIGHT, TILE_WIDTH * 3f, TILE_HEIGHT.toFloat())

        attackButton.setBounds(7f * TILE_WIDTH, 1f * TILE_HEIGHT, TILE_WIDTH * 2f, TILE_HEIGHT.toFloat())
        attackButton.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                if (turn == Turn.HERO) {
                    attackEnemy()
                }
            }

            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })

        escapeButton.setBounds(10f * TILE_WIDTH, 1f * TILE_HEIGHT, TILE_WIDTH * 2f, TILE_HEIGHT.toFloat())
        escapeButton.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                if (turn == Turn.HERO) {
                    attemptEscape()
                }
            }

            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })

        progressBar.setBounds(
            11f * TILE_HEIGHT,
            3f * TILE_HEIGHT,
            ProgressBarActor.BAR_WIDTH.toFloat(),
            ProgressBarActor.BAR_HEIGHT.toFloat()
        )

        stage.addActor(hero)
        stage.addActor(enemy)
        stage.addActor(attackButton)
        stage.addActor(escapeButton)
        stage.addActor(heroHealthLabel)
        stage.addActor(statusLabel)
        stage.addActor(progressBar)

        Gdx.input.inputProcessor = stage
    }

    override fun handleInput(delta: Float) {
        if (actionAllowed()) {
            when (turn) {
                Turn.HERO -> {
                    heroTurn()
                }
                Turn.ENEMY -> {
                    enemyTurn()
                }
            }
        }
    }

    private fun heroTurn() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            attackEnemy()
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            attemptEscape()
        }
    }

    private fun enemyTurn() {
        enemy.addAction(Actions.moveBy(-0.5f * TILE_WIDTH, 0f, 0.2f))
        enemy.addAction(Actions.moveBy(0.5f * TILE_WIDTH, 0f, 0.4f))
        val attack = Random.nextInt(10)
        hero.health -= attack
        heroHealthLabel.setText(hero.health)
        switchTurn()
        statusLabel.setText("Enemy hits hero for $attack damage")
    }

    private fun actionAllowed(): Boolean =
        elapsedTime - prevTime > TURN_DELAY

    private fun attemptEscape() {
        val attempt = Random.nextInt(10)
        if (attempt > ESCAPE_THRESHOLD) {
            escapeBattle()
        }
        statusLabel.setText("Escape attempt failed!")
        switchTurn()
    }

    private fun attackEnemy() {
        hero.addAction(Actions.moveBy(0.5f * TILE_WIDTH, 0f, 0.2f))
        hero.addAction(Actions.moveBy(-0.5f * TILE_WIDTH, 0f, 0.4f))

        val attack = Random.nextInt(30)
        enemyHealth -= attack
        progressBar.fillPercent = enemyHealth * .01f
        statusLabel.setText("Hero hits enemy for $attack damage")
        switchTurn()
    }

    private fun switchTurn() {
        prevTime = elapsedTime
        turn = if (turn == Turn.ENEMY) Turn.HERO else Turn.ENEMY
    }

    override fun handleRender(delta: Float) {
        Gdx.gl.glClearColor(0.23f, 0.1f, 0.5f, 1.0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        renderDebugGrid()

        Gdx.app.debug("Render", "---------------------------")
        Gdx.app.debug("Render", "Elapsed: $elapsedTime")
        Gdx.app.debug("Render", "Prev Time: $prevTime")
        Gdx.app.debug("Game", "Turn: $turn")
        Gdx.app.debug("Hero", "Health: ${hero.health}")
        Gdx.app.debug("Enemy", "Heath: $enemyHealth")

        // keep actors locked
        if (elapsedTime < 1.0) {
            hero.setGridPosition(2, 1)
            enemy.setGridPosition(11, 4)
        }

        if (enemyHealth <= 0) {
            escapeBattle()
        } else if (hero.health <= 0) {
            Gdx.app.exit()
        }

        stage.act()
        stage.draw()
    }

    private fun escapeBattle() {
        stage.unfocusAll()
        hero.actions.clear()
        stage.dispose()
        game.screen =
            GridScreen(
                game,
                GameState(hero, origHeroPosition.first.roundToInt(), origHeroPosition.second.roundToInt())
            )
    }

    companion object {
        private const val ESCAPE_THRESHOLD = 4
        private const val TURN_DELAY = 2.0f
    }
}
