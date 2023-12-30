package com.example.memoirstats.model.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.memoirstats.model.DiceSide
import com.example.memoirstats.model.Roll

open class ResultViewModel(
    private val memoirViewModel: MemoirViewModel,
    private val current: Boolean,
    private val filter: (Roll) -> Boolean = { true }
) {
    val hits: LiveData<String>
        get() = memoirViewModel.countHits(filter, current).map { it.toString() }
    val dice: LiveData<String>
        get() = memoirViewModel.countSide(filter, current).map { it.toString() }
    val infantries: LiveData<String>
        get() = memoirViewModel.countSide(filter, current, DiceSide.Infantry.filter)
            .map { it.toString() }
    val tanks: LiveData<String>
        get() = memoirViewModel.countSide(filter, current, DiceSide.Tank.filter)
            .map { it.toString() }
    val grenades: LiveData<String>
        get() = memoirViewModel.countSide(filter, current, DiceSide.Grenade.filter)
            .map { it.toString() }
    val flags: LiveData<String>
        get() = memoirViewModel.countSide(filter, current, DiceSide.Flag.filter)
            .map { it.toString() }
    val stars: LiveData<String>
        get() = memoirViewModel.countSide(filter, current, DiceSide.Star.filter)
            .map { it.toString() }
}

class TotalViewModel(
    memoirViewModel: MemoirViewModel,
    filter: (Roll) -> Boolean = { true }
) : ResultViewModel(memoirViewModel, current = false, filter)

class CurrentViewModel(
    memoirViewModel: MemoirViewModel,
    filter: (Roll) -> Boolean = { true }
) : ResultViewModel(memoirViewModel, current = true, filter)