#High Level Functions
##What are they?
* first class citizens
* can pass to and return from functions
* can store in collections

##Strategy Pattern
* Allows an algorithms behavior to be selected at runtime
* In OO the strategy patterns is often implemented using an Interface BUT can just pass a function

Anonymous Functions
all equal when passing in lambdas (functions) into other functions
```
calculateResults(1, 2) { print(it) }
calculateResults(1, 2) { s -> print(s) }
calculateResults(1, 2, { print(it) })
calculateResults(1, 2, { s -> print(s)}
calculateResults(1, 2, ::print)
//call print function that is in scope
```

###Closures
* Kotlin lambdas can mutate values
* Unlike Java 8 Lambdas
* total example

###With and Apply
* Used to make certain operations more natural
* Look like language keywords
* Actually use lambdas

```
m.Title = “Title”
m.When = Date(2017, 1, 1)
m.Who.add(“Kevin”)

//changes to

with(m) {
    Title = “Title”
    When = Date(2017, 1, 1)
    Who.add(“Kevin”)
}

m.apply {
    Title = “Title”
    When = Date(2017, 1, 1)
    Who.add(“Kevin”)
}
//m receives this and returns the object the lambda is applied to
```
