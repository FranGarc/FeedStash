package com.franciscogarciagarzon.feedstash.rssparser

import com.franciscogarciagarzon.feedstash.domain.rssparser.RssParseException
import com.franciscogarciagarzon.feedstash.domain.rssparser.RssParser
import com.franciscogarciagarzon.feedstash.rssparser.mapper.toDomain
import com.prof18.rssparser.RssParserBuilder
import okhttp3.OkHttpClient
import javax.inject.Inject
import com.franciscogarciagarzon.feedstash.domain.rssparser.model.RssChannel
import com.prof18.rssparser.model.RssChannel as LibraryRssChannel


import java.io.IOException

class RssParserImpl @Inject constructor(
    private val okHttpClient: OkHttpClient
) : RssParser {

    private val parser by lazy {
        RssParserBuilder(
            callFactory = okHttpClient,
            charset = Charsets.UTF_8,
        ).build()
    }

    override suspend fun getRssChannelFromUrl(url: String): Result<RssChannel> {
        return try {
            Result.success(parser.getRssChannel(url).toDomain())
        } catch (e: Exception) {
            Result.failure(RssParseException("Failed to fetch RSS from URL", e))
        }
    }

    override suspend fun getRssChannelFromXmlString(xmlString: String): Result<RssChannel> {
        return try {
            Result.success(parser.parse(xmlString).toDomain())
        } catch (e: Exception) {
            Result.failure(RssParseException("Failed to parse RSS XML", e))
        }
    }
}

