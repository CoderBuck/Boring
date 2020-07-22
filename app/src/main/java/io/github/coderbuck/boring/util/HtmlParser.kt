package io.github.coderbuck.boring.util

import com.blankj.utilcode.util.GsonUtils
import io.github.coderbuck.boring.bean.ZhihuBean
import io.github.coderbuck.boring.bean.ZhihuHotItem
import io.github.coderbuck.boring.bean.weibo.WeiboHot
import io.github.coderbuck.boring.bean.weibo.WeiboHotList
import org.jsoup.Jsoup
import timber.log.Timber

object HtmlParser {

    fun getWeiboHotList(html: String): WeiboHotList {
        val document = Jsoup.parse(html)
        val elements = document.select("#pl_top_realtimehot > table > tbody > tr > td.td-02 > a")
        val weiboHotList = WeiboHotList()
        val toList = elements.map {
            val href = it.attr("href")
            val name = it.text()
            return@map WeiboHot(name, href)
        }.toList()
        Timber.d("weiboHotList size = ${toList.size}")
        weiboHotList.addAll(toList)
        return weiboHotList
    }

    fun getZhihuHotList(html: String): List<ZhihuHotItem> {
        val list = mutableListOf<ZhihuHotItem>()
        val json = html.substringAfter("\"hotList\":").substringBefore("\"}],") + "\"}]"
        Timber.d("json = $json")
        val bean = GsonUtils.fromJson<ZhihuBean>(json, ZhihuBean::class.java)
        bean.withIndex().forEach {
            val target = it.value.target
            val index = it.index.toString()
            val title = target.titleArea.text
            val excerpt = target.excerptArea.text
            val metrics = target.metricsArea.text
            val img = target.imageArea.url
            val link = target.link.url
            list.add(ZhihuHotItem(index, title, excerpt, metrics, img, link))
        }
        return list
    }
}