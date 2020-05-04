package io.github.coderbuck.boring.bean.weibo

class WeiboHotList : ArrayList<WeiboHot>()

data class WeiboHot(val name: String, val href : String) {
}