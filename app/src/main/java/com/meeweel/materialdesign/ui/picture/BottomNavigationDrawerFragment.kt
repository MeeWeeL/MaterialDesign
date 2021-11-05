package com.meeweel.materialdesign.ui.picture

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.meeweel.materialdesign.R
import com.meeweel.materialdesign.ui.chips.ChipsFragment
import com.meeweel.materialdesign.ui.motion.MotionFragment
import com.meeweel.materialdesign.ui.other.OthersActivity
import com.meeweel.materialdesign.ui.other.PagerActivity
import com.meeweel.materialdesign.ui.settings.SettingsFragment
import kotlinx.android.synthetic.main.bottom_navigation_layout.*

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_navigation_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navigation_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.settings_nav -> activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, SettingsFragment())?.addToBackStack(null)?.commit()
                R.id.bottom_nav -> {
                    val intent = Intent(requireContext(), OthersActivity::class.java)
                    startActivity(intent)
                }
                R.id.pager_nav -> {
                    val intent = Intent(requireContext(), PagerActivity::class.java)
                    startActivity(intent)
                }
                R.id.motion_nav -> activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, MotionFragment())?.addToBackStack(null)?.commit()
            }
            true
        }
    }
}
