package com.onegravity.designpatterns.lazyinit

import java.util.concurrent.ConcurrentHashMap

fun main() {
    LazyInit().run {
        println("*** Start ***")
        getFruit(LazyInit.FruitType.Apple)
        getFruit(LazyInit.FruitType.Banana)
        getFruit(LazyInit.FruitType.Pear)
        getFruit(LazyInit.FruitType.Banana)
        getFruit(LazyInit.FruitType.Pear)
        getFruit(LazyInit.FruitType.Apple)
        getFruit(LazyInit.FruitType.Pear)
        getFruit(LazyInit.FruitType.Banana)
    }
}

class LazyInit {

    enum class FruitType {
        Apple,
        Banana,
        Pear
    }

    data class Fruit(val type: FruitType)

    interface FruitFactory {
        fun getFruit(type: FruitType): Fruit
    }

    /**
     * Create the FruitFactory "on the fly" (aka lazy)
     */
    private val factory by lazy {
        println("Create FruitFactory")
        object: FruitFactory {
            override fun getFruit(type: FruitType): Fruit {
                println("Create $type")
                return Fruit(type)
            }
        }
    }

    private val fruits = ConcurrentHashMap<FruitType, Fruit>()

    /**
     * Retrieve the Fruit objects from the cache aka HashMap.
     * If it doesn't exist yet, create it and add to the HashMap for future use.
     */
    fun getFruit(type: FruitType) =
        fruits[type] ?: run {
            // not fruit found -> create a new one and add it to the HashMap
            factory.getFruit(type).apply { fruits[type] = this }
        }

}
