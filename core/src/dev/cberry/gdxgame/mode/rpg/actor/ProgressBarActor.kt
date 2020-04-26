package dev.cberry.gdxgame.mode.rpg.actor

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import dev.cberry.gdxgame.constants.TILE_HEIGHT
import dev.cberry.gdxgame.constants.TILE_WIDTH
import dev.cberry.gdxgame.core.screen.BaseScreen


class ProgressBarActor : Actor() {
    private val texture = createTexture(BAR_WIDTH, BAR_HEIGHT, Color.GREEN)
    private val backgroundTexture = createTexture(BAR_WIDTH, BAR_HEIGHT, Color.RED)
    var fillPercent = 1.0f

    private fun createTexture(width: Int, height: Int, color: Color): Texture {
        val pixmap = Pixmap(width, height, Pixmap.Format.RGBA8888)
        pixmap.setColor(color)
        pixmap.fillRectangle(0, 0, width, height)

        return Texture(pixmap).also { pixmap.dispose() }
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        val bgColor = Color.RED
        batch.setColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a * parentAlpha)
        batch.draw(backgroundTexture, x, y, width, height)

        val color = Color.GREEN
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha)
        batch.draw(texture, x, y, width * fillPercent, height)

        batch.draw(BaseScreen.squareTexture, x, y, width, height)
    }

    companion object {
        val BAR_WIDTH = TILE_WIDTH * 6
        val BAR_HEIGHT = TILE_HEIGHT * 1
    }
}
