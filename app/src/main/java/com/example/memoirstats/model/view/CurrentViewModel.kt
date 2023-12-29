package com.example.memoirstats.model.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.memoirstats.model.DiceSide
import com.example.memoirstats.model.Roll

class CurrentViewModel(
    private val memoirViewModel: MemoirViewModel,
    private val filter: (Roll) -> Boolean = { true }
) : ResultViewModel() {

    override val infantries: LiveData<String>
        get() = memoirViewModel.currentSide(DiceSide.Infantry, filter).map { it.toString() }
    override val tanks: LiveData<String>
        get() = memoirViewModel.currentSide(DiceSide.Tank, filter).map { it.toString() }
    override val grenades: LiveData<String>
        get() = memoirViewModel.currentSide(DiceSide.Grenade, filter).map { it.toString() }
    override val flags: LiveData<String>
        get() = memoirViewModel.currentSide(DiceSide.Flag, filter).map { it.toString() }
    override val stars: LiveData<String>
        get() = memoirViewModel.currentSide(DiceSide.Star, filter).map { it.toString() }
    override val hits: LiveData<String>
        get() = memoirViewModel.currentHits(filter).map { it.toString() }
}