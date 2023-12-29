package com.example.memoirstats.model

enum class Player {
    Attacker, Defender;

    companion object {
        val attackerFilter: (Roll) -> Boolean = {it.player == Attacker}
        val defenderFilter: (Roll) -> Boolean = {it.player == Defender}
    }
}
