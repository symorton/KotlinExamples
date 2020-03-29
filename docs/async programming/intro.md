# Introduction

[this tutorial](https://app.pluralsight.com/course-player?clipId=760aaf0e-6c15-4edf-a033-b9193503c683)

## Coroutines
* are async not, necessarily multithreading

* looks almost the same as synchronous code:
    * easy to read
    * way less ceremony
        * async, await, runBlocking

* Different Asynchronous Programming Styles
    * Callbacks
    * Futures
        * Java provides 'Future' classes
            * Easier than callbacks
            
* Coroutines are better than Callbacks and Futures

```
// acts async
suspend fun addBlog(title: String) {
    val id = authenticate()
    val blog = createBlogAsync(id, title)
    processBlog(blog)
}
```

* Coroutine are more natural
    * Looping contructs are natural
    * Exception handling is natural

* runBlocking is a coroutine builder

* Gradle
    * need to add a dependency
    * add appropriate library
    * turn on experimental coroutines
    
```
//in gradle file
dependencies {
    compile "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_coroutines_version"
}

kotlin {
    experimental {
        coroutines "enable"
    }
}
```

* 'launch' coroutine builder example
* coroutines are not tied to a thread

```
fun main(args: Array<String>) {
    
    launch {
        delay(1000)
        println("World")
    }

    print("Hello, ")
    Thread.sleep(1500)
}
```

### Order of Docs
1. ["Coroutine Builders"](coroutinebuilders.md)
2. ["Working with Coroutines"](workingwithcoroutines.md)
3. ["Coroutine Contexts"](coroutinecontexts.md)
4. ["Returning Data from Coroutine"](returningdata.md)