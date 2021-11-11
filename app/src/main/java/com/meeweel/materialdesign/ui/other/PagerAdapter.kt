package com.meeweel.materialdesign.ui.other

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.meeweel.materialdesign.ui.other.fragments.MoonFragment
import com.meeweel.materialdesign.ui.other.fragments.PlanetFragment
import com.meeweel.materialdesign.ui.other.fragments.WeatherFragment

private const val TODAY = 0
private const val YESTERDAY = 1
private const val BEFORE_YESTERDAY = 2

class PagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragments = arrayOf(MoonFragment(), PlanetFragment(), WeatherFragment())

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> fragments[TODAY]
            1 -> fragments[YESTERDAY]
            2 -> fragments[BEFORE_YESTERDAY]
            else -> fragments[TODAY]
        }
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return null
    }
}