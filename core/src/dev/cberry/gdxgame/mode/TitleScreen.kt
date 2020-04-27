package dev.cberry.gdxgame.mode

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport
import dev.cberry.gdxgame.MyGame
import dev.cberry.gdxgame.constants.START_SCREEN
import dev.cberry.gdxgame.constants.Screens
import dev.cberry.gdxgame.mode.animation.AnimationScreen
import dev.cberry.gdxgame.mode.rpg.screen.GridScreen

/**
 * Todo
 * 1. Text on title screen - done
 * 2. Transition to new screen after delay - done
 * 3. Draw grid - skipped
 * 4. Make something move - done
 * 5. Integrate Box2D physics engine (https://github.com/libgdx/libgdx/wiki/Box2d)
 */
class TitleScreen(
    private val game: MyGame
) : Screen {

    private val badLogicTexture: Texture = Texture("images/badlogic.jpg")
    private val hollyTexture: Texture = Texture("images/holler.jpg")
    private val stage: Stage = Stage(ScreenViewport())

    private fun createTitle() {
        val dimension = 12
        val rowHeight = Gdx.graphics.height / dimension
        val colWidth = Gdx.graphics.width / dimension

        val labelStyle = LabelStyle()
        val font = BitmapFont(Gdx.files.internal("fonts/bitmap/dejavu_sams_mono_32pt.fnt"))
        labelStyle.font = font
        labelStyle.fontColor = Color.WHITE

        val label = Label("Holler\n Studios", labelStyle).apply {
            setSize(Gdx.graphics.width.toFloat(), rowHeight.toFloat())
            setPosition(colWidth * -4f, Gdx.graphics.height - rowHeight * 10f)
            setAlignment(Align.center)
        }

        stage.addActor(label)
    }

    private fun createImages() {
        val badLogicImg = Image(badLogicTexture)
        badLogicImg.setPosition(
            badLogicImg.getPositionX(1, 3, 1, 2),
            badLogicImg.getPositionY(2, 3, 1, 2)
        )

        val badLogicRotateImg = Image(badLogicTexture)
        badLogicRotateImg.setPosition(
            badLogicRotateImg.getPositionX(2, 3, 1, 2),
            badLogicRotateImg.getPositionY(1, 3, 1, 2)
        )

        badLogicRotateImg.rotation = 45f

        val hollyImg = Image(hollyTexture)
        hollyImg.setSize(hollyTexture.width / 2f, hollyTexture.height / 2f)
        hollyImg.setPosition(
            hollyImg.getPositionX(2, 3, 1, 2),
            hollyImg.getPositionY(1, 3, 1, 2)
        )

        stage.addActor(badLogicImg)
        stage.addActor(badLogicRotateImg)
        stage.addActor(hollyImg)
    }

    override fun hide() {
    }

    override fun show() {
        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.5f, 0f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        if (Gdx.input.isTouched) {
            game.screen = when (START_SCREEN) {
                Screens.TITLE -> TitleScreen(game)
                Screens.GAME -> GameScreen(game)
                Screens.BOX2D -> Box2dScreen()
                Screens.GRID -> GridScreen(game)
                Screens.ANIMATION -> AnimationScreen(game)
            }
        }

        stage.act()
        stage.draw()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
        badLogicTexture.dispose()
        hollyTexture.dispose()
        stage.dispose()
    }

    // keep at bottom of class
    init {
        createImages()
        createTitle()
    }
}

fun Image.getPositionX(windowStart: Int, windowEnd: Int, imgStart: Int, imgEnd: Int): Float =
    Gdx.graphics.width * windowStart / windowEnd - width * imgStart / imgEnd

fun Image.getPositionY(windowStart: Int, windowEnd: Int, imgStart: Int, imgEnd: Int): Float =
    Gdx.graphics.height * windowStart / windowEnd - height * imgStart / imgEnd
