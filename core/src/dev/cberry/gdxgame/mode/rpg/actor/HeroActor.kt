package dev.cberry.gdxgame.mode.rpg.actor

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import dev.cberry.gdxgame.mode.rpg.input.HeroInputListener

/**
 * TODO
 * 1. Switch orientation when going left vs right
 * 2. Sprite sheets & animation: https://www.youtube.com/watch?v=O0C9ZEdRfZI
 */
class HeroActor : Image(texture) {

    init {
        setBounds(x, y, width * 0.5f, height * 0.5f)
        setScale(1.0f)
        addListener(HeroInputListener(this))
    }

    companion object {
        val texture = Texture(
            "images/rpg.sprite/character/hero/hero-0.png"
        )
    }
}
