package com.example.memoirstats.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.memoirstats.fragments.ListFragment
import com.example.memoirstats.fragments.TotalStatsFragment

class BaseFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position % 2 == 0) TotalStatsFragment() else ListFragment()
    }
}