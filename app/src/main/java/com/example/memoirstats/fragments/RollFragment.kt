package com.example.memoirstats.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Spinner
import androidx.core.view.children
import androidx.core.view.setPadding
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.memoirstats.adapters.DiceAdapter
import com.example.memoirstats.databinding.FragmentRollBinding
import com.example.memoirstats.model.DiceSide
import com.example.memoirstats.model.Player
import com.example.memoirstats.model.Roll
import com.example.memoirstats.model.view.MemoirViewModel

private const val ARG_PLAYER = "player"
private const val ARG_SCENARIO = "scenario"
/**
 * A simple [Fragment] subclass representing single dice roll.
 */
class RollFragment : Fragment() {

    private var _binding: FragmentRollBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: MemoirViewModel by activityViewModels()

    private lateinit var player: Player
    private lateinit var scenario: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            player = Player.valueOf(it.getString(ARG_PLAYER)!!)
            scenario = it.getString(ARG_SCENARIO)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRollBinding.inflate(inflater, container, false)
        binding.diceNumber.addTextChangedListener { text ->
            val context = activity?.baseContext!!
            binding.diceGroup.removeAllViews()
            val diceNumber = text.toString().let { if (it.isBlank()) 0 else it.toInt() }
            for (i in 0..<diceNumber) {
                val diceSides = Spinner(context)
                diceSides.id = 123 + i
                diceSides.adapter = DiceAdapter(activity?.baseContext!!)
                diceSides.background = null
                diceSides.setPadding(10)
                val rowSpec = GridLayout.spec(i / 2)
                val columnSpan = if (i == diceNumber - 1)
                    GridLayout.spec(i % 2, 1 + diceNumber % 2)
                else
                    GridLayout.spec(i % 2)
                val layoutParams = GridLayout.LayoutParams(rowSpec, columnSpan)
                binding.diceGroup.addView(diceSides, layoutParams)
            }
        }
        binding.save.setOnClickListener { _ ->
            val results =
                binding.diceGroup.children.map { (it as Spinner).selectedItem as DiceSide }.toList()
            val hits = binding.hitsNumber.text.toString().let { if (it.isBlank()) 0 else it.toInt() }
            val roll = Roll(results, hits, player)
            viewModel.addRoll(roll)
            findNavController().navigateUp()
        }
        // Inflate the layout for this fragment
        return binding.root
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
         * @param player Player making this roll.
         * @param scenario Current scenario name.
         * @return A new instance of Bundle.
         */
        @JvmStatic
        fun makeBundle(scenario: String, player: Player) =
            Bundle().apply {
                putString(ARG_PLAYER, player.name)
                putString(ARG_SCENARIO, scenario)
            }

    }
}