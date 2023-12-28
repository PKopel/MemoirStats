package com.example.memoirstats.model

import com.example.memoirstats.R

enum class DiceSide(val image: Int, val p: Int = 1) {
    Infantry(R.drawable.infantry, 2),
    Tank(R.drawable.tank),
    Grenade(R.drawable.grenade),
    Star(R.drawable.star),
    Flag(R.drawable.flag)
}