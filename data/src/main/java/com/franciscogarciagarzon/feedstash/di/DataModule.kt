package com.franciscogarciagarzon.feedstash.di

import com.franciscogarciagarzon.feedstash.rssparser.RssParser
import com.franciscogarciagarzon.feedstash.rssparser.RssParserImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    fun provideRssParser(httpClient: OkHttpClient): RssParser {
        return RssParserImpl( httpClient )
    }
}