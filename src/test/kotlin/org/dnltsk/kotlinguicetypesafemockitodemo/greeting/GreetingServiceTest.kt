package org.dnltsk.kotlinguicetypesafemockitodemo.greeting

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.dnltsk.kotlinguicetypesafemockitodemo.DemoPrintWriter
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.contains
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GreetingServiceTest() {

    @Mock
    lateinit var demoPrintWriter: DemoPrintWriter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun logGreeting_prints_the_given_greeting() {
        //given
        val greetingService = GreetingService("foo-name", demoPrintWriter)
        //when
        greetingService.printGreeting()
        //than
        verify(demoPrintWriter, times(1)).println(contains("foo-name"))
    }

}