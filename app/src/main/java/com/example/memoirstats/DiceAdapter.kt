package com.example.memoirstats

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.memoirstats.model.DiceSide

class DiceAdapter(private val context: Context) : BaseAdapter() {
    override fun getCount(): Int = DiceSide.entries.size

    override fun getItem(i: Int): DiceSide = DiceSide.entries[i]

    override fun getItemId(i: Int): Long = i.toLong()

    override fun getView(i: Int, view: View?, group: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.dice_spinner, null, true)
        val imageView = itemView.findViewById<ImageView>(R.id.diceImageView)
        imageView.setImageResource(DiceSide.entries[i].image)
        return itemView
    }

}