package com.example.memoirstats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.memoirstats.databinding.FragmentBaseBinding
import com.example.memoirstats.model.Player
import com.example.memoirstats.model.view.MemoirViewModel
import com.example.memoirstats.model.view.TotalViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class BaseFragment : Fragment() {

    private var _binding: FragmentBaseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: MemoirViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBaseBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.totalResults.total = TotalViewModel(viewModel)
        binding.totalResults.attacker = TotalViewModel(viewModel, Player.attackerFilter)
        binding.totalResults.defender = TotalViewModel(viewModel, Player.defenderFilter)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNewScenario.setOnClickListener {
            val name = "test scenario"
            viewModel.addScenario(name)
            val bundle = ScenarioFragment.makeBundle(name)
            findNavController().navigate(R.id.action_BaseFragment_to_ScenarioFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}