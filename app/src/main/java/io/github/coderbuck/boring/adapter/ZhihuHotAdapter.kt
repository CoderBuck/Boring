package io.github.coderbuck.boring.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.coderbuck.boring.bean.ZhihuHotItem
import io.github.coderbuck.boring.databinding.ItemZhihuBinding
import io.github.coderbuck.boring.util.DeepLinkUtils

class ZhihuHotAdapter : RecyclerView.Adapter<ZhihuHotAdapter.Holder>() {
    val items = mutableListOf<ZhihuHotItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = ItemZhihuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]
        holder.item = item
        holder.binding.apply {
            title.text = item.title
            hot.text = item.metrics

            if (item.img.isBlank()) {
                img.visibility = View.GONE
            } else {
                img.visibility = View.VISIBLE
                Glide.with(root.context).load(item.img).into(img)
            }
        }
    }

    class Holder(val binding: ItemZhihuBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var item: ZhihuHotItem

        init {
            binding.root.setOnClickListener {
                val context = binding.root.context
                val link = item.link
//                CustomTabUtils.open(context, link)
                DeepLinkUtils.open(context, link)
            }

        }


    }
}