package io.github.coderbuck.boring.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.coderbuck.boring.R
import io.github.coderbuck.boring.bean.HotRepoItem
import io.github.coderbuck.boring.bean.HotRepoList
import io.github.coderbuck.boring.databinding.ItemGithubRepoBinding
import saschpe.android.customtabs.CustomTabsHelper
import saschpe.android.customtabs.WebViewFallback


class HotRepoAdapter : RecyclerView.Adapter<HotRepoAdapter.Holder>() {

    val items = HotRepoList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = ItemGithubRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val hotRepoItem = items[position]
        holder.hotRepo = hotRepoItem
        holder.binding.name.text = hotRepoItem.name
        holder.binding.description.text = hotRepoItem.description
    }

    class Holder(val binding: ItemGithubRepoBinding) : RecyclerView.ViewHolder(binding.root) {

        lateinit var hotRepo: HotRepoItem

        init {
            val context = binding.root.context
            val customTabsIntent = CustomTabsIntent.Builder()
                .addDefaultShareMenuItem()
                .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setShowTitle(true)
                .build()
            CustomTabsHelper.addKeepAliveExtra(context, customTabsIntent.intent)

            binding.root.setOnClickListener {
                CustomTabsHelper.openCustomTab(
                    context,
                    customTabsIntent,
                    Uri.parse(hotRepo.url),
                    WebViewFallback()
                )
            }
        }

    }
}