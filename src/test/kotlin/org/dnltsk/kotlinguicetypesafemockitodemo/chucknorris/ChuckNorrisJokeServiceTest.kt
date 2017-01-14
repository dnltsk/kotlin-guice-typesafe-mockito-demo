package org.dnltsk.kotlinguicetypesafemockitodemo.chucknorris

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.dnltsk.kotlinguicetypesafemockitodemo.DemoPrintWriter
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.contains
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class ChuckNorrisJokeServiceTest {

    @Mock
    lateinit var httpClient: ChuckNorrisJokeHttpClient

    @Mock
    lateinit var printWriter: DemoPrintWriter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun logRandomJoke_logs_the_joke_correct() {
        //given
        `when`(httpClient.loadRandomJoke()).thenReturn(getTestRespose())
        val service = spy(ChuckNorrisJokeService(httpClient, printWriter))
        //when
        service.printRandomJoke()
        //than
        verify(printWriter, times(1)).println(contains("Chuck Norris can write to an output stream."))
    }

    @Test
    fun client_exception_getting_logged() {
        //given
        `when`(httpClient.loadRandomJoke()).thenThrow(RuntimeException("baaam!!!"))
        val service = spy(ChuckNorrisJokeService(httpClient, printWriter))
        //when
        service.printRandomJoke()
        //than
        verify(printWriter, times(0)).println(contains("Chuck Norris can write to an output stream."))
        verify(service, times(1)).logClientException(any())
    }

    private fun getTestRespose(): ChuckNorrisResponse {
        val inputStream = javaClass.classLoader.getResourceAsStream("goldenTestData/sampleChuckNorrisResponse.json")
        val readValue = ObjectMapper().registerKotlinModule().readValue(inputStream, ChuckNorrisResponse::class.java)
        return readValue
    }

}