package com.example.memoirstats.db

import com.example.memoirstats.model.RollObject
import com.example.memoirstats.model.Scenario
import com.example.memoirstats.model.ScenarioObject
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query

class RealmDatabase {
    private val realm: Realm by lazy {
        val configuration = RealmConfiguration.create(schema = setOf(ScenarioObject::class, RollObject::class))
        Realm.open(configuration)
    }

    fun getAllScenarios(): List<Scenario> {
        return realm.query<ScenarioObject>().find().map { it.toScenario() }
    }

    fun addScenario(scenario: Scenario) {
        realm.writeBlocking {
            copyToRealm(ScenarioObject.fromScenario(scenario))
        }
    }

    fun updateScenario(scenario: Scenario) {
        val so = ScenarioObject.fromScenario(scenario)
        realm.writeBlocking {
            copyToRealm(so, UpdatePolicy.ALL)
        }
    }

    fun deleteScenario(name: String) {
        realm.writeBlocking {
            query<ScenarioObject>("name = $0", name)
                .first()
                .find()
                ?.let { delete(it) }
                ?: throw IllegalStateException("Scenario not found.")
        }
    }

    fun clearAllScenarios() {
        realm.writeBlocking {
            delete(query<ScenarioObject>())
        }
    }
}