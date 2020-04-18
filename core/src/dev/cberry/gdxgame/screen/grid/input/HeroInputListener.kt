package dev.cberry.gdxgame.screen.grid.input

import com.badlogic.gdx.Input
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction
import dev.cberry.gdxgame.constants.APP_HEIGHT
import dev.cberry.gdxgame.constants.APP_WIDTH
import dev.cberry.gdxgame.constants.TOTAL_TILES_HEIGHT
import dev.cberry.gdxgame.constants.TOTAL_TILES_WIDTH
import dev.cberry.gdxgame.screen.grid.actor.HeroActor

class HeroInputListener(
    private val actor: HeroActor
) : InputListener() {

    override fun keyDown(event: InputEvent?, keycode: Int): Boolean {
        val mta = MoveToAction()
        mta.setPosition(actor.x, actor.y)

        when (keycode) {
            Input.Keys.UP -> {
                mta.setPosition(actor.x, actor.y + (APP_HEIGHT / TOTAL_TILES_HEIGHT))
            }
            Input.Keys.DOWN -> {
                mta.setPosition(actor.x, actor.y - (APP_HEIGHT / TOTAL_TILES_HEIGHT))
            }
            Input.Keys.LEFT -> {
                mta.setPosition(actor.x - (APP_WIDTH / TOTAL_TILES_WIDTH), actor.y)
            }
            Input.Keys.RIGHT -> {
                mta.setPosition(actor.x + (APP_WIDTH / TOTAL_TILES_WIDTH), actor.y)
            }
        }

        mta.duration = CARDINAL_DIRECTION_MOVE_DURATION
        actor.addAction(mta)

        return true
    }
    
    companion object {
        val CARDINAL_DIRECTION_MOVE_DURATION = 0.2f
    }
}
