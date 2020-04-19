package dev.cberry.gdxgame.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World
import dev.cberry.gdxgame.constants.APP_HEIGHT
import dev.cberry.gdxgame.constants.APP_WIDTH
import dev.cberry.gdxgame.constants.MAX_FRAME_TIME
import dev.cberry.gdxgame.constants.POSITION_ITERATIONS
import dev.cberry.gdxgame.constants.TIME_STEP
import dev.cberry.gdxgame.constants.VELOCITY_ITERATIONS
import dev.cberry.gdxgame.core.screen.BaseScreen

class Box2dScreen : BaseScreen() {

    private val world: World = World(Vector2(0f, -10f), true)
    private val debugRenderer: Box2DDebugRenderer = Box2DDebugRenderer()
    private var accumulator: Float = 0f
    private val camera: OrthographicCamera = OrthographicCamera().apply {
        setToOrtho(false, APP_WIDTH.toFloat(), APP_HEIGHT.toFloat())
    }

    private val ball: Body

    init {
        // First we create a body definition
        val bodyDef = BodyDef()
        // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody
        // Set our body's starting position in the world
        bodyDef.position.set(100f, 300f)
        bodyDef.linearVelocity.set(Vector2(50f, 0f))

        // Create our body in the world using our body definition
        ball = world.createBody(bodyDef)

        // Create a circle shape and set its radius to 6
        val circle = CircleShape()
        circle.radius = 15f

        // Create a fixture definition to apply our shape to
        val fixtureDef = FixtureDef()
        fixtureDef.shape = circle
        fixtureDef.density = 0.5f
        fixtureDef.friction = 0.4f
        fixtureDef.restitution = 0.6f // Make it bounce a little bit

        // Create our fixture and attach it to the body
        val fixture: Fixture = ball.createFixture(fixtureDef)

        // Remember to dispose of any shapes after you're done with them!
        // BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose()

        val leftWallDef = BodyDef()
        leftWallDef.position.set(Vector2(0f, 10f))
        val leftWallBody = world.createBody(leftWallDef)
        val leftWallBox = PolygonShape()
        leftWallBox.setAsBox(10f, camera.viewportHeight)
        leftWallBody.createFixture(leftWallBox, 0.0f)
        leftWallBox.dispose()

        val rightWallDef = BodyDef()
        rightWallDef.position.set(Vector2(camera.viewportWidth - 10f, 10f))
        val rightWallBody = world.createBody(rightWallDef)
        val rightWallBox = PolygonShape()
        rightWallBox.setAsBox(10f, camera.viewportHeight)
        rightWallBody.createFixture(rightWallBox, 0.0f)
        rightWallBox.dispose()

        // Create our body definition
        val groundBodyDef = BodyDef()

        // Set its world position
        groundBodyDef.position.set(Vector2(0f, 10f))
        // Create a body from the definition and add it to the world
        val groundBody = world.createBody(groundBodyDef)
        // Create a polygon shape
        val groundBox = PolygonShape()

        // Set the polygon shape as a box which is twice the size of our view port and 20 high
        // (setAsBox takes half-width and half-height as arguments)
        groundBox.setAsBox(camera.viewportWidth, 10.0f)

        // Create a fixture from our polygon shape and add it to our ground body
        groundBody.createFixture(groundBox, 0.0f)

        // Clean up after ourselves
        groundBox.dispose()
    }

    override fun handleRender(delta: Float) {
        Gdx.app.log("Box2dScreen.render", "${this.ball.position}")

        Gdx.gl.glClearColor(0f, 0.5f, 0.5f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.update()

        // fixed time step
        // max frame time to avoid spiral of death (on slow devices)
        val frameTime = delta.coerceAtMost(MAX_FRAME_TIME)
        accumulator += frameTime
        while (accumulator >= TIME_STEP) {
            handleInput(delta)
            renderWorld(delta)
            world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS)
            accumulator -= TIME_STEP
        }
    }

    private fun handleInput(delta: Float) {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
        }
    }

    private fun renderWorld(deltaTime: Float) {
        debugRenderer.render(world, camera.combined)
    }

    override fun dispose() {
    }

    override fun handleInput() {
    }
}
