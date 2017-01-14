package org.dnltsk.kotlinguicetypesafemockitodemo

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.dnltsk.kotlinguicetypesafemockitodemo.chucknorris.ChuckNorrisJokeService
import org.dnltsk.kotlinguicetypesafemockitodemo.greeting.GreetingService
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DemoApplicationTest {

    @Mock
    lateinit var greetingService: GreetingService

    @Mock
    lateinit var chuckNorrisJokeService: ChuckNorrisJokeService

    @InjectMocks
    lateinit var demoApplication: DemoApplication

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun dependencies_are_set() {
        assertThat(demoApplication).isNotNull()
        assertThat(greetingService).isNotNull()
        assertThat(chuckNorrisJokeService).isNotNull()
    }

    @Test
    fun run_method_uses_the_two_demo_services() {
        demoApplication.run()
        verify(greetingService, times(1)).printGreeting()
        verify(chuckNorrisJokeService, times(1)).printRandomJoke()
    }
}