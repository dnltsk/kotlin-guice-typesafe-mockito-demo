package org.dnltsk.kotlinguicetypesafemockitodemo.chucknorris

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.classic.spi.LoggingEvent
import ch.qos.logback.core.Appender
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.slf4j.LoggerFactory

@RunWith(MockitoJUnitRunner::class)
class ChuckNorrisJokeServiceTest {

    @Mock
    lateinit var client: ChuckNorrisJokeHttpClient

    @Mock
    lateinit var mockAppender: Appender<ILoggingEvent>

    @Captor
    lateinit var captorLoggingEvent: ArgumentCaptor<LoggingEvent>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
        logger.addAppender(mockAppender)
    }

    @After
    fun teardown() {
        val logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
        logger.detachAppender(mockAppender)
    }

    @Test
    fun logRandomJoke_logs_the_joke_correct() {
        //given
        `when`(client.loadRandomJoke()).thenReturn(getTestRespose())
        var service = spy(ChuckNorrisJokeService(client))
        //when
        service.logRandomJoke()
        //than
        verify(mockAppender).doAppend(captorLoggingEvent.capture())
        val loggingEvent = captorLoggingEvent.getValue()
        assertThat(loggingEvent.getLevel()).isEqualTo(Level.INFO)
        assertThat(loggingEvent.formattedMessage).contains("Chuck Norris can write to an output stream.")
    }

    private fun getTestRespose(): ChuckNorrisResponse {
        val inputStream = javaClass.classLoader.getResourceAsStream("goldenTestData/sampleChuckNorrisResponse.json")
        val readValue = ObjectMapper().registerKotlinModule().readValue(inputStream, ChuckNorrisResponse::class.java)
        return readValue
    }
}