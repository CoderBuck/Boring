package io.github.coderbuck.boring

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.coderbuck.boring.adapter.HotRepoAdapter
import io.github.coderbuck.boring.api.Api
import io.github.coderbuck.boring.bean.github.HotRepoList
import io.github.coderbuck.boring.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    val adapter = HotRepoAdapter()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.addItemDecoration(RecycleViewDivider(this, RecycleViewDivider.VERTICAL, R.drawable.common_item_divider))
        binding.rv.adapter = adapter

        request()
    }

    private fun request() {
        val call = Api.github.getHotRepoList()
        call.enqueue(object : Callback<HotRepoList?> {
            override fun onFailure(call: Call<HotRepoList?>, t: Throwable) {
                Timber.w("onFailure")
            }

            override fun onResponse(call: Call<HotRepoList?>, response: Response<HotRepoList?>) {
                Timber.d("onResponse")
                val body = response.body()
                if (body == null) {
                    Timber.w("body == null")
                    return
                }
                Timber.d("body.size = ${body.size}")

                adapter.items.clear()
                adapter.items.addAll(body)
                adapter.notifyDataSetChanged()
            }

        })
    }
}
