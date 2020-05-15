package io.github.coderbuck.boring.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.coderbuck.boring.bean.ZhihuHotItem

class TestAdapter : ListAdapter<ZhihuHotItem, ZhihuHotAdapter.Holder>(MyDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZhihuHotAdapter.Holder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ZhihuHotAdapter.Holder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }





    private class MyDiff : DiffUtil.ItemCallback<ZhihuHotItem>() {

        override fun areItemsTheSame(oldItem: ZhihuHotItem, newItem: ZhihuHotItem): Boolean {
            return oldItem.link == newItem.link
        }

        override fun areContentsTheSame(oldItem: ZhihuHotItem, newItem: ZhihuHotItem): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: ZhihuHotItem, newItem: ZhihuHotItem): Any? {
            return super.getChangePayload(oldItem, newItem)
        }

    }
}