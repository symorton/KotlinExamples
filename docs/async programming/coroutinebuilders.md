# Running Functions with Coroutine Builders

* Coroutines have to be run inside a 'context'
    * define 'builders' to do this
* Calling certain functions only make sense inside this context
    * e.g. 'delay'
    * must mark these with 'suspend' keyword

* Coroutine builder that goes to new thread 
 ```
launch {
    delay(1000)
}
```

* Coroutine builder that blocks main thread
 ```
    runBlocking {
        delay(1500)
    }
```

or make the whole functions a coroutine by:

```
fun main(args: Array<String>) = runBlocking {}
```

* Can call coroutines inside of coroutines

* To create a function that suspends coroutine

```
// just add suspend keyword
suspend fun doWork() {
    delay(1000)
}
```

### Testing 

```
class SimpleTest {

    // makes test a coroutine and blocks until test is completed
    @Test
    fun firstTest() = runBlocking {
        doWork()
    }
}
```
