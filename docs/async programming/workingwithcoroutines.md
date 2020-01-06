# Working with Coroutines

* What happens if you want to wait for a coroutine to finish? (not delay)
* Can wait until the coroutine has finished
    * Use `join`
* Conversely, what if you can no longer wait
    * `cancel` coroutine

### `join`

* similar to joining a thread
    * calling code blocks until the coroutine has finished

#### Job Interface

* `launch` returns a Job
    * Can use this to `join` the coroutine
    * Can also check if the coroutine has finished

```
fun main(args: Array<String>) = runBlocking {
    
    val job = launch {
        delay(1000)
        println("World")
    }

    print("Hello, ")

    // wait until job has finished
    job.join()
}
```

### Cancelling Coroutines
* If you don't check for cancellation then will not be cancelled.
* All built-in suspending functions co-operate

#### How?
1. Can call a built in suspending function
    * `yield` is a good choice
2. Can explicitly inspect cancellation state
    * job has parameter for this
    
```
fun main(args: Array<String>) = runBlocking {
    
    val job = launch {
        repeat(1000) {
            delay(100)
            // yield() will take care of cancelling as well.
            print(".")
        }
    }

    delay(2500)

    // cancels job and joins main thread
    // job.cancel()
    // job.join()
    job.cancelAndJoin()

    println("done")
}
```

delay deals with cancellation

#### How do you co-operate without built in suspend functions (like delay)?

* To co-operate use `isActive`
    * This is a property of CoroutineScope
    * Available inside builders
    * Not available inside suspending function (but is coming) so need diff approach

```
if (!isActive) throw CancellationException()
OR
if (!isActive) return@launch
 ```

Cancellation Throws Exceptions
* Suspending functions throw exception when cancelled
    * CancellationException
* Need to close resources in our code
* May need to run suspending function in finally
    * will throw CancellationException
    * needs to execute in a special context

```
job.cancel(CancellationException("Reason to cancel"))
job.join()
```

Coroutine Infrastructure deals with Cancellation Exceptions but not other exceptions

### Using Timeouts

* One Reason for Cancellation
* What if we could timeout the code
    * May not then need cancellation
* But need to check for an exception (`withTimeout(100)`) or use (`withTimeoutOrNull(100)`)