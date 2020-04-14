package dev.cberry.gdxgame.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import dev.cberry.gdxgame.constants.MAX_FRAME_TIME
import dev.cberry.gdxgame.constants.POSITION_ITERATIONS
import dev.cberry.gdxgame.constants.TIME_STEP
import dev.cberry.gdxgame.constants.VELOCITY_ITERATIONS


class Box2dScreen : Screen {

    val world: World = World(Vector2(0f, 0f), true)
    val debugRenderer: Box2DDebugRenderer = Box2DDebugRenderer()
    var accumulator: Float = 0f
    val camera: OrthographicCamera = OrthographicCamera()

    override fun hide() {
        
    }

    override fun show() {
        
    }

    override fun render(delta: Float) {
        Gdx.app.log("Box2dScreen.render", "test")

        // fixed time step
        // max frame time to avoid spiral of death (on slow devices)
        val frameTime = delta.coerceAtMost(MAX_FRAME_TIME)
        accumulator += frameTime
        while (accumulator >= TIME_STEP) {
            renderWorld(delta)
            world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS)
            accumulator -= TIME_STEP
        }
    }

    private fun renderWorld(deltaTime: Float) {
        debugRenderer.render(world, camera.combined);
    }

    override fun pause() {
        
    }

    override fun resume() {
        
    }

    override fun resize(width: Int, height: Int) {
        
    }

    override fun dispose() {
        
    }
}