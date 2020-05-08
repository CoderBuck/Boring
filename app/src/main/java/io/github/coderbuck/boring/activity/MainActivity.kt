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


            // 根据tab 改变颜色，效果不好
//            var lastColor = ContextCompat.getColor(this@MainActivity, R.color.zhihu)
//            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//                override fun onTabSelected(tab: TabLayout.Tab) {
//                    val position = tab.position
//                    val startColor = lastColor
//                    val endColor = ContextCompat.getColor(this@MainActivity, EmTab.values()[position].color)
//                    ValueAnimator.ofArgb(startColor, endColor).run {
//                        duration = 400
//                        interpolator = FastOutSlowInInterpolator()
//                        addUpdateListener {
//                            appbar.setBackgroundColor(it.animatedValue as Int)
//                            toolbar.setBackgroundColor(it.animatedValue as Int)
//                            tabLayout.setBackgroundColor(it.animatedValue as Int)
//                        }
//                        start()
//                    }
//                    lastColor = endColor
//                }
//
//                override fun onTabReselected(tab: TabLayout.Tab?) {
//                }
//
//                override fun onTabUnselected(tab: TabLayout.Tab?) {
//                }
//            })
        }
    }
}
