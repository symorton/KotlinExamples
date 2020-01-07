
Key Findings from [this video](https://app.pluralsight.com/player?course=droidcon-sf-session-62&author=droidcon-sf&name=24002e08-547e-4a5f-809e-5ae5ce0e1302&clip=0&mode=live)
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