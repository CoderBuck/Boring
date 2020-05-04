package io.github.coderbuck.boring.util

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
}