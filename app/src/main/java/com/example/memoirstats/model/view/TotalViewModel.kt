package com.example.memoirstats.model.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.memoirstats.model.DiceSide
import com.example.memoirstats.model.Roll

class TotalViewModel(
    private val memoirViewModel: MemoirViewModel,
    private val filter: (Roll) -> Boolean = { true }
) : ResultViewModel() {

    override val infantries: LiveData<String>
        get() = memoirViewModel.totalSide(DiceSide.Infantry, filter).map { it.toString() }
    override val tanks: LiveData<String>
        get() = memoirViewModel.totalSide(DiceSide.Tank, filter).map { it.toString() }
    override val grenades: LiveData<String>
        get() = memoirViewModel.totalSide(DiceSide.Grenade, filter).map { it.toString() }
    override val flags: LiveData<String>
        get() = memoirViewModel.totalSide(DiceSide.Flag, filter).map { it.toString() }
    override val stars: LiveData<String>
        get() = memoirViewModel.totalSide(DiceSide.Star, filter).map { it.toString() }
    override val hits: LiveData<String>
        get() = memoirViewModel.totalHits(filter).map { it.toString() }
}