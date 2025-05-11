package com.franciscogarciagarzon.feedstash.rssparser

import com.franciscogarciagarzon.feedstash.domain.rssparser.RssParser
import com.franciscogarciagarzon.feedstash.domain.rssparser.model.RssChannel
import com.franciscogarciagarzon.feedstash.rssparser.mapper.toDomain
import okio.IOException
import com.prof18.rssparser.model.RssChannel as LibraryRssChannel

class FailedRssParser: RssParser {
    companion object{

    }

    override suspend fun getRssChannelFromUrl(url: String): Result<RssChannel> {
       return  Result.failure(IOException("Network error"))
    }

    override suspend fun getRssChannelFromXmlString(xmlString: String): Result<RssChannel> {
        return  Result.failure(IOException("Network error"))
    }
}