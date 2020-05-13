package io.github.coderbuck.boring.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import io.github.coderbuck.boring.R
import io.github.coderbuck.boring.adapter.ViewPagerAdapter
import io.github.coderbuck.boring.bean.EmTab
import io.github.coderbuck.boring.databinding.ActivityMainBinding
import io.github.coderbuck.boring.util.contentView

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.bind(contentView)
        setSupportActionBar(binding.toolbar)

        binding.apply {
            viewPager.adapter = ViewPagerAdapter(this@MainActivity)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = EmTab.values()[position].title
            }.attach()
        }

    }
}
