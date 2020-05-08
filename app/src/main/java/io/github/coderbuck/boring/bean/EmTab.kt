package io.github.coderbuck.boring.bean

import androidx.annotation.ColorRes
import io.github.coderbuck.boring.R

enum class EmTab(val title: String, @ColorRes val color: Int) {

    ZHIHU("知乎", R.color.zhihu),
    WEIBO("微博", R.color.weibo),
    GITHUB("Github", R.color.github),

}