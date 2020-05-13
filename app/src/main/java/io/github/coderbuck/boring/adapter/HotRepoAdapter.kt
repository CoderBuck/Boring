package io.github.coderbuck.boring.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.coderbuck.boring.R
import io.github.coderbuck.boring.bean.github.HotRepoItem
import io.github.coderbuck.boring.bean.github.HotRepoList
import io.github.coderbuck.boring.databinding.ItemGithubRepoBinding
import io.github.coderbuck.boring.util.DeepLinkUtils
import io.github.coderbuck.boring.util.inflate


class HotRepoAdapter : RecyclerView.Adapter<HotRepoAdapter.Holder>() {

    val items = HotRepoList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = inflate(parent, R.layout.item_github_repo)
        return Holder(ItemGithubRepoBinding.bind(view))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val hotRepoItem = items[position]
        holder.hotRepo = hotRepoItem
        holder.binding.apply {
            name.text = hotRepoItem.name
            lang.text = hotRepoItem.language
            starCount.text = hotRepoItem.currentPeriodStars.toString()
            description.text = hotRepoItem.description
        }
    }

    class Holder(val binding: ItemGithubRepoBinding) : RecyclerView.ViewHolder(binding.root) {

        lateinit var hotRepo: HotRepoItem

        init {
            binding.root.setOnClickListener {
                val context = binding.root.context
                val link = hotRepo.url
//                CustomTabUtils.open(context, link)
                DeepLinkUtils.open(context, link)
            }
        }
    }
}