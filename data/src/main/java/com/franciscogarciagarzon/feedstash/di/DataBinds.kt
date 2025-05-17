package com.franciscogarciagarzon.feedstash.di

import com.franciscogarciagarzon.feedstash.domain.rssparser.RssParser
import com.franciscogarciagarzon.feedstash.rssparser.RssParserImpl
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
    abstract fun bindsRssParser(impl: RssParserImpl): RssParser

}

