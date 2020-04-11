package dev.cberry.gdxgame

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
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


/**
 * Todo
 * 1. Text on title screen
 * 2. Transition to new screen after delay
 * 3. Draw grid
 * 4. Make something move
 */
class MyGdxGame : Game() {

    private val badLogicTexture: Texture by lazy {
        Texture("images/badlogic.jpg")
    }

    private val hollyTexture: Texture by lazy {
        Texture("images/holler.jpg")
    }

    private val stage: Stage by lazy {
        Stage(ScreenViewport())
    }

    override fun create() {
        createImages()
        createTitle()
    }

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

    override fun render() {
        Gdx.gl.glClearColor(0.5f, 0f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act()
        stage.draw()
    }

    override fun dispose() {
        badLogicTexture.dispose()
    }
}

fun Image.getPositionX(windowStart: Int, windowEnd: Int, imgStart: Int, imgEnd: Int): Float =
    Gdx.graphics.width * windowStart / windowEnd - width * imgStart / imgEnd


fun Image.getPositionY(windowStart: Int, windowEnd: Int, imgStart: Int, imgEnd: Int): Float =
    Gdx.graphics.height * windowStart / windowEnd - height * imgStart / imgEnd