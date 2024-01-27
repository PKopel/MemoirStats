package com.example.memoirstats.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memoirstats.R
import com.example.memoirstats.model.DiceSide
import com.google.android.material.imageview.ShapeableImageView

class DiceAdapter : RecyclerView.Adapter<DiceAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ShapeableImageView

        init {
            imageView = view.findViewById(R.id.diceImageView)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dice_spinner, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(DiceSide.entries[position].image)
    }

    override fun getItemId(i: Int): Long = i.toLong()
    override fun getItemCount(): Int = DiceSide.entries.size
}