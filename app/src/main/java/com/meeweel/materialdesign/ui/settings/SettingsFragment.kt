package com.meeweel.materialdesign.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import com.meeweel.materialdesign.R
import com.meeweel.materialdesign.databinding.SettingsFragmentLayoutBinding
import com.meeweel.materialdesign.ui.ThemeHolder
import com.meeweel.materialdesign.ui.picture.PictureOfTheDayFragment

class SettingsFragment : Fragment() {


    private var _binding: SettingsFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.setTheme(ThemeHolder.theme)
        _binding = SettingsFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val switch: Switch = binding.switcher
        if (ThemeHolder.theme == R.style.SecondTheme) switch.isChecked = true
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                ThemeHolder.theme = R.style.SecondTheme
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, PictureOfTheDayFragment.newInstance())
                    ?.commitNow()
            }else{
                ThemeHolder.theme = R.style.DefaultTheme
            }
        }
    }
    private fun setCheckedChangeListener() {
    }
}
