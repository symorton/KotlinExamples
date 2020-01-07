#Interfaces
* Basic definitions
* Default methods
* Properties
```
Interface Time {

    fun setTime(hours: Int, mins: Int = 0)
    fun now() : Time
}
```

* Interface is public by default
* No implements and extends keyword

```
class YetiTime: Time {
    override fun setTime(hours: Int, mins: Int) {}
}
```

If want to add another function to interface Time, default method if want:

fun setTime(time: SydTime) = setTime(time.hours)

where

class SydTime {
    Var hours: Int = 0 
}

Class Foo : A, B {
    Override fun doSomething() {
        Super<A>.doSomething()
    }
}

Classes
* ‘Final’ by default
    * Cannot inherit from them
    * Methods are also final by default
    * Use open to show class can be derived from
* ‘Abstract’ classes
* Everything is public by default
    * no “package-private"
    * But does have “internal"
        * This makes the classes visible to everything in module.
* modifiers
* Sealed Classes
    * Algebraic Data Types
    * Can only have a fixed set of derivations
    * “Enums on Steroids"
* Constructors
    * multiple
* Data classes

Example for extending classes:
```
open class Person {
    var firstName: String = “”
    var lastName: String = “”
    open fun getName(): String = “$firstName $lastName”
}

class Student: Person() {
    override fun getName() : String{return “”}
}
```
Example for abstraction:
```
abstract class Person {
    var firstName: String = “”
    var lastName: String = “”
    open fun getName(): String = “$firstName $lastName”
    abstract fun getAddress(): String
}
```

```
class Student: Person() {
    override fun getName() : String{return “”}

    override fun getAddress(): String {
        return “”
    }
}
```

Sealed Classes

```
sealed class PersonEvent {
    class Awake: PersonEvent()
    class Asleep: PersonEvent()
    class Eating(val food: String) : PersonEvent()
}

fun handlePersonEvent(event: PersonEvent) {
    when(event) {
        is PersonEvent.Awake -> println(“Awake”)
        is PersonEvent.Asleep -> println(“Asleep”)
        is PersonEvent.Eating -> println(event.food)
    }
}
```

// Restricted Set of Classes
// sealed class instead of enum

Secondary Constructor

```
open class Person(name: String) {
    constructor(name: String, age: Int) : this(name)
}
```
// prefer to use default values instead of secondary contructors

Calling superclass constructor
class Student(name: String): Person(name)

Private Constructors
* usually used to inhibit constructions
    * eg to create a singleton
* In Kotlin there is a better way 

Data Classes
* Provide a convenient way to override equals, hashCode, and toString
* Typically immutable classes
* Kotlin also generates ‘copy’ method
