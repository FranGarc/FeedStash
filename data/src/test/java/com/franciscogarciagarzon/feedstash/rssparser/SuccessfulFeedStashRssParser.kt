package com.franciscogarciagarzon.feedstash.rssparser

import com.franciscogarciagarzon.feedstash.domain.rssparser.FeedStashRssParser
import com.franciscogarciagarzon.feedstash.domain.rssparser.model.FeedStashRssChannel
import com.franciscogarciagarzon.feedstash.rssparser.mapper.toDomain
import com.prof18.rssparser.model.RssChannel as LibraryRssChannel

class SuccessfulFeedStashRssParser: FeedStashRssParser {
    companion object{
        val mockChannel = LibraryRssChannel(
            title = "Test Feed",
            link = "Test link",
            description = "Test desc",
            image = null,
            lastBuildDate = "",
            updatePeriod = "",
            items = emptyList(),
            itunesChannelData = null,
            youtubeChannelData = null,
        )
    }

    override suspend fun getRssChannelFromUrl(url: String): Result<FeedStashRssChannel> {
       return  Result.success(mockChannel.toDomain())
    }

    override suspend fun getRssChannelFromXmlString(xmlString: String): Result<FeedStashRssChannel> {
        return  Result.success(mockChannel.toDomain())
    }
}