package com.example.memoirstats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.memoirstats.databinding.FragmentScenarioBinding
import com.example.memoirstats.model.Player
import com.example.memoirstats.model.view.CurrentViewModel
import com.example.memoirstats.model.view.MemoirViewModel

private const val ARG_SCENARIO = "scenario"

/**
 * A simple [Fragment] subclass aggregating rolls for a scenario.
 */
class ScenarioFragment : Fragment() {

    private var _binding: FragmentScenarioBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: MemoirViewModel by activityViewModels()
    private lateinit var scenario: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            scenario = it.getString(ARG_SCENARIO)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScenarioBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.currentResults.total = CurrentViewModel(viewModel)
        binding.currentResults.attacker = CurrentViewModel(viewModel, Player.Attacker.filter)
        binding.currentResults.defender = CurrentViewModel(viewModel, Player.Defender.filter)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAttacker.setOnClickListener {
            val bundle = RollFragment.makeBundle(scenario, Player.Attacker)
            findNavController().navigate(R.id.action_ScenarioFragment_to_RollFragment, bundle)
        }

        binding.buttonDefender.setOnClickListener {
            val bundle = RollFragment.makeBundle(scenario, Player.Defender)
            findNavController().navigate(R.id.action_ScenarioFragment_to_RollFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new bundle for
         * this fragment using the provided parameters.
         *
         * @param scenario Current scenario name.
         * @return A new instance of Bundle.
         */
        @JvmStatic
        fun makeBundle(scenario: String) = Bundle().apply {
            putString(ARG_SCENARIO, scenario)
        }

    }
}