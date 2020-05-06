package io.github.coderbuck.boring.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.coderbuck.boring.R
import io.github.coderbuck.boring.RecycleViewDivider
import io.github.coderbuck.boring.adapter.WeiboHotAdapter
import io.github.coderbuck.boring.api.Api
import io.github.coderbuck.boring.databinding.ActivityWeiboBinding
import io.github.coderbuck.boring.util.HtmlParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class WeiboActivity : AppCompatActivity() {

    val adapter = WeiboHotAdapter()

    lateinit var binding: ActivityWeiboBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeiboBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.addItemDecoration(
            RecycleViewDivider(
                this,
                RecycleViewDivider.VERTICAL,
                R.drawable.common_item_divider
            )
        )
        binding.rv.adapter = adapter

//        request()

        zhihu()


    }

    fun zhihu() {
        val call = Api.zhihu.getHots()
        call.enqueue(object : Callback<String?> {
            override fun onFailure(call: Call<String?>, t: Throwable) {
                Timber.w("onFailure")
            }

            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                val body = response.body()
                if (body == null) {
                    Timber.w("body == null")
                    return
                }
                Timber.d("onResponse " + body)
                HtmlParser.getZhihuHotList(body)
            }
        })
    }

    private fun request() {
        val call = Api.weibo.getHotRepoList()
        call.enqueue(object : Callback<String?> {
            override fun onFailure(call: Call<String?>, t: Throwable) {
                Timber.w("onFailure")
            }

            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                Timber.d("onResponse")
                val body = response.body()
                if (body == null) {
                    Timber.w("body == null")
                    return
                }
                Timber.d("onResponse " + body)
                val weiboHotList = HtmlParser.getWeiboHotList(body)

                adapter.items.clear()
                adapter.items.addAll(weiboHotList)
                adapter.notifyDataSetChanged()
            }
        })
    }
}
