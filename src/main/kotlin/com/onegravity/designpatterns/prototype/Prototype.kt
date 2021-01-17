package com.onegravity.designpatterns.prototype

/**
 * Some pizza attributes / ingredients.
 */
sealed class Crust {
    override fun toString(): String = "${javaClass.simpleName} crust"
}
object Thick : Crust()
object Thin : Crust()

sealed class Sauce {
    override fun toString(): String = javaClass.simpleName
}
object TomatoSauce : Sauce()
object BasilTomatoSauce : Sauce()
object GarlicPestoSauce : Sauce()

sealed class Cheese {
    override fun toString(): String = javaClass.simpleName
}
object Mozzarella: Cheese()
object Parmesan: Cheese()
object Provolone: Cheese()

/**
 * This is our prototype.
 */
abstract class Pizza(val crust: Crust, val sauce: Sauce, val cheese: Cheese)  {
    abstract fun bake()
    abstract fun eat()
    abstract fun clone(): Pizza
    override fun toString(): String = javaClass.simpleName
}

/**
 * ItalianPizza and AmercianPizza are our concrete prototypes.
 */
class ItalianPizza(sauce: Sauce, cheese: Cheese) : Pizza(Thin, sauce, cheese) {
    override fun bake() = println("Baking $this hot and fast")
    override fun eat() = println("Eating $this with $crust, $sauce and $cheese")

    override fun clone() = ItalianPizza(sauce, cheese)
}

class AmercianPizza(sauce: Sauce, cheese: Cheese) : Pizza(Thick, sauce, cheese) {
    override fun bake() = println("Baking $this slow and steady")
    override fun eat() = println("Eating $this with $crust, $sauce and $cheese")

    override fun clone() = AmercianPizza(sauce, cheese)
}

/**
 * This is our abstract prototype registry.
 */
abstract class PizzaStore {
    abstract fun addPizza(pizza: Pizza)
    abstract fun getPizza(crust: Crust, sauce: Sauce, cheese: Cheese): Pizza
}

/**
 * This is our concrete prototype registry.
 */
class LocalPizzaStore : PizzaStore() {

    private val pizzas = HashMap<String, Pizza>()

    // The store knows their customers well and prepares the pizzas with the highest demand
    // ahead of time so they can be served faster.
    init {
        addPizza(ItalianPizza(TomatoSauce, Mozzarella))
        addPizza(ItalianPizza(TomatoSauce, Parmesan))
        addPizza(ItalianPizza(GarlicPestoSauce, Mozzarella))
        addPizza(AmercianPizza(TomatoSauce, Mozzarella))
        addPizza(AmercianPizza(BasilTomatoSauce, Provolone))
    }

    override fun addPizza(pizza: Pizza) {
        val key = with (pizza) { crust.toString() + sauce.toString() + cheese.toString() }
        pizzas[key] = pizza
    }

    /**
     * This is our "factory" that either clones existing pizzas (if they already exist) or creates them from scratch
     * (if they don't exist yet). If a pizzas has to be created from scratch it will be added to the registry so it
     * can be re-used (cloned) later on.
     */
    override fun getPizza(crust: Crust, sauce: Sauce, cheese: Cheese): Pizza {
        val key = crust.toString() + sauce.toString() + cheese.toString()
        return pizzas[key]?.apply {
            println("cloning $this ")
        } ?: run {
            val newPizza = if (crust is Thick) AmercianPizza(sauce, cheese) else ItalianPizza(sauce, cheese)
            pizzas[key] = newPizza
            println("creating new $newPizza")
            newPizza
        }.clone()
    }

}

fun Pizza.bakeAndEatPizza() { bake(); eat(); }

fun main() {
    val store = LocalPizzaStore()
    store.getPizza(Thick, TomatoSauce, Mozzarella).bakeAndEatPizza()        // already in stock
    store.getPizza(Thick, BasilTomatoSauce, Mozzarella).bakeAndEatPizza()   // not in stock yet
    store.getPizza(Thick, TomatoSauce, Parmesan).bakeAndEatPizza()          // not in stock yet
    store.getPizza(Thin, GarlicPestoSauce, Mozzarella).bakeAndEatPizza()    // already in stock
    store.getPizza(Thin, TomatoSauce, Mozzarella).bakeAndEatPizza()         // already in stock
    store.getPizza(Thin, BasilTomatoSauce, Parmesan).bakeAndEatPizza()      // not in stock yet
    store.getPizza(Thick, BasilTomatoSauce, Mozzarella).bakeAndEatPizza()   // now in stock as well
    store.getPizza(Thin, BasilTomatoSauce, Parmesan).bakeAndEatPizza()      // now in stock as well
}
