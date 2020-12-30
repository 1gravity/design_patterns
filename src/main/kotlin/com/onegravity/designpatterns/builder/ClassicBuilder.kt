package com.onegravity.designpatterns.builder

data class Menu(
    private val fish: Fish?,
    private val meat: Meat?,
    private val pasta: Pasta?,
    private val rice: Rice?,
    private val veggie: Veggie?,
    private val salad: Salad?
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

    data class Builder(
        private var fish: Fish? = null,
        private var meat: Meat? = null,
        private var pasta: Pasta? = null,
        private var rice: Rice? = null,
        private var veggie: Veggie? = null,
        private var salad: Salad? = null) {

        fun fish(fish: Fish) = apply { this.fish = fish }
        fun meat(meat: Meat) = apply { this.meat = meat }
        fun pasta(pasta: Pasta) = apply { this.pasta = pasta }
        fun rice(rice: Rice) = apply { this.rice = rice }
        fun veggie(veggie: Veggie) = apply { this.veggie = veggie }
        fun salad(salad: Salad) = apply { this.salad = salad }

        fun build() = Menu(fish, meat, pasta, rice, veggie, salad)
    }
}

fun main() {
    // the non-builder way to create the Menu object
    Menu(
        Fish("Salmon"),
        null,
        null,
        Rice("Brown"),
        null,
        Salad("Lettuce")
    ).eat()

    Menu(
        null,
        Meat("Beef"),
        Pasta("Noodles"),
        null,
        Veggie("Broccoli"),
        Salad("Iceberg")
    ).eat()

    // the Builder way to create the Menu object
    Menu.Builder()
        .fish(Fish("Salmon"))
        .rice(Rice("Brown"))
        .salad(Salad("Lettuce"))
        .build().eat()

    Menu.Builder()
        .meat(Meat("Beef"))
        .pasta(Pasta("Noodles"))
        .veggie(Veggie("Broccoli"))
        .salad(Salad("Iceberg"))
        .build().eat()
}
