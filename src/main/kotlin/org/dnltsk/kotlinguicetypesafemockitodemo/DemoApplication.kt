package org.dnltsk.kotlinguicetypesafemockitodemo

import com.github.racc.tscg.TypesafeConfigModule
import com.google.inject.Guice
import com.google.inject.Inject
import com.google.inject.Injector
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import org.dnltsk.kotlinguicetypesafemockitodemo.chucknorris.ChuckNorrisJokeService
import org.dnltsk.kotlinguicetypesafemockitodemo.greeting.GreetingService
import org.slf4j.LoggerFactory


open class DemoApplication @Inject constructor(
        val greetingService: GreetingService,
        val chuckNorrisJokeService: ChuckNorrisJokeService
) {

    private val LOG = LoggerFactory.getLogger(javaClass)

    fun run() {
        LOG.info("started DemoApplication..")
        greetingService.logGreeting()
        chuckNorrisJokeService.logRandomJoke()
        LOG.info("finished DemoApplication!")
    }

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            val injector = configureInjector()
            val demoApplication = injector.getInstance(DemoApplication::class.java)
            demoApplication.run()
        }

        private fun configureInjector(): Injector {
            val profileConfig = loadProfileConfig()
            val injector = Guice.createInjector(
                    TypesafeConfigModule.fromConfigWithPackage(profileConfig, "org.dnltsk.kotlinguicetypesafemockitodemo"),
                    DemoModule()
            )
            return injector
        }

        private fun loadProfileConfig(): Config {
            val defaultConfig = loadDefaultConfig()
            val profile = System.getProperty("profile")

            return when (profile) {
                "local" -> ConfigFactory.load("application-local.properties").withFallback(defaultConfig)
                "dev" -> ConfigFactory.load("application-dev.properties").withFallback(defaultConfig)
                "eis" -> ConfigFactory.load("application-eis.properties").withFallback(defaultConfig)
                "prod" -> ConfigFactory.load("application-prod.properties").withFallback(defaultConfig)
                else -> defaultConfig
            }
        }

        private fun loadDefaultConfig() = ConfigFactory.load("application.properties")
    }

}

