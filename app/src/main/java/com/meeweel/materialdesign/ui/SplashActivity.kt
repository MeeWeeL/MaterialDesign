package com.meeweel.materialdesign.ui


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.meeweel.materialdesign.R
import com.meeweel.materialdesign.databinding.SplashLayoutBinding

class SplashActivity : AppCompatActivity() {

    private var handler = Handler()

    lateinit var bind: SplashLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = SplashLayoutBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.splashImage.setImageResource(R.drawable.anig)

        bind.splashImage
            .animate()
            .rotationY(1260f)
            .setDuration(3000)
            .setInterpolator(BounceInterpolator())
            .start()

        handler.postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}