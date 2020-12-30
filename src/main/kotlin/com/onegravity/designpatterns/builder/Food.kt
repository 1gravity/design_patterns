package com.onegravity.designpatterns.builder

sealed class Food(private val type: String) {
    open fun eat() = println("eating ${javaClass.simpleName}: $type")
}

class Fish(fish: String) : Food(fish)
class Meat(meat: String) : Food(meat)
class Pasta(pasta: String) : Food(pasta)
class Rice(rice: String) : Food(rice)
class Veggie(veggie: String) : Food(veggie)
class Salad(salad: String) : Food(salad)
