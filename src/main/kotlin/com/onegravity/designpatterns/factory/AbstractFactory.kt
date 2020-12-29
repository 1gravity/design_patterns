package com.onegravity.designpatterns.factory

interface Pizza {
    fun create()
    fun bake()
    fun eat()
}

object ItalianPizza : Pizza {
    override fun create() = println("Creating Italian pizza")
    override fun bake() = println("Baking pizza hot and fast")
    override fun eat() = println("Eating pizza with thin crust")
}

object AmercianPizza : Pizza {
    override fun create() = println("Creating American pizza")
    override fun bake() = println("Baking pizza slow and steady")
    override fun eat() = println("Eating pizza with thick crust")
}

abstract class PizzaFactory {
    abstract fun createPizza(): Pizza
}

object ItalianPizzaFactory: PizzaFactory() {
    override fun createPizza() = ItalianPizza
}

object AmercianPizzaFactory: PizzaFactory() {
    override fun createPizza() = AmercianPizza
}

/**
 * Simple constructor dependency injection to decouple the client (Person) from all concrete pizza objects.
 */
class Person(private val factory: PizzaFactory) {
    fun makeAndEatPizza() {
        factory.createPizza()
            .run {
                create()
                bake()
                eat()
            }
    }
}

fun main() {
    Person(ItalianPizzaFactory).makeAndEatPizza()
    Person(AmercianPizzaFactory).makeAndEatPizza()
}