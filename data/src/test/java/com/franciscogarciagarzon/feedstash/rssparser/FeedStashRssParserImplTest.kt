package com.franciscogarciagarzon.feedstash.rssparser

import com.franciscogarciagarzon.feedstash.domain.rssparser.model.FeedStashRssChannel
import com.franciscogarciagarzon.feedstash.rssparser.mapper.toDomain
import com.prof18.rssparser.RssParser
import com.prof18.rssparser.model.RssChannel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue

class FeedStashRssParserImplTest {

    private val mockRssParser: RssParser = mockk()

    private lateinit var parser: FeedStashRssParserImpl

    @BeforeEach
    fun setup() {
        parser = FeedStashRssParserImpl(mockRssParser)
    }


    @Test
    fun getRssChannelFromUrlTestSuccess() = runTest {
        // Given
        val url = "https://example.com/rss"

        val fakeChannel = getFakeRssChannel()
        coEvery { mockRssParser.getRssChannel(url) } returns fakeChannel

        // When
        val result = parser.getRssChannelFromUrl(url)

        // Then
        assertTrue(result.isSuccess)
    }


    private fun getFakeRssChannel() : RssChannel {
        return RssChannel(
            title = "Test Feed",
            link = null,
            description = null,
            image = null,
            lastBuildDate = null,
            updatePeriod = null,
            items = emptyList(),
            itunesChannelData = null,
            youtubeChannelData = null,
        )
    }
}