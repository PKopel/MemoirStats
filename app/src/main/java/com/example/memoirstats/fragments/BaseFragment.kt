package com.example.memoirstats.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.memoirstats.R
import com.example.memoirstats.adapters.BaseFragmentAdapter
import com.example.memoirstats.databinding.FragmentBaseBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class BaseFragment : Fragment() {

    private var _binding: FragmentBaseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var baseFragmentAdapter: BaseFragmentAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        baseFragmentAdapter = BaseFragmentAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = baseFragmentAdapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}