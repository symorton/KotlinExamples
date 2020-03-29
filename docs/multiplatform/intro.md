
Key Findings from [this video (from 12/19/19)](https://app.pluralsight.com/player?course=droidcon-sf-session-62&author=droidcon-sf&name=24002e08-547e-4a5f-809e-5ae5ce0e1302&clip=0&mode=live)
* Clean Interfaces are great
* Treat Business logic as block box in shared code
* Aggressively validating for unexpected input
* Testing is v v important for shared code (pain to debug in client)
* State machines and rule engines great for sharing (separate logic from architecture)

## Kotlin Multiplatform

Kotlin Common
* Kotlin/JVM
    * Android
* Kotlin/JS
    * Web
* Kotlin/Native
    * iOS
    
```
// Common
expect fun platform(): String

fun getGreeting(): String {
    return "Kotlin is running on ${platformName()}"
}

// Android
actual fun platformName(): String = "Android"

// iOS
actual fun platformName(): String {
    return UIDevice.currentDevice.systemName()
}
```
* iOS
    * Can use entire UIDevice API
    * Can use Object C libraries
    
* Kotlin.test
    * Official JetBrains library
    * JUnit-style test assertions
    * Support for many test runners:
        * JUnit, JUnit 5, TestNG, Jasmine, Mocha, Jest
        
### Kotlin/JS Deployment Quirks
* external dependency (so no hot fixes)
* Custom Gradle tasks to create/deploy artifacts (npm packages for example)
    * Kotlin/JS is still using ES5: web tooling limitations
    * Minified bundle size (can use Dead Code Eliminator (DCE))
* No automatic typing (like type script)

### Android Quirks
* No just build the JAR

### Kotlin/Native (iOS Quirks)
* Kotlin/Native builds Objective-C framework
* Fat frameworks are not automatic (build same framework for different cpus)
* Exceptions require manual handling
* Concurrency is not good yet
* Framework interdependencies are finicky

Main thing I got from this video: Business logic in shared code, with http calls and other concurrent code not in shared code

## Learnings from Kotlin Multiplatform Development

[this video](https://app.pluralsight.com/player?course=droidcon-nyc-session-56&author=droidcon-nyc&name=ff66f621-8797-4d0e-bc6f-108990b306bd&clip=0&mode=live)

Timeline
1. Onboard Android Devs
2. Kotlin Serialization (to use same data models for Android and iOS)
3. Ktor (networking operations)
4. SQLDelight (?)
5. View models and the rest (but use native for UI imo)

Frustrations for iOS developers
* No control over iOS code
* Ktor Multiplatform is experimental (will get bugs if using in production)
* Can only use it in the main thread (no concurrency)

Testing
* Can write tests once and will run for both android and iOS

Technical Learnings
* Mapping Strings to Enum using Kotlin Serialization (@SerialId does not have any affect)
* Threads issues on the Main Thread in iOS (classes in Kotlin not allowed in other threads in iOS)
* Kotlin code and swift/objective-c code does not need to exist in the same repo
    * can use a script (cocopod to create a framework from kotlin code to put into swift/objective-c code)
* Where are the debugging symbols?
    * dSYM files only for debug? (there is not dSYM files for release yet)
* Coroutines on iOS?
Workaround:
```
// Common
internal expect val ApplicationScope: CoroutineScope

ApplicationScope.launch {
    try {
        val ret = getCitySuggestions(query, isAdding, longitude, latitude)
        callback(ret, null)
    } catch(error: Error) {
        callback(null, error)
    }
}

// ios/actual.kt
internal actual val ApplicationScope: CoroutineScope = MainScope()

private class MainDispatcher: CoroutineDispatcher() {
    overrride fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_main_queue()) { block.run() }
        }
    }

internal class MainScope: CoroutineScope {
    private val dispatcher = MainDispatcher()
    private val job = Job()
    
    override val coroutineContext: CoroutineContext
        get() = dispatcher + job
}
```
* will run coroutine on the main thread.

## Kotlin Multiplatform Libary Development 

[This Talk](https://app.pluralsight.com/player?course=droidcon-sf-session-05&author=droidcon-sf&name=3cf5f647-687d-4fef-aa52-a08c32e08a20&clip=0&mode=live)

Out of box for KMP you don't get:
1. Concurrency
2. Locale
3. Date/Time
4. File I/O
5. Networking
6. (most of JRE)

Optimize for JVM usually

Example
```
// Multiplatform AtomicInt implementation
expect class AtomicInt(initialValue: Int) {
    fun get(): Int
    fun set(newValue: Int)
    fun incrementAndGet(): Int

    fun addAndGet(delta: Int): Int
    fun compareAndSet(expected: Int, new: Int): Boolean
}

// Android (AtomicInteger is same type def)
import java.util.concurrent.atomic.AtomicInteger

actual typealias AtomicInt = AtomicInteger

// iOS 
import kotlin.native.concurrent.AtomicInt

actual class AtomicInt actual constructor(initialValue: Int) {
    private val atom = AtomicInt(initialValue)

    actual fun get(): Int = atom.value
    actual fun set(newValue: Int) {
        atom.value = newValue
    }
    actual fun incrementAndGet(): Int = atom.addAndGet(1)
    actual fun decrementAndGet(): Int = atom.addAndGet(-1)
    actual fun addAndGet(delta: Int): Int = atom.addAndGet(delta)
    actual fun compareAndSet(expected: Int, new: Int): Boolean = atom.compareAndSet(expected, new
}
```

But honestly should minimize expect/actual and use interfaces instead
Empty typealias

What to use
* Interfaces when reasonable
* Singletons and service objects for sure
    * easier to test
* typealias when you need a bunch of things and don't want a parallel delegate hierarchy
* Data classes and type hierarchies get complicated with interfaces

note to self: Look into [CrashKiOS](https://github.com/touchlab/CrashKiOS)
* Crash Reporting for Kotlin iOS

Publishing
* Maven Central
* Bintray (jcenter)
* Jitpack does not work

CI 
* They like App Center wooo
[Getting Started writing a library](go.touchlab.co/kmplib)
* Kotlin Xcode Plugin
* Xcode sync (get you Kotlin in Xcode)




    
    