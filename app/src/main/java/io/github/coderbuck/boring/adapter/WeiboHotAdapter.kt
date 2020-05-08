package io.github.coderbuck.boring.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.coderbuck.boring.bean.weibo.WeiboHot
import io.github.coderbuck.boring.bean.weibo.WeiboHotList
import io.github.coderbuck.boring.databinding.ItemWeiboHotBinding
import io.github.coderbuck.boring.util.DeepLinkUtils

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
            binding.root.setOnClickListener {
                val context = binding.root.context
                val link = "https://s.weibo.com" + item.href
//                CustomTabUtils.open(context, link)
                DeepLinkUtils.open(context, link)
            }
        }
    }
}