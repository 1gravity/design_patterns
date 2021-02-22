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
    abstract fun create(): Pizza
}

object ItalianPizzaFactory: PizzaFactory() {
    override fun create() = ItalianPizza
}

object AmercianPizzaFactory: PizzaFactory() {
    override fun create() = AmercianPizza
}

/**
 * Simple constructor dependency injection to decouple the client (HungryPerson) from all concrete pizza objects.
 */
class HungryPerson(private val factory: PizzaFactory) {
    fun makeAndEatPizza() {
        factory.create()
            .run {
                create()
                bake()
                eat()
            }
    }
}

fun main() {
    HungryPerson(ItalianPizzaFactory).makeAndEatPizza()
    HungryPerson(AmercianPizzaFactory).makeAndEatPizza()
}