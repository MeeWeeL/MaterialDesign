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

private const val TODAY = 0
private const val YESTERDAY = 1
private const val BEFORE_YESTERDAY = 2

class PagerActivity  : AppCompatActivity() {

    lateinit var binding: OtherFragmentLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OtherFragmentLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        view_pager.adapter = PagerAdapter(supportFragmentManager)
        tab_layout.setupWithViewPager(view_pager)
        setTab(TODAY)

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                setTab(position)
            }
        })
    }

    private fun setTab(position: Int) {
        val layoutInflater = LayoutInflater.from(this@PagerActivity)

        tab_layout.getTabAt(TODAY)?.customView = null
        tab_layout.getTabAt(YESTERDAY)?.customView = null
        tab_layout.getTabAt(BEFORE_YESTERDAY)?.customView = null

        setCustomTab(position, layoutInflater)
    }

    private fun setCustomTab(position: Int, inflator: LayoutInflater) {
        val cust = inflator.inflate(R.layout.custom_tab_moon, null)
        cust.findViewById<AppCompatTextView>(R.id.custom_tab).text = when (position) {
            TODAY -> "Moon"
            YESTERDAY -> "Planet"
            BEFORE_YESTERDAY -> "Whether"
            else -> "Error"
        }
        cust.findViewById<AppCompatTextView>(R.id.custom_tab)
            .setTextColor(
                ContextCompat.getColor(
                    this@PagerActivity,
                    R.color.colorAccent
                )
            )
        tab_layout.getTabAt(TODAY)?.customView = if (position == TODAY) cust else flater(TODAY)
        tab_layout.getTabAt(YESTERDAY)?.customView = if (position == YESTERDAY) cust else flater(YESTERDAY)
        tab_layout.getTabAt(BEFORE_YESTERDAY)?.customView = if (position == BEFORE_YESTERDAY) cust else flater(BEFORE_YESTERDAY)
    }

    private fun flater(tabb: Int) : View {
        val preTab = layoutInflater.inflate(R.layout.custom_tab_moon,null)

        when (tabb) {
            TODAY -> {
                preTab.custom_tab.setText("Moon")
                preTab.custom_tab.setCompoundDrawables(
                    AppCompatResources.getDrawable(this, R.drawable.ic_moon),
                    null, null, null
                )
                return preTab
            }
            YESTERDAY -> {

                preTab.custom_tab.setText("Planet")
                preTab.custom_tab.setCompoundDrawables(
                    AppCompatResources.getDrawable(this, R.drawable.ic_planet),
                    null, null, null
                )
                return preTab
            }
            BEFORE_YESTERDAY -> {

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