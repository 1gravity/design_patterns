### Definition

Instead of creating objects "from scratch", we create them from prototypical instances.
In Java terminology we clone objects (using the *clone()* method) instead of creating new ones (using the *new* keyword).
The object creation (through cloning) happens in a way that decouples the client from the instantiated classes (similar to the *factory patterns*).

### Use Cases

<ol>
<li>
The creation of an object is expensive and creating clones lowers the cost of object creation.
E.g.:
<ul>
<li>
An account object reflects the result of a backend call.
If we need another account object (defensive copy, definition see below),
it's cheaper to clone the original object than to create a new one (which would involve retrieving the account data again).</li>
<li>
Sometimes we need to build complex objects like a DOM for a web page (builder pattern!).
Having a "template" (aka prototype) helps to build a DOM quicker.
E.g. if we have a template for tables, we can copy the table template/prototype instead of building it from scratch (one clone() call compared to building it using the builder pattern).
</li>
</ul>
</li>
<li>
We want to determine the object to create at runtime based on a prototype which was also built at runtime.
The prototype itself can be the result of our program execution and it could be hard to create more identical or similar objects from scratch. E.g.
<ul>
<li>
A mitotic cell division (remember those biology classes...) can be modelled by cloning an existing cell.
The original cell with all its genetic information is the prototype and could be the result of complex computations
so cloning is the least expensive way to simulate a mitosis and also mirrors the different phases of the real world phenomenon.
<li>
A reporting engine creates templates = prototypes based on dynamic parameters read from different sources (branding requirements, types of reports etc.).
When the user runs a report, the reporting engine takes one of the templates / prototypes, copies / clones it and fills it with data based on user-defined criteria (time frame, location, price etc.).
In this case the prototypes are pre-computed reports to make the creation of the actual reports simpler / cheaper.
</li>
</ul>
</li>
</ol>

### Prototype vs Factory patterns

The factory patterns create objects based on a well-defined class hierarchy.
The caller can pass in arguments, and the factories use them to determine which objects to create.
The prototype pattern adds an extra layer of abstraction on top of the object creation process.
Using the prototype pattern, objects are created (or cloned) based on runtime objects not just based on a class hierarchy.
<ul>
<li>Factory patterns:&nbsp;&nbsp;class hierarchy + arguments + program logic -> instantiate objects</li>
<li>Prototype pattern:&nbsp;class hierarchy + arguments + program logic  -> instantiate prototype + arguments + program logic -> create/clone objects</li>
</ul>
An abstract factory can use prototypes under the hood to increase its flexibility in providing instances of different classes.

### Defensive copying
Defensive copying is a technique to mitigate the negative effects caused by unintentional (or intentional) modifications of shared objects.
Instead of sharing the original object, we share a copy of it and thus any modification made to the copy will not affect the original object
