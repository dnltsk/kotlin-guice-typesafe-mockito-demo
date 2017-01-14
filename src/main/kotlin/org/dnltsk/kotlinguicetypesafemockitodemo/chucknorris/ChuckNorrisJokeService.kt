package org.dnltsk.kotlinguicetypesafemockitodemo.chucknorris

import com.google.inject.Inject
import com.google.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
class ChuckNorrisJokeService @Inject constructor(
        private val httpClient: ChuckNorrisJokeHttpClient
) {

    private val LOG = LoggerFactory.getLogger(javaClass)

    fun logRandomJoke() {
        val chuckNorrisResponse = httpClient.loadRandomJoke()
        val joke = chuckNorrisResponse.value.joke
        LOG.info("joke: $joke")
    }

}