### Definition

Lazy initialization delays the creation of an object / data structure or the calculation of a value until the first time it's needed.<br>
This is typically accomplished by wrapping it with an accessor that checks whether the initialization has already been done or not. If it has it will return the object/value straight away. If it hasn't it will create the object (or calculate the value) and return it.

Note: this is *not* a gang of four design pattern.

### Use Cases

Consider using lazy initialization whenever a data structure is expensive to create / initialize and if it's creation and use depends on the execution path of the program.
If the data structure is needed in any case and from the very beginning of the program execution, there's no point in lazy initialize it.