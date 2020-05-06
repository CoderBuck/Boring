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


    // #root > div > main > div
    // #root > div > main > div > a:nth-child(1) > div.HotList-itemPre
    // #root > div > main > div > a:nth-child(1) > div.HotList-itemBody
    // #root > div > main > div > a:nth-child(1) > div.HotList-itemImgContainer

    // <a class="HotList-item" data-za-detail-view-path-module="FeedItem" data-za-extra-module="{&quot;attached_info_bytes&quot;:&quot;CjcIABADGgg0OTU2MjMxMSDwzcT1BTBmOI0mQAByCTM5Mjc3NjgzOHgAqgEJYmlsbGJvYXJk0gEA&quot;}"><div class="HotList-itemPre"><div class="HotList-itemIndex HotList-itemIndexHot">01</div><div class="HotList-itemLabel" style="color: rgb(241, 64, 60);"></div></div><div class="HotList-itemBody"><div class="HotList-itemTitle">如何看待 5 月 5 日虎门大桥因剧烈晃动而封闭？是因为天气影响吗？</div><div class="HotList-itemExcerpt">有没有大佬说说是什么因素影响而造成的呢？</div><div class="HotList-itemMetrics">6068 万热度</div></div><div class="HotList-itemImgContainer"><span></span><img src="https://pic4.zhimg.com/80/v2-db4e272158ae3115c22b73132aa9a54f_1440w.jpg" alt="如何看待 5 月 5 日虎门大桥因剧烈晃动而封闭？是因为天气影响吗？"></div></a>
    fun getZhihuHotList(html: String) {
        val document = Jsoup.parse(html)
        val elements = document.select("a.HotList-item")
        val toList = elements.forEach {
            val index = it.select(".HotList-itemIndex").first().text()
            val title = it.select(".HotList-itemTitle").first().text()
            val excerptElement = it.select(".HotList-itemExcerpt").first()
            val excerpt = if (excerptElement != null && excerptElement.hasText()) excerptElement.text() else ""
            val metrics = it.select(".HotList-itemMetrics").first().text()
            val imgElement = it.select(".HotList-itemImgContainer > img").first()
            val img = if (imgElement != null && imgElement.hasText()) imgElement.attr("src") else ""
            Timber.d("item = { index = $index, title = $title, excerpt = $excerpt, metrics = $metrics, img = $img}")
        }
    }
}