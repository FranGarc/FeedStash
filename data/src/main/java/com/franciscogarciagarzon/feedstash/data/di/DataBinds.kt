package com.franciscogarciagarzon.feedstash.data.di

import com.franciscogarciagarzon.feedstash.domain.rssparser.FeedStashRssParser
import com.franciscogarciagarzon.feedstash.data.rssparser.FeedStashRssParserImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBinds {

    @Binds
    @Singleton
    abstract fun bindsRssParser(impl: FeedStashRssParserImpl): FeedStashRssParser

}

