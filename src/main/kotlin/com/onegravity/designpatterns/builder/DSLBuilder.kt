package com.onegravity.designpatterns.builder

import kotlin.random.Random

class DSLMenu {
    var fish: Fish? = null
    var meat: Meat? = null
    var pasta: Pasta? = null
    var rice: Rice? = null
    var veggie: Veggie? = null
    var salad: Salad? = null

    fun eat() {
        println("*** Eating menu ***")
        fish?.eat()
        meat?.eat()
        pasta?.eat()
        rice?.eat()
        veggie?.eat()
        salad?.eat()
    }
}

fun build(builder: DSLMenu.() -> Unit) = DSLMenu().apply { (builder(this)) }

fun main() {
    build {
        fish = Fish("Salmon")
        rice = Rice("Brown")
        salad = Salad("Lettuce")
    }.eat()

    build {
        meat = Meat("Beef")
        pasta = Pasta("Noodles")
        veggie = Veggie("Broccoli")
        salad = Salad("Iceberg")
    }.eat()

    build {
        meat = Meat("Beef")
        if (Random.nextBoolean())
            pasta = Pasta("Noodles")
        else
            veggie = Veggie("Broccoli")
        salad = Salad("Iceberg")
    }.eat()
}
