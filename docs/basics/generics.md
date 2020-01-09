# Generics

Generic Functions

```
// functions
fun <T> List<T>.itemAt(ndx: Int) : T {}

// classes
class Node<T> (private val item: T) {
    fun value() :T {
        return item
    }
}

// classes contrained to particular interface
class Node<T: Number> (private val item: T) {
    fun value() :T {
        return item
    }
}
```

Generics at Runtime
* Java erases all generic type information
* Kotlin can reify this
    * Make into a thing
    * Take type information and make it a real thing that compiler can actually use


```
fun main() {
    val meetings = listOf(BoardMeeting(), FinanceMeeting())

    val board = meetings.typeOf<BoardMeeting>()

    println(board.count())
}

inline fun <reified T> List<*>.typeOf() : List<T> {
    
    val returnList = mutableListOf<T>()

    for (item in this) {
        if (item is T) P
            returnList.add(item)
        }
    }
    
    return returnList
}

open classMeeting {}

class BoardMeeting : Meeting() {}

class FinanceMeeting : Meeting() {}
```

Non-inline Type Parameters
* Sometimes need to make a parameter not-inline when the function is inline

Kotlin Variance
* 'in' and 'out' Keywords
* Declaration site variance
    * Generally more elegant
* Kotlin also supports 'use site' 

Covariant
* Derived type can be used where base type is more specific
* Marked as 'out' parameter
* only ever getting something out of T when using lists and maps and such

Contravariance
* Base type can be used where derived type is more specific 
* mark as 'in'


