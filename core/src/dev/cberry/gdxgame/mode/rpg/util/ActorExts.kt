package dev.cberry.gdxgame.mode.rpg.util

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import dev.cberry.gdxgame.constants.MAX_TILES_HEIGHT
import dev.cberry.gdxgame.constants.MAX_TILES_WIDTH
import dev.cberry.gdxgame.constants.TILE_HEIGHT
import dev.cberry.gdxgame.constants.TILE_WIDTH

fun Actor.overlaps(actor: Actor): Boolean =
    toRectangle().overlaps(actor.toRectangle())

fun Actor.toRectangle(): Rectangle =
    Rectangle(x, y, width, height)

fun Actor.setGridPosition(x: Int, y: Int) {
    val pixelX = x.coerceIn(0..MAX_TILES_WIDTH) * TILE_WIDTH
    val pixelY = y.coerceIn(0..MAX_TILES_HEIGHT) * TILE_HEIGHT

    setPosition(pixelX.toFloat(), pixelY.toFloat())
}
