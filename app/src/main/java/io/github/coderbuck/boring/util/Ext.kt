package io.github.coderbuck.boring.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

/**
 * 拓展类
 */

/* Activity */
val AppCompatActivity.contentView: View
    get() = (findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)

fun <T : ViewModel> AppCompatActivity.provideViewModel(clazz: Class<T>) = ViewModelProvider(this)[clazz]


/* Fragment */
fun <T : ViewModel> Fragment.provideViewModel(clazz: Class<T>) = ViewModelProvider(this)[clazz]


/* RecyclerView */
fun <VH : RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.inflate(parent: ViewGroup, @LayoutRes layoutId: Int): View {
    return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
}

/**
 * Reduces drag sensitivity of [ViewPager2] widget
 */
fun ViewPager2.reduceDragSensitivity() {
    val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
    recyclerViewField.isAccessible = true
    val recyclerView = recyclerViewField.get(this) as RecyclerView

    val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
    touchSlopField.isAccessible = true
    val touchSlop = touchSlopField.get(recyclerView) as Int
    touchSlopField.set(recyclerView, touchSlop * 2)       // "8" was obtained experimentally
}
