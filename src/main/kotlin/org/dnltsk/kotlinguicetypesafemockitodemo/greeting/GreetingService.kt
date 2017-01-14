package org.dnltsk.kotlinguicetypesafemockitodemo.greeting

import com.github.racc.tscg.TypesafeConfig
import com.google.inject.Inject
import com.google.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
class GreetingService @Inject constructor(
        @TypesafeConfig("greeting") private val greeting: String
) {

    private val LOG = LoggerFactory.getLogger(javaClass)

    fun logGreeting() {
        LOG.info("greeting: $greeting")
    }
}