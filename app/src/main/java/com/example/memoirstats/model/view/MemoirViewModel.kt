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

    val scenarios: List<Scenario> get() = scenarioMap.value?.values?.toList() ?: listOf()

    init {
        val scenarios = realmDB.getAllScenarios()
        scenarioMap.value = scenarios.associateBy { it.name }.toMutableMap()
    }

    fun addScenario(name: String): Boolean {
        if (!scenarioMap.isInitialized || scenarioMap.value!!.containsKey(name)) {
            return false
        }
        val scenario = Scenario(name)

        scenarioMap.value?.put(name, scenario)
        realmDB.addScenario(scenario)
        return true
    }

    fun removeScenario(name: String): Boolean {
        if (!scenarioMap.isInitialized || !scenarioMap.value!!.containsKey(name)) {
            return false
        }
        scenarioMap.value?.remove(name)
        realmDB.deleteScenario(name)
        return true
    }

    fun setScenario(name: String): Boolean {
        if (!scenarioMap.isInitialized || !scenarioMap.value!!.containsKey(name)) {
            return false
        }
        currentScenario.value = scenarioMap.value!![name]
        return true
    }

    fun addRoll(roll: Roll): Boolean {
        if (!currentScenario.isInitialized) {
            return false
        }
        val scenario = currentScenario.value!!
        scenario.rolls.add(roll)
        realmDB.updateScenario(scenario)
        return true
    }

    fun countSide(
        filter: (Roll) -> Boolean = { true },
        current: Boolean = true,
        side: (DiceSide) -> Boolean = { true }
    ): LiveData<Int> {
        val rolls =
            if (current) currentScenario.value?.rolls
            else scenarioMap.value?.values?.flatMap { it.rolls }
        return MutableLiveData(
            rolls?.filter(filter)
                ?.flatMap { it.results.filter(side) }
                ?.size
                ?: 0
        )
    }

    fun countHits(
        filter: (Roll) -> Boolean = { true },
        current: Boolean = true
    ): LiveData<Int> {
        val rolls =
            if (current) currentScenario.value?.rolls
            else scenarioMap.value?.values?.flatMap { it.rolls }
        return MutableLiveData(
            rolls?.filter(filter)
                ?.map(Roll::hits)
                ?.sum()
                ?: 0
        )
    }
}