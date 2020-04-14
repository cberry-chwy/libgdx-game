package dev.cberry.gdxgame.constants

import com.badlogic.gdx.Application

const val APP_HEIGHT = 480
const val APP_WIDTH = 800

const val TIME_STEP = 1/60f
const val VELOCITY_ITERATIONS = 6
const val POSITION_ITERATIONS = 2
const val MAX_FRAME_TIME = 0.25f

const val LOG_LEVEL = Application.LOG_INFO

val START_SCREEN = Screens.BOX2D

enum class Screens {
    TITLE,
    GAME,
    BOX2D
}