package com.example.eletriccars.UI.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.eletriccars.UI.CarFragment
import com.example.eletriccars.UI.FavoritFragment

class TabAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return  2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CarFragment()
            1 -> FavoritFragment()
            else -> {CarFragment()}
        }
    }

}