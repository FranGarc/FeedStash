package com.franciscogarciagarzon.feedstash.domain.rssparser

import com.franciscogarciagarzon.feedstash.domain.rssparser.model.RssChannel

interface RssParser{
    suspend fun getRssChannelFromUrl(url: String): Result<RssChannel>
    suspend fun getRssChannelFromXmlString(xmlString: String): Result<RssChannel>
}