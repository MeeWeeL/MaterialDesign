package com.meeweel.materialdesign.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.meeweel.materialdesign.R
import com.meeweel.materialdesign.databinding.MainActivityBinding
import com.meeweel.materialdesign.ui.picture.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setTheme(ThemeHolder.theme)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }

        binding.animateBtn.setOnClickListener {
            ObjectAnimator.ofFloat(binding.animateBtn, "translationX", 400f).setDuration(1000).start()
            Thread {
                Thread.sleep(1000)
                runOnUiThread {
                    ObjectAnimator.ofFloat(binding.animateBtn, "rotationY", binding.animateBtn.rotationY + 360f).setDuration(1000).start()
                }
                Thread.sleep(1000)
                runOnUiThread {
                    ObjectAnimator.ofFloat(binding.animateBtn, "translationY", 3360f).setDuration(1000).start()
                }
            }.start()
        }
    }
    fun changeTheme(@StyleRes th: Int) {
        ThemeHolder.theme = th
        recreate()
    }

    fun image(view: android.view.View) {}

}
object ThemeHolder {
    var theme = R.style.DefaultTheme
}
