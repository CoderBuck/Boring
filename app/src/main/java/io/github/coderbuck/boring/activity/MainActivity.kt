package io.github.coderbuck.boring.activity

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.github.coderbuck.boring.R
import io.github.coderbuck.boring.adapter.ViewPagerAdapter
import io.github.coderbuck.boring.bean.EmTab
import io.github.coderbuck.boring.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.fragment_rv.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.apply {
            viewPager.adapter = ViewPagerAdapter(this@MainActivity)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = EmTab.values()[position].title
            }.attach()

        }
    }
}
