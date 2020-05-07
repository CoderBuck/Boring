package io.github.coderbuck.boring.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import io.github.coderbuck.boring.adapter.ViewPagerAdapter
import io.github.coderbuck.boring.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val tabTitles = listOf("知乎", "微博", "Github")
        binding.apply {
            viewPager.adapter = ViewPagerAdapter(this@MainActivity)
            TabLayoutMediator(tabLayout,viewPager) { tab, position ->
                tab.text = tabTitles[position]
            }.attach()
        }
    }
}
