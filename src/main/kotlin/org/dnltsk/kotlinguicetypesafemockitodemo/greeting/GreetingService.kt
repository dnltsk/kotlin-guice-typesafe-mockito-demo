package org.dnltsk.kotlinguicetypesafemockitodemo.greeting

import com.github.racc.tscg.TypesafeConfig
import com.google.inject.Inject
import com.google.inject.Singleton
import org.dnltsk.kotlinguicetypesafemockitodemo.DemoPrintWriter

@Singleton
class GreetingService @Inject constructor(
        @TypesafeConfig("greeting") private val greeting: String,
        private val printWriter: DemoPrintWriter
) {

    fun printGreeting() {
        printWriter.println("greeting: $greeting")
    }
}