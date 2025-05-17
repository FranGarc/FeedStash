package com.franciscogarciagarzon.feedstash.data.di

import com.prof18.rssparser.RssParser
import com.prof18.rssparser.RssParserBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    fun provideRssParser(okHttpClient: OkHttpClient): RssParser {
       return RssParserBuilder(
            callFactory = okHttpClient,
            charset = Charsets.UTF_8,
        ).build()
    }
}