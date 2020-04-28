package io.github.coderbuck.boring.bean

data class NewsBean(
    var title: String,
    var author: String,
    var imgUrl: String,
    var time: String,
    var saveCount: Int,
    var commentsCount: Int
)