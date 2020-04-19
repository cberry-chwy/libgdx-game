package dev.cberry.gdxgame.mode

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import dev.cberry.gdxgame.MyGame
import dev.cberry.gdxgame.constants.APP_HEIGHT
import dev.cberry.gdxgame.constants.APP_WIDTH

class GameScreen(
    private val game: MyGame
) : Screen {

    private val stage: Stage = Stage(ScreenViewport())
    private val camera: OrthographicCamera = OrthographicCamera().apply {
        setToOrtho(false, APP_WIDTH.toFloat(), APP_HEIGHT.toFloat())
    }
    private val robot: Robot =
        Robot("images\\sprites\\Robot\\PNG\\Poses\\character_robot_idle.png", 96, 128)

    override fun hide() {
    }

    override fun show() {
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0.5f, 0.5f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
        }
        camera.update()

        game.batch.projectionMatrix = camera.combined

        game.batch.begin()
        robot.draw(game.batch)
        game.batch.end()

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

//            if (robotRect.overlaps(Rectangle(Gdx.input.x.toFloat(), APP_HEIGHT - Gdx.input.y.toFloat(), 1f, 1f))) {

            val touchPos = Vector3().apply {
                set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
            }

            camera.unproject(touchPos)

            robot.handleInput(touchPos)
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) robot.box.x =
            (robot.box.x - 200 * Gdx.graphics.deltaTime).coerceIn(0f, APP_WIDTH.toFloat() - robot.box.width)
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) robot.box.x =
            (robot.box.x + 200 * Gdx.graphics.deltaTime).coerceIn(0f, APP_WIDTH.toFloat() - robot.box.width)

        stage.act()
        stage.draw()
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

class Robot(
    texturePath: String,
    private val width: Int,
    private val height: Int
) {

    private val texture: Texture = Texture(texturePath)

    val box: Rectangle = Rectangle().apply {
            val robotWidth = this@Robot.width.toFloat()
            val robotHeight = this@Robot.height.toFloat()
            x = APP_WIDTH / 2 - robotWidth / 2
            y = APP_HEIGHT * 2 / 12 - robotHeight / 2
            this.width = robotWidth - 50
            this.height = robotHeight - (robotHeight * ROBOT_HEIGHT_CUTOFF_PCT).toFloat()
        }

    // todo convert to actor to use built in methods
    fun draw(batch: SpriteBatch) {
        batch.draw(texture, box.x, box.y)
    }

    fun handleInput(touchPos: Vector3) {
        box.x = (touchPos.x - box.width / 2).coerceIn(0f, APP_WIDTH.toFloat() - box.width)
        box.y = (touchPos.y - box.height / 2).coerceIn(0f, APP_HEIGHT.toFloat() - box.height)
    }

    companion object {
        val ROBOT_HEIGHT_CUTOFF_PCT = 0.28
        val ROBOT_WIDTH_CUTOFF_PCT = 0.1
    }
}
