package com.example.memoirstats.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.memoirstats.databinding.FragmentTotalStatsBinding
import com.example.memoirstats.model.Player
import com.example.memoirstats.model.view.MemoirViewModel
import com.example.memoirstats.model.view.TotalViewModel

/**
 * A simple [Fragment] subclass showing total stats.
 */
class TotalStatsFragment : Fragment() {

    private var _binding: FragmentTotalStatsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: MemoirViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTotalStatsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.totalResults.total = TotalViewModel(viewModel)
        binding.totalResults.attacker = TotalViewModel(viewModel, Player.Attacker.filter)
        binding.totalResults.defender = TotalViewModel(viewModel, Player.Defender.filter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}