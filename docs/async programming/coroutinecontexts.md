# Understanding Coroutine Contexts

* All coroutines run as part of a context
    * created by the launcher
* Context can flow to 'child' coroutines
* Different contexts supplied
* Contexts can be joined

## Dispatchers
* Contexts provide a coroutine dispatcher
    * Determines which thread the coroutine is run on
* Coroutines can run on: 
    * Pool thread
    * Unconfined thread
    * specific thread

* CommonPool
    * The fork/join pool, which is the default pool in the current implementation
* coroutineContext
    * Inherit the context of the current coroutine
* DefaultDispatcher
    * The fork/join pool, which is the default pool in the current implementation
* newSingleThreadContext
    * Runs the coroutine on a new thread. This is an expensive operation. The new thread needs to be managed by your code
* Unconfined
    * Starts the coroutine in the calling thread but only until the first suspension point. Resumes on thread determined
    by the suspending function.
    * helpful if you are using rx java with kotlin coroutines
    
## Accessing the 'job' inside of Coroutine Context

```
val job = launch {
    // to get job within Context (!! because know it is not null)
    val jobWithinContext = coroutineContext[Job]!!
    println("isActive? ${jobWithinContext.isActive")
}
```

* Parent-Child Relationship between coroutineContexts
    * outer.cancelChildren() to cancel children of parent

* Combining Contexts
    * Contexts are maps and combine like maps
    * Keys in the left context are replaced by matching values in the right
    * Missing keys are not added 
    * Therefore order may be important


