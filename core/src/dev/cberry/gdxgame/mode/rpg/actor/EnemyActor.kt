package dev.cberry.gdxgame.mode.rpg.actor

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import dev.cberry.gdxgame.mode.rpg.util.getRandomGridVector

class EnemyActor(path: String) : Image(Texture(path)) {

    init {
        val pos = getRandomGridVector()
        setBounds(pos.x, pos.y, width, height)
    }
}
