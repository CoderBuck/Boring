package io.github.coderbuck.boring.bean

class HotDevList : ArrayList<HotDevItem>()

data class HotDevItem(
    val avatar: String,
    val name: String,
    val repo: Repo,
    val sponsorUrl: String,
    val url: String,
    val username: String
)

data class Repo(
    val description: String,
    val name: String,
    val url: String
)