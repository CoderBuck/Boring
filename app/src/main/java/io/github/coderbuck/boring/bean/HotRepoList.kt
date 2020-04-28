package io.github.coderbuck.boring.bean

class HotRepoList : ArrayList<HotRepoItem>()

data class HotRepoItem(
    val author: String,
    val avatar: String,
    val builtBy: List<Creator>,
    val currentPeriodStars: Int,
    val description: String,
    val forks: Int,
    val language: String,
    val languageColor: String,
    val name: String,
    val stars: Int,
    val url: String
)

data class Creator(
    val avatar: String,
    val href: String,
    val username: String
)