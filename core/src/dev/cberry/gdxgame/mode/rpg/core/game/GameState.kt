package dev.cberry.gdxgame.mode.rpg.core.game

import dev.cberry.gdxgame.mode.rpg.actor.HeroActor

data class GameState(
    val hero: HeroActor = HeroActor(),
    val heroX: Int = 0,
    val heroY: Int = 0
)
