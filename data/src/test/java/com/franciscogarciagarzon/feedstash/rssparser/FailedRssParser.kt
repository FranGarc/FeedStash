package com.franciscogarciagarzon.feedstash.rssparser

import com.franciscogarciagarzon.feedstash.domain.rssparser.RssParser
import com.franciscogarciagarzon.feedstash.domain.rssparser.model.FeedStashRssChannel
import okio.IOException

class FailedRssParser: RssParser {
    companion object{

    }

    override suspend fun getRssChannelFromUrl(url: String): Result<FeedStashRssChannel> {
       return  Result.failure(IOException("Network error"))
    }

    override suspend fun getRssChannelFromXmlString(xmlString: String): Result<FeedStashRssChannel> {
        return  Result.failure(IOException("Network error"))
    }
}