package com.meeweel.materialdesign.ui.motion

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import androidx.fragment.app.Fragment
import com.meeweel.materialdesign.databinding.MotionFragmentLayoutBinding
import com.meeweel.materialdesign.databinding.SettingsFragmentLayoutBinding
import kotlinx.android.synthetic.main.main_activity.*

class MotionFragment : Fragment() {


    var pic1 = false
    var pic2 = false
    var pic3 = false
    var pic4 = false
    var counter = 1
    private var _binding: MotionFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MotionFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pic1.setOnClickListener {
            if (!pic1) {
                it.animate().setDuration(500).rotationYBy(720f)
            }
        }
        binding.pic2.setOnClickListener {
            if (!pic2) {
                it.animate().setDuration(500).rotationYBy(720f)
            }
        }
        binding.pic3.setOnClickListener {
            if (!pic3) {
                it.animate().setDuration(500).rotationYBy(720f)
            }
        }
        binding.pic4.setOnClickListener {
            if (!pic4) {
                it.animate().setDuration(500).rotationYBy(720f)
            }
        }
        binding.motionBtn.setOnClickListener {
            when (counter) {
                1 -> {
                    ObjectAnimator.ofFloat(binding.motionBtn, "rotationY", 360f).setDuration(1000).start()
                    counter++
                }
                2 -> {
                    ObjectAnimator.ofFloat(binding.pic1, "translationX", 400f).setDuration(1000).start()
                    ObjectAnimator.ofFloat(binding.pic2, "translationX", -400f).setDuration(1000).start()
                    ObjectAnimator.ofFloat(binding.pic3, "translationY", 400f).setDuration(1000).start()
                    ObjectAnimator.ofFloat(binding.pic4, "translationY", -400f).setDuration(1000).start()
                    Thread {
                        Thread.sleep(1000)
                        requireActivity().runOnUiThread {
                            ObjectAnimator.ofFloat(binding.pic1, "translationY", -400f).setDuration(1000).start()
                            ObjectAnimator.ofFloat(binding.pic2, "translationY", 400f).setDuration(1000).start()
                            ObjectAnimator.ofFloat(binding.pic3, "translationX", 400f).setDuration(1000).start()
                            ObjectAnimator.ofFloat(binding.pic4, "translationX", -400f).setDuration(1000).start()
                        }
                    }.start()
                    counter++
                }
                else -> {
                    binding.pic1.animate().setDuration(1000).rotationYBy(-360f)
                    ObjectAnimator.ofFloat(binding.pic2, "rotationY", binding.pic2.rotationY-360f).setDuration(1000).start()
                    ObjectAnimator.ofFloat(binding.pic3, "rotationY", binding.pic3.rotationY+360f).setDuration(1000).start()
                    ObjectAnimator.ofFloat(binding.pic4, "rotationY", binding.pic4.rotationY-360f).setDuration(1000).start()
                }
            }
        }
    }

}