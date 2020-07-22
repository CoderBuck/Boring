package io.github.coderbuck.boring.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.coderbuck.boring.R
import io.github.coderbuck.boring.RecycleViewDivider
import io.github.coderbuck.boring.adapter.HotRepoAdapter
import io.github.coderbuck.boring.databinding.FragmentRvBinding
import io.github.coderbuck.boring.util.provideViewModel
import io.github.coderbuck.boring.viewmodel.GithubViewModel
import me.buck.viewbindingktx.viewBinding

class GithubFragment : Fragment(R.layout.fragment_rv) {

    private val adapter = HotRepoAdapter()
    private val model: GithubViewModel by viewModels()
    private val binding by viewBinding(FragmentRvBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.request()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.layoutManager = LinearLayoutManager(context)
        binding.rv.addItemDecoration(RecycleViewDivider(context, RecycleViewDivider.VERTICAL, R.drawable.common_item_divider))
        binding.rv.adapter = adapter

        model.hotRepoList.observe(viewLifecycleOwner, Observer {
            adapter.items.clear()
            adapter.items.addAll(it)
            adapter.notifyDataSetChanged()
        })

        model.refresh.observe(viewLifecycleOwner, Observer { refresh ->
            if (!refresh) {
                binding.refreshLayout.isRefreshing = false
            }
        })

        binding.refreshLayout.setOnRefreshListener {
            model.request()
        }

    }
}