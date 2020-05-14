package io.github.coderbuck.boring.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.coderbuck.boring.R
import io.github.coderbuck.boring.bean.weibo.WeiboHot
import io.github.coderbuck.boring.bean.weibo.WeiboHotList
import io.github.coderbuck.boring.databinding.ItemWeiboHotBinding
import io.github.coderbuck.boring.util.DeepLinkUtils
import io.github.coderbuck.boring.util.inflate

class WeiboHotAdapter : RecyclerView.Adapter<WeiboHotAdapter.Holder>() {
    val items = WeiboHotList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent, R.layout.item_weibo_hot)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.item = items[position]
        holder.binding.textView.text = items[position].name
    }

    class Holder(parent: ViewGroup, id: Int) : BaseHolder(parent, id) {
        val binding = ItemWeiboHotBinding.bind(itemView)!!
        lateinit var item: WeiboHot

        init {
            binding.root.setOnClickListener {
                val context = binding.root.context
                val link = "https://s.weibo.com" + item.href
//                CustomTabUtils.open(context, link)
                DeepLinkUtils.open(context, link)
            }
        }
    }
}