package com.franciscogarciagarzon.feedstash.rssparser

import com.franciscogarciagarzon.feedstash.domain.rssparser.RssParseException
import com.franciscogarciagarzon.feedstash.domain.rssparser.model.FeedStashRssChannel
import com.prof18.rssparser.RssParser
import com.prof18.rssparser.model.RssChannel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FeedStashRssParserImplTest {

    private val mockRssParser: RssParser = mockk()

    private lateinit var parser: FeedStashRssParserImpl

    private val validUrl = "http:\\\\www.validurl.com\\feed"
    private val xmlString = "<channel>\n" +
            "  <title>W3Schools Home Page</title>\n" +
            "  <link>https://www.w3schools.com</link>\n" +
            "  <description>Free web building tutorials</description>\n" +
            "  <item>\n" +
            "    <title>RSS Tutorial</title>\n" +
            "    <link>https://www.w3schools.com/xml/xml_rss.asp</link>\n" +
            "    <description>New RSS tutorial on W3Schools</description>\n" +
            "  </item>\n" +
            "  <item>\n" +
            "    <title>XML Tutorial</title>\n" +
            "    <link>https://www.w3schools.com/xml</link>\n" +
            "    <description>New XML tutorial on W3Schools</description>\n" +
            "  </item>\n" +
            "</channel>"

    @BeforeEach
    fun setup() {
        parser = FeedStashRssParserImpl(mockRssParser)
    }


    @Test
    fun `when url is valid, getRssChannelFromUrl returns a successful response with a FeedStashRssChannel`() = runTest {
        // Given
        val fakeChannel = getFakeRssChannel()
        coEvery { mockRssParser.getRssChannel(validUrl) } returns fakeChannel

        // When
        val result = parser.getRssChannelFromUrl(validUrl)

        // Then
        assertTrue(result.isSuccess)
        assertTrue { result.getOrNull() is FeedStashRssChannel }
    }

    @Test
    fun `when url is not valid, getRssChannelFromUrl returns a failed response with a RssParseException`() = runTest {
        // Given
        val invalidUrl = "invalid url"
        coEvery { mockRssParser.getRssChannel(invalidUrl) } throws Exception()

        // When
        val result = parser.getRssChannelFromUrl(invalidUrl)

        // Then
        assertTrue(result.isFailure)
        assertTrue { result.exceptionOrNull() is RssParseException }
    }

    @Test
    fun `when xmlString is valid, getRssChannelFromXmlString returns a successful response with a FeedStashRssChannel`() = runTest {
        // Given

        val fakeChannel = getFakeRssChannel()
        coEvery { mockRssParser.parse(xmlString) } returns fakeChannel

        // When
        val result = parser.getRssChannelFromXmlString(xmlString)

        // Then
        assertTrue(result.isSuccess)
        assertTrue { result.getOrNull() is FeedStashRssChannel }

    }

    @Test
    fun `when xmlString is not valid, getRssChannelFromXmlString returns a failed response with a RssParseException`() = runTest {
        // Given
        val invalidXml = "invalid xml"

        coEvery { mockRssParser.parse(invalidXml) } throws Exception()

        // When
        val result = parser.getRssChannelFromXmlString(invalidXml)

        // Then
        assertTrue(result.isFailure)
        assertTrue { result.exceptionOrNull() is RssParseException }

    }


    private fun getFakeRssChannel(): RssChannel {
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