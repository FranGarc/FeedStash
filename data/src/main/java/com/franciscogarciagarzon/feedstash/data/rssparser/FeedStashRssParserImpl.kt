package com.franciscogarciagarzon.feedstash.data.rssparser

import com.franciscogarciagarzon.feedstash.domain.rssparser.RssParseException
import com.franciscogarciagarzon.feedstash.domain.rssparser.FeedStashRssParser
import com.franciscogarciagarzon.feedstash.data.rssparser.mapper.toDomain
import javax.inject.Inject
import com.franciscogarciagarzon.feedstash.domain.rssparser.model.FeedStashRssChannel
import com.prof18.rssparser.RssParser

class FeedStashRssParserImpl @Inject constructor(
    private val parser: RssParser
) : FeedStashRssParser {

    override suspend fun getRssChannelFromUrl(url: String): Result<FeedStashRssChannel> {
        return try {
            val rssChannel = parser.getRssChannel(url)
            val dom = rssChannel.toDomain()
            Result.success(dom)
        } catch (e: Exception) {
            Result.failure(RssParseException("Failed to fetch RSS from URL", e))
        }
    }

    override suspend fun getRssChannelFromXmlString(xmlString: String): Result<FeedStashRssChannel> {
        return try {
            val rssChannel = parser.parse(xmlString)
            val dom = rssChannel.toDomain()
            Result.success(dom)
        } catch (e: Exception) {
            Result.failure(RssParseException("Failed to parse RSS XML", e))
        }
    }
}

