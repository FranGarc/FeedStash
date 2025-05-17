package com.franciscogarciagarzon.feedstash.data

import com.franciscogarciagarzon.feedstash.data.rssparser.FeedStashRssParserImpl
import com.franciscogarciagarzon.feedstash.data.rssparser.mapper.toDomain
import com.franciscogarciagarzon.feedstash.domain.rssparser.model.FeedStashRssChannel
import com.prof18.rssparser.RssParser
import com.prof18.rssparser.model.RssChannel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class FeedStashRssParserImplTest {

    private lateinit var parserImpl: FeedStashRssParserImpl
    private val mockRssParser: RssParser = mockk()

    @BeforeEach
    fun setup() {
//        parserImpl = FeedStashRssParserImpl(mockRssParser)
        parserImpl = FeedStashRssParserImpl(RssParser())
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun getRssChannelFromUrlShouldReturnSuccessWhenParserSucceeds() = runTest {
        // Given
        val testUrl = "https://ehtio.es/rss"
//        val expectedRssChannel = mockk<RssChannel>()
//        val expectedDomain = mockk<FeedStashRssChannel>()
//
//        coEvery { mockRssParser.getRssChannel(testUrl) } returns expectedRssChannel
//        every { expectedRssChannel.toDomain() } returns expectedDomain


        // When
        val result = parserImpl.getRssChannelFromUrl(testUrl)

        // Then
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull()?.title =="¡Eh, tío!")
    }

//    @Test
//    fun `getRssChannelFromUrl should return failure when parser throws exception`() = runTest {
//        // Given
//        val testUrl = "http://example.com/rss"
//        val expectedException = Exception("Network error")
//
//        coEvery { mockRssParser.getRssChannel(testUrl) } throws expectedException
//
//        // When
//        val result = parserImpl.getRssChannelFromUrl(testUrl)
//
//        // Then
//        assertTrue(result.isFailure)
//        assertTrue(result.exceptionOrNull() is RssParseException)
//        assertTrue(result.exceptionOrNull()?.cause == expectedException)
//    }
//
//    @Test
//    fun `getRssChannelFromXmlString should return success when parser succeeds`() = runTest {
//        // Given
//        val testXml = "<rss>...</rss>"
//        val expectedRssChannel = mockk<RssChannel>()
//        val expectedDomain = mockk<FeedStashRssChannel>()
//
//        coEvery { mockRssParser.parse(testXml) } returns expectedRssChannel
//        every { expectedRssChannel.toDomain() } returns expectedDomain
//
//        // When
//        val result = parserImpl.getRssChannelFromXmlString(testXml)
//
//        // Then
//        assertTrue(result.isSuccess)
//        assertTrue(result.getOrNull() == expectedDomain)
//    }
//
//    @Test
//    fun `getRssChannelFromXmlString should return failure when parser throws exception`() = runTest {
//        // Given
//        val testXml = "<rss>...</rss>"
//        val expectedException = Exception("Parse error")
//
//        coEvery { mockRssParser.parse(testXml) } throws expectedException
//
//        // When
//        val result = parserImpl.getRssChannelFromXmlString(testXml)
//
//        // Then
//        assertTrue(result.isFailure)
//        assertTrue(result.exceptionOrNull() is RssParseException)
//        assertTrue(result.exceptionOrNull()?.cause == expectedException)
//    }
//
//    @Test
//    fun `getRssChannelFromXmlString should handle empty XML string`() = runTest {
//        // Given
//        val emptyXml = ""
//        coEvery { mockRssParser.parse(emptyXml) } throws IllegalArgumentException("Empty XML")
//
//        // When
//        val result = parserImpl.getRssChannelFromXmlString(emptyXml)
//
//        // Then
//        assertTrue(result.isFailure)
//        assertTrue(result.exceptionOrNull() is RssParseException)
//    }
}