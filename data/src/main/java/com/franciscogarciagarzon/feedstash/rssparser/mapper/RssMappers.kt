package com.franciscogarciagarzon.feedstash.rssparser.mapper

import com.prof18.rssparser.model.RssChannel
import com.prof18.rssparser.model.RssImage
import com.prof18.rssparser.model.RssItem


fun RssItem.toDomain(): com.franciscogarciagarzon.feedstash.domain.rssparser.model.RssItem =
    com.franciscogarciagarzon.feedstash.domain.rssparser.model.RssItem(
        guid = this.guid,
        title = this.title,
        author = this.author,
        link = this.link,
        pubDate = this.pubDate,
        description = this.description,
        content = this.content,
        image = this.image,
        sourceName = this.sourceName,
        sourceUrl = this.sourceUrl,
        categories = this.categories,
        commentsUrl = this.commentsUrl,
    )

fun RssImage.toDomain(): com.franciscogarciagarzon.feedstash.domain.rssparser.model.RssImage =
    com.franciscogarciagarzon.feedstash.domain.rssparser.model.RssImage(
        title = this.title,
        url = this.url,
        link = this.link,
        description = this.description
    )

fun RssChannel.toDomain(): com.franciscogarciagarzon.feedstash.domain.rssparser.model.RssChannel =
    com.franciscogarciagarzon.feedstash.domain.rssparser.model.RssChannel(
        title = this.title,
        link = this.link,
        description = this.description,
        image = this.image?.toDomain(),
        lastBuildDate = this.lastBuildDate,
        updatePeriod = this.updatePeriod,
        items = this.items.map { it.toDomain() }
    )