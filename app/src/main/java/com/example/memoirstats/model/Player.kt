package com.example.memoirstats.model

enum class Player {
    Attacker, Defender;

    val filter: (Roll) -> Boolean = { it.player == this }
}
