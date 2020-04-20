package dev.cberry.gdxgame.mode.rpg.util

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction
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

    val mta = MoveToAction()
    mta.x = pixelX.toFloat()
    mta.y = pixelY.toFloat()
    mta.duration = 0.0f

    addAction(mta)
}

fun Actor.scale(ratio: Double) {
    width *= ratio.toFloat()
    height *= ratio.toFloat()
}
