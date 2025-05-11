package com.franciscogarciagarzon.feedstash.domain.rssparser.model

data class RssChannel(
    val title: String = "",
    val link: String = "",
    val description: String = "",
    val image: RssImage = RssImage(),
    val lastBuildDate: String = "",
    val updatePeriod: String = "",
    val items: List<RssItem> = emptyList<RssItem>(),
)