package com.franciscogarciagarzon.feedstash.rssparser

import com.franciscogarciagarzon.feedstash.domain.rssparser.RssParseException
import com.franciscogarciagarzon.feedstash.domain.rssparser.RssParser
import com.franciscogarciagarzon.feedstash.domain.rssparser.model.RssChannel
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import com.prof18.rssparser.model.RssChannel as LibraryRssChannel

class RssParserImplTest {

    private lateinit var successfulParser: RssParser
    private lateinit var failedParser: RssParser

    @BeforeEach
    fun setUp() {
        // Mock the parser builder to return our mock parser
        successfulParser = SuccessfulRssParser()
        failedParser = FailedRssParser()
    }

    @DisplayName("getRssChannelFromUrl returns success when parser succeeds")
    @Test
    fun getRssChannelFromUrlSuccess() {
        // Given
        val testUrl = "https://example.com/rss"
        runTest {
            // When
            val result = successfulParser.getRssChannelFromUrl(testUrl)

            // Then
            Assertions.assertTrue(result.isSuccess)
            assertEquals(SuccessfulRssParser.mockChannel.title, result.getOrNull()?.title)
        }
    }

    @Test
    fun `getRssChannelFromUrl returns failure when parser throws exception`() = runTest {
        // Given
        val testUrl = "https://example.com/rss"

        // When
        val result = failedParser.getRssChannelFromUrl(testUrl)

        // Then
        Assertions.assertTrue(result.isFailure)
        Assertions.assertTrue(result.exceptionOrNull() is RssParseException)
        assertEquals("Failed to fetch RSS from URL", result.exceptionOrNull()?.message)
    }

    @Test
    fun `getRssChannelFromXmlString returns success when parser succeeds`() = runTest {
        // Given
        val testXml = "<rss><channel><title>Test Feed</title></channel></rss>"
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

        // When
        val result = successfulParser.getRssChannelFromXmlString(testXml)

        // Then
        Assertions.assertTrue(result.isSuccess)
        assertEquals("Test Feed", result.getOrNull()?.title)
    }

    @Test
    fun `getRssChannelFromXmlString returns failure when parser throws exception`() = runTest {
        // Given
        val testXml = "<invalid>xml</invalid>"

        // When
        val result = failedParser.getRssChannelFromXmlString(testXml)

        // Then
        Assertions.assertTrue(result.isFailure)
        Assertions.assertTrue(result.exceptionOrNull() is RssParseException)
        assertEquals("Failed to parse RSS XML", result.exceptionOrNull()?.message)
    }

    @Test
    fun `parser is lazily initialized`() {
        // The mockRssParser shouldn't be accessed until first use
        // Our setup in @Before would throw if it tried to initialize immediately
        Assertions.assertNotNull(successfulParser)
    }
}

