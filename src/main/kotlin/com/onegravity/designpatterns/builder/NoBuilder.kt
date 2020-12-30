package com.onegravity.designpatterns.builder

data class NoBuilderMenu(
    private val fish: Fish? = null,
    private val meat: Meat? = null,
    private val pasta: Pasta? = null,
    private val rice: Rice? = null,
    private val veggie: Veggie? = null,
    private val salad: Salad? = null
) {

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

fun main() {
    // using default and named arguments we don't really need a builder any more
    NoBuilderMenu(
        fish = Fish("Salmon"),
        rice = Rice("Brown"),
        salad = Salad("Lettuce")
    ).eat()

    NoBuilderMenu(
        meat = Meat("Beef"),
        pasta = Pasta("Noodles"),
        veggie = Veggie("Broccoli"),
        salad = Salad("Iceberg")
    ).eat()
}
