package rsk.kotlin

import rsk.java.Created
import rsk.java.User

fun main(args: Array<String>) {
    var user = User("Syd")

    var count = 0

    // may need to use a SAM constructor
    var eventListener = Created({ println("User $it has been created") })

    user.create(eventListener)
}