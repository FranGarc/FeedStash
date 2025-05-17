package com.franciscogarciagarzon.feedstash.domain.rssparser.model

data class FeedStashRssChannel(
    val title: String = "",
    val link: String = "",
    val description: String = "",
    val image: FeedStashRssImage? = null,
    val lastBuildDate: String = "",
    val updatePeriod: String = "",
    val items: List<FeedStashRssItem> = emptyList<FeedStashRssItem>(),
)