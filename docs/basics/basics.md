#Kotlin Basics
from [Pluralsight Class](https://app.pluralsight.com/course-player?clipId=133ca59b-5a0b-4261-a533-7a220956d25f
)

## String Templates
* “Here is a string with ${Variable}”
* Simple String Comparison using == is fine.
* If is an expression, and actually returns a value
* Null cannot be a value for a non-type string
* Add a question mark to be able to be null

`Var Anser: String? = null;
Q?.CorrectAnswer`
(If Q is null, do not perform the operation, does not throw an error)
Kotlin comes with a great deal of SAFETY built into it.

Switch Statement —> When Statement

`When (Answer) {`
    `CorrectAnswer -> print(“correct”)`
    `Else -> print(“Try Again")`
 `}`

Apparently more powerful than switch statement

Val number: Int? = try {
    Integer.parseInt(q.Answer) // turns string into int
} catch (e:NumberFormatException) {
Null }

Var values = 1..10 //range of values
For loops
For(I in 1..10) {
    Println(i)
}

//10 downTo 1
//step 2
//1 until 10 prints 1 all the way up to 9 (half-closed range)

For loops for LIST
Var numbers = listOf(1,2,3,4,5)
For (I in numbers) {
    Print(i)
}

For loops for Maps

Var ages = TreeMap<String, Int>()
Ages[“Zoey”] = 27

For((name, age) in ages) {
    Println(“$name is $age”)
}

numbers.withIndex() //maps index and element

Ranges can be created from an object that has a comparable

EXCEPTIONS
Var reader = FileReader(“filename”)
Could use try catch then finally (optionally)


Functions
* Don’t need to be part of a class
* Are introduced with the ‘fun’ keyword
* Can have default parameters
* Can have named parameters
* Can ‘extend’ existing types

@file:JvmName(“displayFunctions”) will change class that compiler creates to wrap functions from ProgramKT to displayFunctions

Default parameters:
Fun connect (addr: URI, useProxy:Boolean = true) : Boolean {}

@JvmOverloads will create java overloads to help with default parameters

Named parameters because order doesn’t matter, and see from call sight what parameters you are passing in

###Extension Functions
Can add functions to classes not owned by you (Static functions)
benefits
1. Cuts down on use of utility classes
2. Makes code much easier to read (looks like a normal method call on the class)
Fun String.replaceMultipleWhiteSpace()
Value of the receiver is this
* Add to existing classes
* Add to your own classes
    * For helper functions

Infix Functions
* Member or extension functions
* Have a single parameter
* Marked with infix keyword

infix fun Header.plus(other:Header) : Header {
    Return Header(this.Name + other.Name)
}

H3 = h1 plus h2

###Operator Overloading
* Predefined operators that can be overloaded (plus instead of +)
* Add operator before infix to be able to use + instead of plus
* Eh don’t use this unless building domain specific languages.

###Tail Recursive Functions
* Use `tailrec` keyword
* Have correct form
* Kotlin optimizes away the recursion
