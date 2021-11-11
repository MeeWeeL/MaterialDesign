package com.meeweel.materialdesign.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.meeweel.materialdesign.R
import com.meeweel.materialdesign.databinding.MainActivityBinding
import com.meeweel.materialdesign.ui.picture.PictureOfTheDayFragment
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    lateinit var binding: ViewBinding
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
        animate_btn.setOnClickListener {
            ObjectAnimator.ofFloat(animate_btn, "translationX", 400f).setDuration(1000).start()
            Thread {
                Thread.sleep(1000)
                runOnUiThread {
                    ObjectAnimator.ofFloat(animate_btn, "rotationY", animate_btn.rotationY + 360f).setDuration(1000).start()
                }
                Thread.sleep(1000)
                runOnUiThread {
                    ObjectAnimator.ofFloat(animate_btn, "translationY", 3360f).setDuration(1000).start()
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
