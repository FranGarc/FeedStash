package com.franciscogarciagarzon.feedstash.rssparser.mapper

import com.franciscogarciagarzon.feedstash.domain.rssparser.model.FeedStashRssChannel
import com.franciscogarciagarzon.feedstash.domain.rssparser.model.FeedStashRssImage
import com.franciscogarciagarzon.feedstash.domain.rssparser.model.FeedStashRssItem
import com.prof18.rssparser.model.RssChannel
import com.prof18.rssparser.model.RssImage
import com.prof18.rssparser.model.RssItem


fun RssItem.toDomain(): FeedStashRssItem =
    FeedStashRssItem(
        guid = this.guid ?: "",
        title = this.title ?: "",
        author = this.author ?: "",
        link = this.link ?: "",
        pubDate = this.pubDate ?: "",
        description = this.description ?: "",
        content = this.content ?: "",
        image = this.image ?: "",
        sourceName = this.sourceName ?: "",
        sourceUrl = this.sourceUrl ?: "",
        categories = this.categories,
        commentsUrl = this.commentsUrl ?: "",
    )

fun RssImage.toDomain(): FeedStashRssImage =
    FeedStashRssImage(
        title = this.title ?: "",
        url = this.url ?: "",
        link = this.link ?: "",
        description = this.description ?: "",
    )

fun RssChannel.toDomain(): FeedStashRssChannel =
    FeedStashRssChannel(
        title = this.title ?: "",
        link = this.link ?: "",
        description = this.description ?: "",
        image = this.image?.toDomain(),
        lastBuildDate = this.lastBuildDate ?: "",
        updatePeriod = this.updatePeriod ?: "",
        items = this.items.map { it.toDomain() }
    )