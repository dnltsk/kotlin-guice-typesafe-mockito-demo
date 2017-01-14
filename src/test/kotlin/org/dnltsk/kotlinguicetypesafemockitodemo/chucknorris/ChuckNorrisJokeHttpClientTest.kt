package org.dnltsk.kotlinguicetypesafemockitodemo.chucknorris

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.Test

class ChuckNorrisJokeHttpClientTest() {

    @Test
    fun createRandomJokeUrl_uses_the_ingested_value() {
        //given
        val client = ChuckNorrisJokeHttpClient("http://dummyBaseUrl", "dummyIncludes", "dummyExcludes")
        //when
        val url = client.createRandomJokeUrl()
        //than
        assertThat(url).isEqualTo("http://dummyBaseUrl/random?&limitTo=[dummyIncludes]&exclude=[dummyExcludes]")
    }

    @Test
    fun includes_are_hidden_if_not_set() {
        //given
        val client = ChuckNorrisJokeHttpClient("http://dummyBaseUrl", "", "dummyExcludes")
        //when
        val url = client.createRandomJokeUrl()
        //than
        assertThat(url).isEqualTo("http://dummyBaseUrl/random?&exclude=[dummyExcludes]")
    }

    @Test
    fun excludes_are_hidden_if_not_set() {
        //given
        val client = ChuckNorrisJokeHttpClient("http://dummyBaseUrl", "dummyIncludes", "")
        //when
        val url = client.createRandomJokeUrl()
        //than
        assertThat(url).isEqualTo("http://dummyBaseUrl/random?&limitTo=[dummyIncludes]")
    }

}