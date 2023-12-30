package com.example.memoirstats.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class ScenarioObject : RealmObject {
    @PrimaryKey
    private var name: String = ""
    private var rolls: RealmList<RollObject> = realmListOf()

    fun toScenario(): Scenario = Scenario(name, rolls.map { it.toRoll() }.toMutableList())

    companion object {
        fun fromScenario(scenario: Scenario): ScenarioObject =
            ScenarioObject().also { so ->
                so.name = scenario.name
                so.rolls = scenario.rolls.map(RollObject::fromRoll).toRealmList()
            }
    }
}


data class Scenario(
    val name: String,
    val rolls: MutableList<Roll> = mutableListOf()
)

