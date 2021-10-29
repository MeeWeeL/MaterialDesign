package com.meeweel.materialdesign.ui.other

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.meeweel.materialdesign.R
import com.meeweel.materialdesign.databinding.OtherFragmentsActivityLayoutBinding
import com.meeweel.materialdesign.ui.other.fragments.MoonFragment
import com.meeweel.materialdesign.ui.other.fragments.PlanetFragment
import com.meeweel.materialdesign.ui.other.fragments.WeatherFragment
import kotlinx.android.synthetic.main.other_fragments_activity_layout.*

class OthersActivity : AppCompatActivity() {

    lateinit var binding: OtherFragmentsActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OtherFragmentsActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        nav_menu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.moon -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.other_frame, MoonFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.planet -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.other_frame, PlanetFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.weather -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.other_frame, WeatherFragment())
                        .commitAllowingStateLoss()
                    true
                }
                else -> true
            }
        }
        nav_menu.selectedItemId = R.id.moon
        val badge = nav_menu.getOrCreateBadge(R.id.moon)
        badge.maxCharacterCount = 2
        badge.number = 1

        nav_menu.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.moon -> {
                    badge.number += 1
                }
                R.id.planet -> {

                }
                R.id.weather -> {

                }
            }
        }
    }
}