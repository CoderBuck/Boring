package io.github.coderbuck.boring.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.github.coderbuck.boring.fragment.GithubFragment
import io.github.coderbuck.boring.fragment.ZhihuFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return ZhihuFragment()
        }

        return GithubFragment()
    }
}