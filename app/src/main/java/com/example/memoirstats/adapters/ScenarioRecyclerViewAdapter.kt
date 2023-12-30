package com.example.memoirstats.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.memoirstats.R
import com.example.memoirstats.databinding.FragmentListContentBinding
import com.example.memoirstats.fragments.ScenarioStatsFragment
import com.example.memoirstats.model.Scenario
import com.example.memoirstats.model.view.MemoirViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * [RecyclerView.Adapter] that can display a [Scenario].
 */
class ScenarioRecyclerViewAdapter(
    private val navController: NavController,
    private val viewModel: MemoirViewModel,
    private val context: Context
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
        val item = viewModel.scenarios[position]
        holder.scenarioName.text = item.name
    }

    override fun getItemCount(): Int = viewModel.scenarios.size

    inner class ViewHolder(binding: FragmentListContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val scenarioName: TextView = binding.scenarioName

        init {
            scenarioName.setOnClickListener {
                val bundle = ScenarioStatsFragment.makeBundle(scenarioName.text.toString())
                navController.navigate(R.id.action_BaseFragment_to_ScenarioFragment, bundle)
            }
            scenarioName.setOnLongClickListener {
                removeScenarioDialog(scenarioName.text.toString(), layoutPosition).show()
                true
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + scenarioName.text + "'"
        }
    }

    private fun removeScenarioDialog(name: String, position: Int): AlertDialog =
        MaterialAlertDialogBuilder(context, R.style.MaterialAlertDialog_rounded)
            .setTitle("${context.getString(R.string.remove_scenario)} $name")
            .setPositiveButton(R.string.ok) { _, _ ->
                viewModel.removeScenario(name)
                notifyItemRemoved(position)
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
}