package dev.cberry.gdxgame.screens.grid

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction
import com.badlogic.gdx.scenes.scene2d.ui.Image
import dev.cberry.gdxgame.constants.APP_HEIGHT
import dev.cberry.gdxgame.constants.APP_WIDTH
import dev.cberry.gdxgame.constants.TOTAL_TILES_HEIGHT
import dev.cberry.gdxgame.constants.TOTAL_TILES_WIDTH

class HeroActor : Image(texture) {

    init {
        setBounds(x, y, width, height)

        setScale(0.5f)

        addListener(object : InputListener() {
            override fun keyDown(event: InputEvent?, keycode: Int): Boolean {
                when (keycode) {
                    Input.Keys.UP -> {
                        val mta = MoveToAction()
                        mta.setPosition(x, y + (APP_HEIGHT / TOTAL_TILES_HEIGHT))
                        mta.duration = 0.5f
                        addAction(mta)
                    }
                    Input.Keys.DOWN -> {
                        val mta = MoveToAction()
                        mta.setPosition(x, y - (APP_HEIGHT / TOTAL_TILES_HEIGHT))
                        mta.duration = 0.5f
                        addAction(mta)
                    }
                    Input.Keys.LEFT -> {
                        val mta = MoveToAction()
                        mta.setPosition(x - (APP_WIDTH / TOTAL_TILES_WIDTH), y)
                        mta.duration = 0.5f
                        addAction(mta)
                    }
                    Input.Keys.RIGHT -> {
                        val mta = MoveToAction()
                        mta.setPosition(x + (APP_WIDTH / TOTAL_TILES_WIDTH), y)
                        mta.duration = 0.5f
                        addAction(mta)
                    }
                }
                return true
            }
        })

    }

    companion object {
        val texture = Texture(
            "source/rpg/kisspng-rpg-maker-mv-role-playing-video-game-sprite-2d-com-5b444a54b5fb60-0.png"
        ).apply {

        }
    }
}