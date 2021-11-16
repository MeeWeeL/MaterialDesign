package com.meeweel.materialdesign.ui.other

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.meeweel.materialdesign.R
import com.meeweel.materialdesign.databinding.OtherFragmentsActivityLayoutBinding
import com.meeweel.materialdesign.ui.other.fragments.MoonFragment
import com.meeweel.materialdesign.ui.other.fragments.PlanetFragment
import com.meeweel.materialdesign.ui.other.fragments.WeatherFragment

class OthersActivity : AppCompatActivity() {

    lateinit var binding: OtherFragmentsActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OtherFragmentsActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navMenu.setOnNavigationItemSelectedListener { item ->
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
        binding.navMenu.selectedItemId = R.id.moon
        val badge = binding.navMenu.getOrCreateBadge(R.id.moon)
        badge.maxCharacterCount = 2
        badge.number = 1

        binding.navMenu.setOnNavigationItemReselectedListener { item ->
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