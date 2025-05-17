package com.franciscogarciagarzon.feedstash.domain.rssparser.model

data class FeedStashRssItem(
    val guid: String = "",
    val title: String = "",
    val author: String = "",
    val link: String = "",
    val pubDate: String = "",
    val description: String = "",
    val content: String = "",
    val image: String = "",
    val sourceName: String = "",
    val sourceUrl: String = "",
    val categories: List<String> = emptyList<String>(),
    val commentsUrl: String = "",
)
