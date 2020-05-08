package io.github.coderbuck.boring.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.drm.DrmStore
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.IntentUtils
import com.bumptech.glide.Glide
import io.github.coderbuck.boring.R
import io.github.coderbuck.boring.bean.ZhihuHotItem
import io.github.coderbuck.boring.databinding.ItemZhihuBinding
import io.github.coderbuck.boring.util.CustomTabUtils
import io.github.coderbuck.boring.util.DeepLinkUtils
import saschpe.android.customtabs.CustomTabsHelper
import saschpe.android.customtabs.WebViewFallback
import timber.log.Timber

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