#Campanion Objects
* Kotlin does not have static methods
* However can have ‘singletons'
* Use ‘objects’ keyword
* Use ‘companion object’ to get ‘statics'

object Course(val id: Int, val Title: String) {}

```
object Courses {
    var allCourses = arrayListOf<Course>()
}
```

 …and then in my Student Class

```
fun enrole(courseName:String) {
    val course = Courses.allCourses
        .filter{it.Title == courseName}
        .firstOrNull()
}
```

### Singletons used a anti-pattern
* Hard to test and reuse

### Objects
* Can derive from other classes/interfaces
* Can bu used where any ‘instance’ is used
* Can be declared inside of other classes

### Companion Object
* Factory object and static members
* Essentially static functions on that class
* create() methods

Inside of Student Class
```
companion object {
    fun createUndergrad(name: String): Undergraduate {
        return Undergraduate(name)
    }
}
//acts as a Factory method to create Undergraduate object
```

To use this method in code 
Student.createUndergrad(“Simon”)

Can Also implement interfaces:

```
companion object: XmlSerializer<Student> {
    @JvmStatic
    override fun toXml(item: Student) {}
    fun createUndergrad(name: String): Undergraduate {
        return Undergraduate(name)
    }
}
… }


interface XmlSerializer<T> {
    fun toXml(item: T)
}
```

Have to add annotation @JvmStatic to companion object to really say that it is static
