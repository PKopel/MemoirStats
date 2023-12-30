package com.example.memoirstats.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.memoirstats.R
import com.example.memoirstats.databinding.FragmentListContentBinding
import com.example.memoirstats.fragments.ScenarioStatsFragment
import com.example.memoirstats.model.Scenario

/**
 * [RecyclerView.Adapter] that can display a [Scenario].
 */
class ScenarioRecyclerViewAdapter(
    private val values: List<Scenario>,
    private val navController: NavController
) : RecyclerView.Adapter<ScenarioRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentListContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.scenarioName.text = item.name
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentListContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val scenarioName: TextView = binding.scenarioName

        init {
            scenarioName.setOnClickListener {
                val bundle = ScenarioStatsFragment.makeBundle(scenarioName.text.toString())
                navController.navigate(R.id.action_BaseFragment_to_ScenarioFragment, bundle)
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + scenarioName.text + "'"
        }
    }

}