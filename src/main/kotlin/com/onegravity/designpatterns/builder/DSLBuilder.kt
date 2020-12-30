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

    /**
     * If you want an immutable object we can consider the DSLMenu the builder class for the Menu class.
     */
    fun build(menu: DSLMenu) = Menu(menu.fish, menu.meat, menu.pasta, menu.rice, menu.veggie, menu.salad)
}

/**
 * This simple function creates the DSL to build our menu.
 */
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

    // immutable Menu
    build {
        veggie = Veggie("Broccoli")
        salad = Salad("Iceberg")
        build(this)
    }.eat()
}
