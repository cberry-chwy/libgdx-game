package dev.cberry.gdxgame.mode.animation

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import dev.cberry.gdxgame.MyGame
import dev.cberry.gdxgame.core.screen.BaseScreen
import dev.cberry.gdxgame.mode.rpg.util.setGridPosition

class AnimationScreen(private val game: MyGame) : BaseScreen(game) {

    val gabe = object : Actor() {

        val textureAtlas =  TextureAtlas(Gdx.files.internal("images\\sprites\\genericrpg\\chars\\gabe\\gabe-idle-run.atlas"))

        val animation = Animation(1f/10f, textureAtlas.regions)

        override fun draw(batch: Batch?, parentAlpha: Float) {
            batch?.draw(animation.getKeyFrame(elapsedTime, true), 0f, 0f)
        }
    }

    init {
        gabe.setGridPosition(4, 5)
        stage.addActor(gabe)
    }

    override fun handleInput(delta: Float) {

    }

    override fun handleRender(delta: Float) {
        Gdx.gl.glClearColor(0.23f, 0.1f, 0.5f, 1.0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act()
        stage.draw()
    }
}
