package org.dnltsk.kotlinguicetypesafemockitodemo.greeting

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.classic.spi.LoggingEvent
import ch.qos.logback.core.Appender
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.slf4j.LoggerFactory

@RunWith(MockitoJUnitRunner::class)
class GreetingServiceTest() {

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
    fun logGreeting_uses_the_ingested_value() {
        //given
        val greetingService = GreetingService("foo")
        //when
        greetingService.logGreeting()
        //than

        verify(mockAppender).doAppend(captorLoggingEvent.capture())
        val loggingEvent = captorLoggingEvent.getValue()
        assertThat(loggingEvent.getLevel()).isEqualTo(Level.INFO)
        assertThat(loggingEvent.formattedMessage).contains("foo")
    }

}