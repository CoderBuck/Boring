package io.github.coderbuck.boring.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.coderbuck.boring.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.tabLayout.apply {
            addTab(newTab().apply { text = "知乎" })
            addTab(newTab().apply { text = "微博" })
            addTab(newTab().apply { text = "Github" })
        }
    }
}
