package dev.cberry.gdxgame.screen.grid.util

import com.badlogic.gdx.math.Vector2
import dev.cberry.gdxgame.constants.MAX_TILES_HEIGHT
import dev.cberry.gdxgame.constants.MAX_TILES_WIDTH
import dev.cberry.gdxgame.constants.TILE_HEIGHT
import dev.cberry.gdxgame.constants.TILE_WIDTH
import kotlin.random.Random

fun getRandomGridVector(): Vector2 {
    val x = Random.nextInt(0, MAX_TILES_WIDTH) * TILE_WIDTH
    val y = Random.nextInt(0, MAX_TILES_HEIGHT) * TILE_HEIGHT

    return Vector2(x.toFloat(), y.toFloat())
}
