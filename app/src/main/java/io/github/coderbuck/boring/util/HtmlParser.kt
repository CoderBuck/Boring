package io.github.coderbuck.boring.util

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
        val document = Jsoup.parse(html)
        val elements = document.select("a.HotList-item")
        val list = mutableListOf<ZhihuHotItem>()
        elements.forEach {
            val index = it.select(".HotList-itemIndex").first().text()
            val title = it.select(".HotList-itemTitle").first().text()
            val excerpt = it.select(".HotList-itemExcerpt").first()?.text() ?: ""
            val metrics = it.select(".HotList-itemMetrics").first().text()
            val img = it.select(".HotList-itemImgContainer > img").first()?.attr("src") ?: ""
            Timber.d("item = { index = $index, title = $title, excerpt = $excerpt, metrics = $metrics, img = $img}")
            list.add(ZhihuHotItem(index, title, excerpt, metrics, img))
        }
        return list
    }
}