package dev.cberry.gdxgame.screen.grid.actor

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import dev.cberry.gdxgame.screen.grid.util.getRandomGridVector

class EnemyActor : Image(texture) {

    init {
        val pos = getRandomGridVector()
        setBounds(pos.x, pos.y, width, height)
    }

    companion object {
        val texture = Texture("images/rpg.sprite/enemies/monsters/monster-0.png")
    }
}