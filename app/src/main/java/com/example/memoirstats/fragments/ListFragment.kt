package com.example.memoirstats.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.memoirstats.R
import com.example.memoirstats.adapters.ScenarioRecyclerViewAdapter
import com.example.memoirstats.model.view.MemoirViewModel

/**
 * A fragment representing a list of Scenarios.
 */
class ListFragment : Fragment() {

    private val viewModel: MemoirViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = ScenarioRecyclerViewAdapter(viewModel.scenarios, findNavController())
            }
        }
        return view
    }
}