package io.github.coderbuck.boring.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.coderbuck.boring.R
import io.github.coderbuck.boring.bean.github.HotRepoItem
import io.github.coderbuck.boring.bean.github.HotRepoList
import io.github.coderbuck.boring.databinding.ItemGithubRepoBinding
import io.github.coderbuck.boring.util.DeepLinkUtils


class HotRepoAdapter : RecyclerView.Adapter<HotRepoAdapter.Holder>() {

    val items = HotRepoList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent, R.layout.item_github_repo)
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

    class Holder(parent: ViewGroup, id: Int) : BaseHolder(parent, id) {
        val binding = ItemGithubRepoBinding.bind(itemView)!!
        lateinit var hotRepo: HotRepoItem

        init {
            binding.root.setOnClickListener {
                val context = binding.root.context
                val link = hotRepo.url
                DeepLinkUtils.open(context, link)
            }
        }
    }
}