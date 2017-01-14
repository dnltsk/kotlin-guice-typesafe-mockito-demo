package org.dnltsk.kotlinguicetypesafemockitodemo.chucknorris

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.racc.tscg.TypesafeConfig
import com.google.inject.Inject
import com.google.inject.Singleton
import com.mashape.unirest.http.JsonNode
import com.mashape.unirest.http.Unirest
import org.slf4j.LoggerFactory

@Singleton
class ChuckNorrisJokeHttpClient @Inject constructor(
        @TypesafeConfig("chuck-norris-joke.base-url") private val baseUrl: String,
        @TypesafeConfig("chuck-norris-joke.includes") private val includes: String,
        @TypesafeConfig("chuck-norris-joke.excludes") private val excludes: String) {

    private val LOG = LoggerFactory.getLogger(javaClass)

    fun loadRandomJoke(): ChuckNorrisResponse {
        val randomJokeUrl = createRandomJokeUrl()
        LOG.info("open " + randomJokeUrl)
        val body = Unirest.get(randomJokeUrl).asJson().body
        return serializeRandomJokeBody(body)
    }

    private fun serializeRandomJokeBody(body: JsonNode): ChuckNorrisResponse =
            ObjectMapper().registerKotlinModule().readValue(
                    body.toString(),
                    ChuckNorrisResponse::class.java)

    fun createRandomJokeUrl(): String {
        var url = "$baseUrl/random?"
        if (includes.isNotBlank())
            url += "&limitTo=[$includes]"
        if (excludes.isNotBlank())
            url += "&exclude=[$excludes]"

        return url
    }

}


