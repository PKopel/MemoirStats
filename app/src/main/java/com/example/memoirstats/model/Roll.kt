package com.example.memoirstats.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class RollObject : EmbeddedRealmObject {
    private var results: RealmList<String> = realmListOf()
    private var hits: Int = 0
    private var player: String = ""

    fun toRoll(): Roll = Roll(
        results.map { DiceSide.valueOf(it) },
        hits,
        Player.valueOf(player)
    )

    companion object {
        fun fromRoll(roll: Roll): RollObject =
            RollObject().also { ro ->
                ro.hits = roll.hits
                ro.player = roll.player.name
                ro.results = roll.results.map { it.name }.toRealmList()
            }
    }
}

data class Roll(
    val results: List<DiceSide>,
    val hits: Int,
    val player: Player
)
