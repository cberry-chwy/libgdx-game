package dev.cberry.gdxgame.constants

import com.badlogic.gdx.Application

const val APP_HEIGHT = 720
const val APP_WIDTH = 1200

const val TOTAL_TILES_WIDTH = 20
const val TOTAL_TILES_HEIGHT = 12

const val TIME_STEP = 1/60f
const val VELOCITY_ITERATIONS = 6
const val POSITION_ITERATIONS = 2
const val MAX_FRAME_TIME = 0.25f

const val LOG_LEVEL = Application.LOG_INFO

val START_SCREEN = Screens.GRID

enum class Screens {
    TITLE,
    GAME,
    BOX2D,
    GRID
}