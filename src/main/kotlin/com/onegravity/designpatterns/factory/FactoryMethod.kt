package com.onegravity.designpatterns.factory

import java.util.concurrent.atomic.AtomicBoolean

/*
 * The @see com.onegravity.designpatterns.factory.PizzaFactory has already an example of the factory method
 * (the createPizza method). Nevertheless here's a simple example of the "other" factory design pattern.
 */

interface Joke {
    fun get(): String
}

interface JokeFactory {
    /**
     * Yes this is the factory method!
     */
    fun createJoke(): Joke
}

abstract class Actor(private val firstName: String, private val lastName: String) {
    fun name() = "$firstName $lastName"
}

object Brad : Actor("Brad", "Pitt"), JokeFactory {
    private val flag = AtomicBoolean(true)
    private val joke1 = object: Joke {
        override fun get() = "Schwarzenegger has a long one, Brad Pitt has a short one, Madonna does not have one and the Pope does not use his. What is it?\nA surname."
    }
    private val joke2 = object: Joke {
        override fun get() = "This woman said that I reminded her of Brad Pitt.\nI was flattered, until she mentioned it was when he played Benjamin Button."
    }
    override fun createJoke() = if (flag.getAndSet(! flag.get())) joke1 else joke2
}

object Daniel : Actor("Daniel", "Radcliffe"), JokeFactory {
    override fun createJoke() = object : Joke {
        override fun get() = "Do you think Daniel Radcliffe could ever play a hobbit?\nNo, but Elijah would."
    }
}

fun main() {
    Brad.run {
        println(name())
        println(createJoke().get())
        println(createJoke().get())
    }
    Daniel.run {
        println(name())
        println(createJoke().get())
        println(createJoke().get())
    }
}
