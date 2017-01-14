package org.dnltsk.kotlinguicetypesafemockitodemo.chucknorris

import com.google.inject.Inject
import com.google.inject.Singleton
import org.dnltsk.kotlinguicetypesafemockitodemo.DemoPrintWriter
import org.slf4j.LoggerFactory

@Singleton
class ChuckNorrisJokeService @Inject constructor(
        private val httpClient: ChuckNorrisJokeHttpClient,
        private val printWriter: DemoPrintWriter
) {

    private val LOG = LoggerFactory.getLogger(javaClass)

    fun printRandomJoke() {
        try {
            val chuckNorrisResponse = httpClient.loadRandomJoke()
            val joke = chuckNorrisResponse.value.joke
            printWriter.println("joke: $joke")
        } catch(e: Exception) {
            logClientException(e)
        }
    }

    fun logClientException(e: Exception) {
        LOG.error("cannot printRandomJoke: ${e.message}", e)
    }

}