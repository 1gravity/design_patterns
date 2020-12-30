### Definition

The builder pattern used to be used to simplify the creation of complex objects.
Typically, we use constructors to create different representations of the same class. 
If a class has many representations, we end up with many constructors. 
Having optional parameters amplifies the problem, although modern languages offer support with default and named arguments.

I wrote `used to be used` because IMHO in a language like Kotlin, the builder pattern is obsolete (see below).

### Examples
#### Classic Builder
```
Menu.Builder()
    .fish(Fish("Salmon"))
    .rice(Rice("Brown"))
    .salad(Salad("Lettuce"))
    .build().eat()
```
#### No Builder
Using default and named arguments we get:
```
NoBuilderMenu(
    fish = Fish("Salmon"),
    rice = Rice("Brown"),
    salad = Salad("Lettuce")
).eat()
```
#### DSL Builder
Using Kotlin DSL we get:
```
build {
    fish = Fish("Salmon")
    rice = Rice("Brown")
    salad = Salad("Lettuce")
}.eat()
```
### Comparison
Building the object is comparable for all three variants (5 lines of code).<br>
With the classic builder pattern approach we need to create an extra builder class (more code) -> higher memory usage (in-memory duplication of all arguments).<br>
With the DSL approach we need to create a build function (just one line of code), but gain the ability to add more logic into the build function. E.g. we could do
```
    build {
        meat = Meat("Beef")
        if (Random.nextBoolean())
            pasta = Pasta("Noodles")
        else
            veggie = Veggie("Broccoli")
        salad = Salad("Iceberg")
    }.eat()
```
If you just want to build an object, using default and named arguments is absolutely sufficient.
If you need a bit more flexibility, the DSL approach is the way to go.
Never use the classical builder pattern, it's obsolete. 