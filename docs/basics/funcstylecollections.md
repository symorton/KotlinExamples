#Functional Style and Collections

* Many benefits of using functional style with collections
* Can use library functions
* Simplifies code

##Filter and Map
```
val ints = listOf(1,2,3,4,5)
val smallInts = ints.filter{ it < 4 }
// filter anything less than 4 and add to new collection

for(i: Int in smallInts) println(i)

val squaredInts = ints.map {it * it}

for(i: Int in squaredInts) println(i)

// transforms the data in the list

val smallSquaredInts = ints.
                        filter { it < 5 }.
                        map { it * it}

// can chain together these calls

val meetings = listOf(Meeting(1, “Board”), Meeting(2, “Committee”))

val titles = meetings.map {m -> m.titles}
//returns a list of meeting titles

class Meeting(val id: Int, val title: String {}
```

predicate
* Lambdas that return a boolean

* all -> all functions satisfy predicate
* any -> any functions satisfy predicate
* count -> number of functions satisfy
* find -> find first function that satisfy or null

```
var greaterThanThree = {v: Int -> v > 3}
var largeInts = ints.any {it > 3}
var numberOfLargeInts = ints.count(greaterThanThree)
var found = ints.find(greaterThanThree)
```

###.flatMap
List<List<Person>> to List<Person>

###.distinct()
Get back only distinct set of items (must be data class type for hashcode/equals)


###.asSequence()
* Good for infinite collections / lazy loading
* More efficient than lists when it comes to processing the data
* Terminal and Non-Terminal Operations
* Sequences are lazily evaluated
* Evaluation starts when actually using terminal operation
* Like Streams
