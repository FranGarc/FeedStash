//import com.franciscogarciagarzon.feedstash.domain.rssparser.FeedStashRssParser
//import com.franciscogarciagarzon.feedstash.domain.rssparser.model.FeedStashRssChannel
//import com.franciscogarciagarzon.feedstash.rssparser.FeedStashRssParserImpl
//import com.franciscogarciagarzon.feedstash.rssparser.mapper.toDomain
//import com.prof18.rssparser.RssParserBuilder
//import io.mockk.coEvery
//import io.mockk.every
//import io.mockk.mockk
//import kotlinx.coroutines.test.runTest
//import okhttp3.OkHttpClient
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Assertions.assertTrue
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.DisplayName
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.assertThrows
//import java.io.IOException
//import com.prof18.rssparser.model.RssChannel as LibraryRssChannel
//
//class FeedStashRssParserImplTestTest {
//
//    // Mocks
//    private val mockOkHttpClient: OkHttpClient = mockk(relaxed = true)
//    private val mockRssParser: com.prof18.rssparser.RssParser = mockk()
//
//    // System Under Test
//    private lateinit var parser: FeedStashRssParser
//
//    @BeforeEach
//    fun setUp() {
//        // Configure the builder to return our mock parser
//        every {
//            RssParserBuilder(
//                callFactory = mockOkHttpClient,
//                charset = Charsets.UTF_8
//            ).build()
//        } returns mockRssParser
//
//        // Initialize the real parser with mocked dependencies
//        parser = FeedStashRssParserImpl(mockRssParser)
//    }
//
//    @Test
//    @DisplayName("Successfully parse RSS from URL")
//    fun `getRssChannelFromUrl returns success`() = runTest {
//        // Given
//        val testUrl = "https://example.com/rss"
//        val mockChannel = mockk<LibraryRssChannel> {
//            every { title } returns "Test Feed"
//            every { toDomain() } returns FeedStashRssChannel(title = "Test Feed")
//        }
//        coEvery { mockRssParser.getRssChannel(testUrl) } returns mockChannel
//
//        // When
//        val result = parser.getRssChannelFromUrl(testUrl)
//
//        // Then
//        assertTrue(result.isSuccess)
//        assertEquals("Test Feed", result.getOrNull()?.title)
//    }
//
//    @Test
//    @DisplayName("Handle network error when fetching RSS")
//    fun `getRssChannelFromUrl returns failure on error`() = runTest {
//        // Given
//        val testUrl = "https://example.com/rss"
//        coEvery { mockRssParser.getRssChannel(testUrl) } throws IOException("Network error")
//
//        // When
//        val result = parser.getRssChannelFromUrl(testUrl)
//
//        // Then
//        assertTrue(result.isFailure)
//        assertEquals("Failed to fetch RSS from URL", result.exceptionOrNull()?.message)
//    }
//
//    @Test
//    @DisplayName("Successfully parse RSS from XML string")
//    fun `getRssChannelFromXmlString returns success`() = runTest {
//        // Given
//        val testXml = "<rss><title>Test</title></rss>"
//        val mockChannel = mockk<LibraryRssChannel> {
//            every { title } returns "Test Feed"
//            every { toDomain() } returns FeedStashRssChannel(title = "Test Feed")
//        }
//        coEvery { mockRssParser.parse(testXml) } returns mockChannel
//
//        // When
//        val result = parser.getRssChannelFromXmlString(testXml)
//
//        // Then
//        assertTrue(result.isSuccess)
//        assertEquals("Test Feed", result.getOrNull()?.title)
//    }
//
//    @Test
//    @DisplayName("Handle malformed XML input")
//    fun `getRssChannelFromXmlString returns failure`() = runTest {
//        // Given
//        val testXml = "<invalid>xml</invalid>"
//        coEvery { mockRssParser.parse(testXml) } throws RuntimeException("Parse error")
//
//        // When
//        val result = parser.getRssChannelFromXmlString(testXml)
//
//        // Then
//        assertTrue(result.isFailure)
//        assertEquals("Failed to parse RSS XML", result.exceptionOrNull()?.message)
//    }
//
//    @Test
//    @DisplayName("Reject empty URL input")
//    fun `empty URL throws exception`() = runTest {
//        assertThrows<IllegalArgumentException> {
//            parser.getRssChannelFromUrl("")
//        }
//    }
//}