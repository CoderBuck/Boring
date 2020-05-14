package io.github.coderbuck.boring.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import io.github.coderbuck.boring.R
import io.github.coderbuck.boring.bean.ZhihuHotItem
import io.github.coderbuck.boring.databinding.ItemZhihuBinding
import io.github.coderbuck.boring.util.DeepLinkUtils

class ZhihuHotAdapter : RecyclerView.Adapter<ZhihuHotAdapter.Holder>() {
    val items = mutableListOf<ZhihuHotItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent, R.layout.item_zhihu)
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
                Glide.with(root.context)
                    .load(item.img)
                    .transform(CenterCrop(), RoundedCorners(15))
                    .into(img)
            }
        }
    }

    class Holder(parent: ViewGroup, id: Int) : BaseHolder(parent, id) {
        val binding = ItemZhihuBinding.bind(itemView)!!
        lateinit var item: ZhihuHotItem

        init {
            binding.root.setOnClickListener {
                val context = binding.root.context
                val link = item.link
                DeepLinkUtils.open(context, link)
            }

        }


    }
}