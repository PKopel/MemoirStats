package com.example.memoirstats.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.memoirstats.databinding.NewRollTableBinding
import com.example.memoirstats.model.DiceSide
import com.example.memoirstats.model.Player
import com.example.memoirstats.model.Roll
import com.example.memoirstats.model.view.MemoirViewModel
import com.example.memoirstats.model.view.QtyViewModel

private const val ARG_PLAYER = "player"
private const val ARG_SCENARIO = "scenario"

/**
 * A simple [Fragment] subclass representing single dice roll.
 */
class RollFragment : Fragment() {

    private var _binding: NewRollTableBinding? = null

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
        _binding = NewRollTableBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        val infantry = QtyViewModel().also { binding.infantry = it }
        val grenade = QtyViewModel().also { binding.grenade = it }
        val tank = QtyViewModel().also { binding.tank = it }
        val flag = QtyViewModel().also { binding.flag = it }
        val star = QtyViewModel().also { binding.star = it }
        val hits = QtyViewModel().also { binding.hits = it }

        binding.save.setOnClickListener { _ ->
            val results = List(infantry.quantity.value ?: 0) { DiceSide.Infantry } +
                    List(grenade.quantity.value ?: 0) { DiceSide.Grenade } +
                    List(tank.quantity.value ?: 0) { DiceSide.Tank } +
                    List(flag.quantity.value ?: 0) { DiceSide.Flag } +
                    List(star.quantity.value ?: 0) { DiceSide.Star }
            val roll = Roll(results, hits.quantity.value ?: 0, player)
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