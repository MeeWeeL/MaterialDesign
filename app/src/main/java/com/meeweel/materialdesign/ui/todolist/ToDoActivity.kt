package com.meeweel.materialdesign.ui.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.meeweel.materialdesign.R
import com.meeweel.materialdesign.databinding.ToDoActivityBinding
import com.meeweel.materialdesign.ui.todolist.todorepository.defaultQuest
import kotlin.concurrent.thread

class ToDoActivity : AppCompatActivity() {

    private val viewModel: ToDoViewModel by lazy {
        ViewModelProvider(this).get(ToDoViewModel::class.java)
    }
    private lateinit var binding: ToDoActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ToDoActivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        savedInstanceState?.let {} ?: refresh()
        binding.navBar.background = null
        binding.fab.setOnClickListener {
            supportFragmentManager.apply {
                beginTransaction()
                    .replace(R.id.container, ToDoCreateFragment.newInstance(Bundle().apply {
                        putParcelable(ToDoCreateFragment.BUNDLE_EXTRA, defaultQuest)
                    }))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
        binding.navBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.main_fragment_nav -> refresh(ToDoMainFragment())
            }
            true
        }

    }
    private fun refresh(fragment: Fragment = ToDoMainFragment()) {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, fragment)
            .commitNow()
    }

    override fun onDestroy() {
        viewModel.sync()
        thread {
            Thread.sleep(1000)
            runOnUiThread {
                super.onDestroy()
            }
        }
    }
    override fun onDetachedFromWindow() {
        viewModel.sync()
        super.onDetachedFromWindow()
    }
}