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
import com.meeweel.materialdesign.databinding.CustomTabMoonBinding
import com.meeweel.materialdesign.databinding.OtherFragmentLayoutBinding
import kotlinx.android.synthetic.main.custom_tab_moon.view.*

private const val TODAY = 0
private const val YESTERDAY = 1
private const val BEFORE_YESTERDAY = 2

class PagerActivity  : AppCompatActivity() {

    lateinit var binding: OtherFragmentLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OtherFragmentLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPager.adapter = PagerAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        setTab(TODAY)

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

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

        binding.tabLayout.getTabAt(TODAY)?.customView = null
        binding.tabLayout.getTabAt(YESTERDAY)?.customView = null
        binding.tabLayout.getTabAt(BEFORE_YESTERDAY)?.customView = null

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
        binding.tabLayout.getTabAt(TODAY)?.customView = if (position == TODAY) cust else flater(TODAY)
        binding.tabLayout.getTabAt(YESTERDAY)?.customView = if (position == YESTERDAY) cust else flater(YESTERDAY)
        binding.tabLayout.getTabAt(BEFORE_YESTERDAY)?.customView = if (position == BEFORE_YESTERDAY) cust else flater(BEFORE_YESTERDAY)
    }

    private fun flater(tabb: Int) : View {
        val bind = CustomTabMoonBinding.inflate(layoutInflater)
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