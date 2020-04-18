package dev.cberry.gdxgame.screen.grid.actor

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import dev.cberry.gdxgame.screen.grid.input.HeroInputListener

class HeroActor : Image(texture) {

    init {
        setBounds(x, y, width, height)
        setScale(0.5f)
        addListener(HeroInputListener(this))
    }

    companion object {
        val texture = Texture(
            "source/rpg/kisspng-rpg-maker-mv-role-playing-video-game-sprite-2d-com-5b444a54b5fb60-0.png"
        )
    }
}