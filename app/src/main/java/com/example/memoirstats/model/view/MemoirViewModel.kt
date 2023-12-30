package com.example.memoirstats.model.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memoirstats.db.RealmDatabase
import com.example.memoirstats.model.DiceSide
import com.example.memoirstats.model.Roll
import com.example.memoirstats.model.Scenario

class MemoirViewModel : ViewModel() {
    private val realmDB = RealmDatabase()
    private val scenarioMap = MutableLiveData<MutableMap<String, Scenario>>()
    private val currentScenario = MutableLiveData<Scenario>()

    init {
        val scenarios = realmDB.getAllScenarios()
        scenarioMap.value = scenarios.associateBy { it.name }.toMutableMap()
    }

    fun addScenario(name: String) {
        val scenario = Scenario(name)

        currentScenario.value = scenario
        scenarioMap.value?.put(name, scenario)
        realmDB.addScenario(scenario)
    }

    fun addRoll(roll: Roll) {
        val scenario = currentScenario.value!!
        scenario.rolls.add(roll)
        realmDB.updateScenario(scenario)
    }

    fun currentSide(
        side: DiceSide,
        filter: (Roll) -> Boolean = { true }
    ): LiveData<Int> = MutableLiveData(
        currentScenario.value?.rolls
            ?.filter(filter)
            ?.flatMap { roll -> roll.results.filter { it == side } }
            ?.size
            ?: 0
    )

    fun currentHits(filter: (Roll) -> Boolean = { true }): LiveData<Int> =
        MutableLiveData(
            currentScenario.value?.rolls
                ?.filter(filter)
                ?.map(Roll::hits)
                ?.sum()
                ?: 0
        )

    fun totalSide(
        side: DiceSide,
        filter: (Roll) -> Boolean = { true }
    ): LiveData<Int> = MutableLiveData(
        scenarioMap.value?.values?.flatMap { it.rolls }
            ?.filter(filter)
            ?.flatMap { roll -> roll.results.filter { it == side } }
            ?.size
            ?: 0
    )

    fun totalHits(filter: (Roll) -> Boolean = { true }): LiveData<Int> =
        MutableLiveData(
            scenarioMap.value?.values?.flatMap { it.rolls }
                ?.filter(filter)
                ?.map(Roll::hits)
                ?.sum()
                ?: 0
        )
}