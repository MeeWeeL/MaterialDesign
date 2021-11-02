package com.meeweel.materialdesign.ui.other

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.meeweel.materialdesign.R
import com.meeweel.materialdesign.databinding.OtherFragmentLayoutBinding
import kotlinx.android.synthetic.main.custom_tab_moon.view.*
import kotlinx.android.synthetic.main.other_fragment_layout.*
import kotlinx.android.synthetic.main.other_fragment_layout.view.*
import kotlinx.android.synthetic.main.other_fragments_activity_layout.*

class PagerActivity  : AppCompatActivity() {

    lateinit var binding: OtherFragmentLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OtherFragmentLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        view_pager.adapter = PagerAdapter(supportFragmentManager)
        tab_layout.setupWithViewPager(view_pager)
        setTab(0)

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                setTab(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                TODO("Not yet implemented")
            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }
        })
    }

    private fun setTab(position: Int) {
        val layoutInflater = LayoutInflater.from(this@PagerActivity)

        tab_layout.getTabAt(0)?.customView = null
        tab_layout.getTabAt(1)?.customView = null
        tab_layout.getTabAt(2)?.customView = null

        setCustomTab(position, layoutInflater)
    }

    private fun setCustomTab(position: Int, inflator: LayoutInflater) {
        val cust = inflator.inflate(R.layout.custom_tab_moon, null)
        cust.findViewById<AppCompatTextView>(R.id.custom_tab)
            .setTextColor(
                ContextCompat.getColor(
                    this@PagerActivity,
                    R.color.colorAccent
                )
            )
        tab_layout.getTabAt(0)?.customView = if (position == 0) cust else flater(0)
        tab_layout.getTabAt(1)?.customView = if (position == 1) cust else flater(1)
        tab_layout.getTabAt(2)?.customView = if (position == 2) cust else flater(2)
    }

    private fun flater(tabb: Int) : View {
        val preTab = layoutInflater.inflate(R.layout.custom_tab_moon,null)

        when (tabb) {
            0 -> {
                preTab.custom_tab.setText("Moon")
                preTab.custom_tab.setCompoundDrawables(
                    AppCompatResources.getDrawable(this, R.drawable.ic_moon),
                    null, null, null
                )
                return preTab
            }
            1 -> {

                preTab.custom_tab.setText("Planet")
                preTab.custom_tab.setCompoundDrawables(
                    AppCompatResources.getDrawable(this, R.drawable.ic_planet),
                    null, null, null
                )
                return preTab
            }
            2 -> {

                preTab.custom_tab.setText("Weather")
                preTab.custom_tab.setCompoundDrawables(
                    AppCompatResources.getDrawable(this, R.drawable.ic_weather),
                    null, null, null
                )
                return preTab
            }
            else -> return preTab
        }

    }
}