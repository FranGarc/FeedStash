package com.franciscogarciagarzon.feedstash.domain.rssparser

class RssParseException(
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)