package io.github.coderbuck.boring.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.coderbuck.boring.R
import io.github.coderbuck.boring.bean.weibo.WeiboHot
import io.github.coderbuck.boring.bean.weibo.WeiboHotList
import io.github.coderbuck.boring.databinding.ItemWeiboHotBinding
import saschpe.android.customtabs.CustomTabsHelper
import saschpe.android.customtabs.WebViewFallback

class WeiboHotAdapter : RecyclerView.Adapter<WeiboHotAdapter.Holder>() {
    val items = WeiboHotList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = ItemWeiboHotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.item = items[position]
        holder.binding.textView.text = items[position].name
    }

    class Holder(val binding: ItemWeiboHotBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var item: WeiboHot

        init {
            val context = binding.root.context
            val customTabsIntent = CustomTabsIntent.Builder()
                .addDefaultShareMenuItem()
                .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setShowTitle(true)
                .build()
            CustomTabsHelper.addKeepAliveExtra(context, customTabsIntent.intent)
            binding.root.setOnClickListener {
                val url = "https://s.weibo.com" + item.href
                CustomTabsHelper.openCustomTab(
                    context,
                    customTabsIntent,
                    Uri.parse(url),
                    WebViewFallback()
                )
            }
        }
    }
}