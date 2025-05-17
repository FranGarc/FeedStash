package com.franciscogarciagarzon.feedstash.domain.rssparser

import com.franciscogarciagarzon.feedstash.domain.rssparser.model.FeedStashRssChannel

interface RssParser{
    suspend fun getRssChannelFromUrl(url: String): Result<FeedStashRssChannel>
    suspend fun getRssChannelFromXmlString(xmlString: String): Result<FeedStashRssChannel>
}