package org.dnltsk.kotlinguicetypesafemockitodemo.chucknorris

/**
 * use Kotlin Jackson Generator to generate that data model!
 * https://plugins.jetbrains.com/idea/plugin/8376-kotlin-jackson-generator
 */
class ChuckNorrisResponse(
        val type: String,
        val value: ChuckNorrisJoke)

class ChuckNorrisJoke(
        val id: Int,
        val categories: List<String>,
        val joke: String)

