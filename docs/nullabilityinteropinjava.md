#Understand How Nullability Interacts with Your Existing Java Code

## Kotlin Understands Annotation
* @Nullable, @NotNull
* javax.annotation
* android.support.annotation
* org.jetbrains.annotation

##What if there are no annotations?
* Kotlin now working with 'Platform' types
* Developer has full responsibility
* Platform types are hidden, but can see them in error messages

`String!` -> platform type 
* when it doesn't know if type can be null or not
* must do null checks then

##What about overriding Java Types
* Can make the parameters Nullable or not
* When implementing Java interface with Kotlin code, we can choose to return nullable or not code.
