package com.meeweel.materialdesign.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.meeweel.materialdesign.R
import com.meeweel.materialdesign.databinding.SettingsFragmentLayoutBinding
import com.meeweel.materialdesign.ui.MainActivity
import com.meeweel.materialdesign.ui.ThemeHolder

class SettingsFragment : Fragment() {


    private var _binding: SettingsFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SettingsFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val switch: Switch = binding.switcher
        if (ThemeHolder.theme == R.style.SecondTheme) switch.isChecked = true
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            (requireActivity() as? MainActivity)?.let {
                if (isChecked) {
                    it.changeTheme(R.style.SecondTheme)
                } else {
                    it.changeTheme(R.style.DefaultTheme)
                }
            }
        }
    }
}