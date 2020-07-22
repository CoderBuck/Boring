package io.github.coderbuck.boring.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import io.github.coderbuck.boring.R
import io.github.coderbuck.boring.adapter.ViewPagerAdapter
import io.github.coderbuck.boring.bean.EmTab
import io.github.coderbuck.boring.databinding.ActivityMainBinding
import io.github.coderbuck.boring.util.reduceDragSensitivity
import me.buck.viewbindingktx.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        binding.apply {
            viewPager.reduceDragSensitivity()
            viewPager.adapter = ViewPagerAdapter(this@MainActivity)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = EmTab.values()[position].title
            }.attach()
        }
    }
}
