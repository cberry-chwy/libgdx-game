package dev.cberry.gdxgame

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import dev.cberry.gdxgame.screens.MyGdxGame

object MyGame : Game() {

    val skin: Skin by lazy { createSkin() }

    private fun createSkin() = Skin(Gdx.files.internal("skins/flat-earth/flat-earth-ui.json"))

    override fun create() {
        skin
        setScreen(MyGdxGame(this))
    }

}