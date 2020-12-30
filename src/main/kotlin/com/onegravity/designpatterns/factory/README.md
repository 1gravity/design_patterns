### Definition

There are common misunderstandings when talking about the "factory pattern". Many refer to the "factory pattern" when they really mean the "abstract factory pattern" (and not the "factory method pattern") and when they do, their understanding is that the pattern provides a way to create class instances using a factory object. First there are two factory patterns and second this understanding ignores the fact that the pattern they refer to is called the "abstract factory pattern" and not just the "factory pattern" so by definition we will have one or more concrete implementations of an abstract factory.

#### Abstract Factory pattern

This pattern provides a way to use a group of individual factories that inherit from a common ```AbstractFactory``` interface. The client uses one of these factories to create instances of classes it needs for its operation. The client can know which concrete factory it's using but won't have knowledge about the concrete objects the factory is creating (it's coded against their interfaces).<br>
I would argue that a good implementation of this pattern decouples the client from the concrete factory by using dependency injection. That way the client will have no knowledge about which implementation of the factory it's using allowing to mock an implementation e.g. for testing purposes.

#### Factory Method pattern

We use a factory method to create instances of classes instead of instantiating them directly. The implementation of the factory method can be overridden to create different instances of the class.   

### Use Cases
The purpose of the two patterns is to insulate a client from object creation and thus decouple it from the concrete objects themselves. The client only  knows the interface those objects implement (it's coded against their interfaces).