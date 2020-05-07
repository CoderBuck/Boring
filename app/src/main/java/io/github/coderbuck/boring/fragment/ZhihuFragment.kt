package io.github.coderbuck.boring.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.coderbuck.boring.R
import io.github.coderbuck.boring.RecycleViewDivider
import io.github.coderbuck.boring.adapter.ZhihuHotAdapter
import io.github.coderbuck.boring.databinding.FragmentRvBinding
import io.github.coderbuck.boring.viewmodel.ZhihuViewModel

class ZhihuFragment : Fragment(R.layout.fragment_rv) {

    private val adapter = ZhihuHotAdapter()
    private lateinit var model: ZhihuViewModel
    private lateinit var binding: FragmentRvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this)[ZhihuViewModel::class.java]
        model.request()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRvBinding.bind(view)
        binding.rv.layoutManager = LinearLayoutManager(context)
        binding.rv.addItemDecoration(RecycleViewDivider(context, RecycleViewDivider.VERTICAL, R.drawable.common_item_divider))
        binding.rv.adapter = adapter

        model.items.observe(viewLifecycleOwner, Observer {
            adapter.items.clear()
            adapter.items.addAll(it)
            adapter.notifyDataSetChanged()
        })

    }
}