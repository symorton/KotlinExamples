#Nullability

* Java often throws `NullPointerException`
* Need lots of defensive code
* Kotlin supports 'nullable' types
    * Means only explicit variables can be null
    
    * At compile time, we get null checks
    
* What if you want to allow null?
    * Variable can be declared to accept null
    * Put  '?' after type name
    
```
// m can be either null or Meeting
val m: Meeting?
```

* If we try to call a function through a method that can hold a null value, it won't be allowed.
    * Unless we explicitly allow it 
* Assignation
    * Cannot assign to non-null type
    
```
// not allowed because type mismatch.
val m: Meeting? = null
val newMeeting = Meeting()
newMeeting = m
```

###Safe Call (?.)

`m?.method()`
* Checks for null first
* calls function only if non-null reference

##Elvis (?:) lol
`newMeeting = m ?: Meeting()`
* null-coalescing operator
* if m if null, newMeeting equals m, else newMeeting equals Meeting()

## Safe cast (as?)
`val saveable = o as? ISaveable`
* Casts to type or returns null

## Not-Null assertion
`m!!.close()`
* very blunt 
* asserts that something is not null
* throws NPE if it is where operator is used
* meant to stand out!!

## Example in Kotlin

```
fun main(args: Array<String> {
    var m: Meeting? = null
    var newMeeting = Meeting()

    //not allowed by compilier
    newMeeting = m
    
    //allowed
    m = newMeeting

    closeMeeting(newMeeting)

    newMeeting = m ?: Meeting()
}

fun closeMeeting(m: Meeting?): Boolean? {
    //safe call
    return if (m?.canClose == true) m?.close()
    else false
}

// OR write like this
fun closeMeeting(m: Meeting?): Boolean? {
    // try to avoid this
    return if (m!!.canClose) m.close()
    else false
}

class Meeting {
    val canClose: Boolean = false

    fun close(): Boolean {
        return true
    }
}
```

### 'let' function
* used to avoid explicit null checks
* useful when passing Nullable values to functions expecting non-null values

```
m?.let {
    closeMeetingNonNull(m)
}

fun closeMeetingNonNull(m: Meeting): Boolean? {
    return if (m.canClose) m.close()
    else false
}
```

### Late initialized properties
* Sometimes values cannot be initialized when declared
* Do not want to mark them as Nullable
* Use 'lateinit' instead
    * Then you are on hook to initialize it.

`var address: Address? = null` but don't want this to be nullable to
`lateinit var address: Address`
