package com.meeweel.materialdesign.ui

import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import com.meeweel.materialdesign.R
import com.meeweel.materialdesign.ui.picture.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(ThemeHolder.theme)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }
    fun changeTheme(@StyleRes th: Int) {
        ThemeHolder.theme = th
        recreate()
    }
}

object ThemeHolder {
    var theme = R.style.DefaultTheme
}
