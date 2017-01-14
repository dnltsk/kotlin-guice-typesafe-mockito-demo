[![Build Status](https://travis-ci.org/dnltsk/kotlin-guice-typesafe-mockito-demo.svg?branch=master)](https://travis-ci.org/dnltsk/kotlin-guice-typesafe-mockito-demo) [![codecov](https://codecov.io/gh/dnltsk/kotlin-guice-typesafe-mockito-demo/branch/master/graph/badge.svg)](https://codecov.io/gh/dnltsk/kotlin-guice-typesafe-mockito-demo)
 [![codebeat badge](https://codebeat.co/badges/9fcb09b1-15d8-4b3d-a3cc-fd8f7f81b412)](https://codebeat.co/projects/github-com-dnltsk-kotlin-guice-typesafe-mockito-demo)


# Kotlin, Google Guice, Typesafe Config, Mockito Demo

This Demo gives you an example how to set up an application in Kotlin with Guice, Typesafe Config, and Mockito.
Looks like that's the counterpart to the [spring-boot-kotlin-demo](https://github.com/sdeleuze/spring-boot-kotlin-demo)

Summary of what this demo brings together: 
* [**Kotlin**](https://github.com/JetBrains/kotlin) :rocket: 
* [**Google Guice**](https://github.com/google/guice) for dependency injection
* [**Typesafe Config**](https://github.com/typesafehub/config) to read out application properties
* [**unirest**](https://github.com/Mashape/unirest-java) as http client
* [**Mockito**](https://github.com/mockito/mockito) for mocking
 
## Why not Spring Boot

Once you set up **spring-boot-starter** and **spring-boot-starter-test** up and running everything can be fine. 
But sometimes you don't want/need to have that magic and search for a more lightweight approach.

In addition two major issues happen:
* *version conflict* when having **spring-boot-starter-test** and **mockito-kotlin** in place (Mockito 1 vs Mockito 2), see [issue 5537](https://github.com/spring-projects/spring-boot/issues/5537) 
=> luckily that'll be fixed in Spring Boot 1.5
* *exit 1*when adding **Logstash Lockback** dependency (still didn't figured the root cause), see [issue 5968](https://github.com/spring-projects/spring-boot/issues/5968)

## Why the other libraries

* **Google Guice** is truly lightweight and forces you to use constructor injection which is the preferred approach in Kotlin as well
* **Typesafe Config** is open to almost all possible ways to configure your project, for Guice we also need [**Typesafe Config Guice**](https://github.com/racc/typesafeconfig-guice). 
* **unirest** is the *jsoup* of an http client - wuhuu!!!
* **Mockito** allows you to mock and spy your class under test and its dependencies.
*The tricky part when using it with Kotlin is that all classes and methods are final and per se not mockable.
Therefore we can use [**Mockito-Kotlin**](https://github.com/nhaarman/mockito-kotlin) which opens them for testing.*
* as written above [**Logstash Lockback**](https://github.com/logstash/logstash-logback-encoder) was a reason to turn away from Spring Boot so it should be in that demo as well.
* last but not least [**Jackson**](https://github.com/FasterXML/jackson) is the de-facto standard when it's about serialization. Logstash depends on it, Kotlin can use it 

## build

* `mvn clean test package`

## run

* `java -jar target/kotlin-guice-typesafe-mockito-demo.jar` for default properties
  * uses application.properties
  * greets with 'Hello Default!'
  * excludes _explicit_ Chuck Norris Jokes
* `java -jar -Dprofile=dev target/kotlin-guice-typesafe-mockito-demo.jar` to use profile properties
  * uses application-dev.properties
  * keeps default properties from application.properties
  * greets with 'Hi Dev!'
  * filters _nerdy_ Chuck Norris Jokes