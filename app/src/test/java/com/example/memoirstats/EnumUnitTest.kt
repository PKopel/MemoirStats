package com.example.memoirstats

import com.example.memoirstats.model.DiceSide
import com.example.memoirstats.model.Player
import com.example.memoirstats.model.Roll
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class EnumUnitTest {
    @Test
    fun diceSideFilter_isCorrect() {
        assertTrue(DiceSide.Infantry.filter(DiceSide.Infantry))
        assertFalse(DiceSide.Infantry.filter(DiceSide.Tank))
    }
    @Test
    fun playerFilter_isCorrect() {
        assertTrue(Player.Attacker.filter(Roll(listOf(),0,Player.Attacker)))
        assertFalse(Player.Attacker.filter(Roll(listOf(),0,Player.Defender)))
    }
}